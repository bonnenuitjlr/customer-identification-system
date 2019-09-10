package com.cmcc.identification.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    enum MODE {
        stand_alone, cluster, sentinel
    }

    public RedisConnectionFactory connectionFactory(String hostName, int maxIdle, int maxTotal, int database,
                                                    long maxWaitMillis, boolean testOnBorrow, String mode, String password, String master4Sentinel) throws Exception {
        MODE _mode = MODE.valueOf(mode);
        if (_mode == null) {
            throw new IllegalArgumentException("property [redis.mode] wrong,allowed value is " + MODE.values());
        }
        switch (_mode) {
            case stand_alone: {
                return connectionFactoryStandAlone(hostName, maxIdle, maxTotal, database, password, maxWaitMillis, testOnBorrow);
            }
            case cluster: {
                return connectionFactoryCluster(this.getNodes(hostName, mode), maxTotal, maxIdle, database, password, maxWaitMillis, testOnBorrow);
            }
            case sentinel: {
                return connectionFactorySentinel(this.getNodes(hostName, mode), master4Sentinel, maxTotal, maxIdle, database, password, maxWaitMillis, testOnBorrow);
            }
            default: {
                throw new Exception("RedisConnectionFactory build exception");
            }
        }
    }

    @Bean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisATemplate(@Value("${spring.redis.hostname}") String hostName,
                                                        @Value("${spring.redis.pool.maxIdle}") int maxIdle,
                                                        @Value("${spring.redis.pool.maxTotal}") int maxTotal, @Value("${spring.redis.database}") int database,
                                                        @Value("${spring.redis.pool.maxWaitMillis}") long maxWaitMillis,
                                                        @Value("${spring.redis.pool.testOnBorrow}") boolean testOnBorrow,
                                                        @Value("${spring.redis.mode}") String mode,
                                                        @Value("${spring.redis.password}") String password,
                                                        @Value("${spring.redis.master4Sentinel}") String master4Sentinel) throws Exception {

        RedisTemplate<String, Object> temple = new RedisTemplate<String, Object>();
        temple.setKeySerializer(new StringRedisSerializer());
        temple.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        temple.setConnectionFactory(connectionFactory(hostName, maxIdle, maxTotal, database, maxWaitMillis, testOnBorrow, mode, password, master4Sentinel));
        return temple;
    }

	/*// 原始配置
	@Bean(name = "redisBTemplate")
	@ConditionalOnProperty(prefix = "abcache", name = {"nodeB.enabled","redis.enabled"})
	public RedisTemplate<String, Object> redisBTemplate(@Value("${abcache.nodeB.hostName}") String hostName,
			@Value("${abcache.nodeB.maxIdle}") int maxIdle,
			@Value("${abcache.nodeB.maxTotal}") int maxTotal, @Value("${abcache.nodeB.database}") int database,
			@Value("${abcache.nodeB.maxWaitMillis}") long maxWaitMillis,
			@Value("${abcache.nodeB.testOnBorrow}") boolean testOnBorrow,
			@Value("${abcache.nodeB.mode}") String mode,
			@Value("${abcache.nodeB.master4Sentinel}") String master4Sentinel) throws Exception {
		
		RedisTemplate<String, Object> temple = new RedisTemplate<String, Object>();
		
		temple.setKeySerializer(new StringRedisSerializer());
		temple.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		
		temple.setConnectionFactory(connectionFactory(hostName, maxIdle, maxTotal, database, maxWaitMillis, testOnBorrow, mode, master4Sentinel));
		return temple;
	}*/

    public RedisConnectionFactory connectionFactoryStandAlone(String hostName, int maxIdle, int maxTotal,
                                                              int index, String password, long maxWaitMillis, boolean testOnBorrow) {
        String[] info = getIpPort(hostName);
        String host = info[0];
        int port = Integer.parseInt(info[1]);

        JedisConnectionFactory jedis = new JedisConnectionFactory();
        jedis.setHostName(host);
        jedis.setPort(port);
        jedis.setPassword(password);
        if (index != 0) {
            jedis.setDatabase(index);
        }
        jedis.setPoolConfig(poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow));
        jedis.afterPropertiesSet();
        RedisConnectionFactory factory = jedis;

        return factory;
    }

    private RedisConnectionFactory connectionFactorySentinel(Set<String> nodes, String master4Sentinel, int maxTotal,
                                                             int maxIdle, int database, String password, long maxWaitMillis, boolean testOnBorrow) {

        RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration().master(master4Sentinel);
        String host;
        int port;
        for (String sentinelNode : nodes) {
            host = sentinelNode.split(":")[0];
            port = Integer.parseInt(sentinelNode.split(":")[1]);

            sentinelConfig.addSentinel(new RedisNode(host, port));
        }

        JedisConnectionFactory factory = new JedisConnectionFactory(sentinelConfig);
        factory.setUsePool(true);
        factory.setDatabase(database);
        factory.setPassword(password);

        JedisPoolConfig poolConf = this.poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow);
        factory.setPoolConfig(poolConf);

        factory.afterPropertiesSet();
        return factory;
    }

    private RedisConnectionFactory connectionFactoryCluster(Set<String> nodes, int maxTotal, int maxIdle, int database, String password, long maxWaitMillis, boolean testOnBorrow) {
        RedisClusterConfiguration clusterConf = new RedisClusterConfiguration(nodes);
        clusterConf.setMaxRedirects(1);
        JedisConnectionFactory factory = new JedisConnectionFactory(clusterConf);
        JedisPoolConfig poolConf = this.poolCofig(maxIdle, maxTotal, maxWaitMillis, testOnBorrow);
        factory.setUsePool(true);
        factory.setPoolConfig(poolConf);
        factory.setDatabase(database);
        factory.setPassword(password);

        factory.afterPropertiesSet();
        return factory;
    }

    public JedisPoolConfig poolCofig(int maxIdle, int maxTotal, long maxWaitMillis, boolean testOnBorrow) {
        JedisPoolConfig poolCofig = new JedisPoolConfig();
        poolCofig.setMaxIdle(maxIdle);
        poolCofig.setMaxTotal(maxTotal);
        poolCofig.setMaxWaitMillis(maxWaitMillis);
        poolCofig.setTestOnBorrow(testOnBorrow);
        return poolCofig;
    }


    private String[] getIpPort(String hostName) {
        String[] info = hostName.split(":");
        return info;
    }

    private Set<String> getNodes(String hostName, String mode) {
        Set<String> nodesList = new HashSet<String>();
        if (MODE.stand_alone.name().equals(mode)) {
            nodesList = null;
        } else if (MODE.cluster.name().equals(mode)) {
            String[] nodeArray = hostName.split(",");
            for (String str : nodeArray) {
                nodesList.add(str);
            }
        } else if (MODE.sentinel.name().equals(mode)) {
            String[] nodeArray = hostName.split(",");
            for (String str : nodeArray) {
                nodesList.add(str);
            }
        }
        return nodesList;
    }

}
