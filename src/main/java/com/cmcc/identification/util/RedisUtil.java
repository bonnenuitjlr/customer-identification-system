package com.cmcc.identification.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisCommands;


@Component
public class RedisUtil {

	@Autowired(required = false)
	@Qualifier("redisTemplate")
	private RedisTemplate<?, ?> template;
	
	public RedisUtil(RedisTemplate<String, Object> redisTemplate) {
		this.template = redisTemplate;
	}
	
	public Long ttl(String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.ttl(rawKey);
			}
		}, true);
	}
	
	
	public String rPop(String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return deserializeString(connection.rPop(rawKey));
			}
		}, true);
	}
	
	public String lPop(String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return deserializeString(connection.lPop(rawKey));
			}
		}, true);
	}
	
	public Long lPushX(String key,String values) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(values); 
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.lPushX(rawKey,rawVal);
			}
		}, true);
	}
	
	public Long rPushX(String key,String values) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(values); 
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.rPushX(rawKey,rawVal);
			}
		}, true);
	}
	
	public Long lPush(String key,Set<String> values) {
		final byte[] rawKey = rawByte(key);
		final byte[][] rawVal = rawBytes(values); 
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.lPush(rawKey,rawVal);
			}
		}, true);
	}
	
	public Long rPush(String key,Set<String> values) {
		final byte[] rawKey = rawByte(key);
		final byte[][] rawVal = rawBytes(values); 
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.rPush(rawKey,rawVal);
			}
		}, true);
	}
	
	public String get(String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return deserializeString(connection.get(rawKey));
			}
		}, true);
	}

	public Boolean hSet(String key,String field,String value) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawfield = rawByte(field);
		final byte[] rawVal = rawByte(value);
		
		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.hSet(rawKey, rawfield, rawVal);
			}
		}, true);
	}
	
	
	public Boolean hSetNX(String key,String field,String value) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawfield = rawByte(field);
		final byte[] rawVal = rawByte(value);
		
		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.hSetNX(rawKey, rawfield, rawVal);
			}
		}, true);
	}
	
	
	public List<String> hVals(String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<List<String>>() {
			public List<String> doInRedis(RedisConnection connection) {
				return deserializeValues(connection.hVals(rawKey));
			}
		}, true);
	}
	
	
	public String hGet(final String key,final String field) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(field);
		
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return deserializeString(connection.hGet(rawKey, rawVal));
			}
		}, true);
	}
	
	public Long hDel(String key,Set<String> fields) {
		final byte[][] rawBytes = rawBytes(fields);
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.hDel(rawKey, rawBytes);
			}
		}, true);
	}
	
	public Boolean hExists(String key,String field) {
		final byte[] rawField = rawByte(field);
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.hExists(rawKey, rawField);
			}
		}, true);
	}
	
	public Double hIncrBy(String key,String field, double delta) {
		final byte[] rawFields = rawByte(field);
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<Double>() {
			public Double doInRedis(RedisConnection connection) {
				return connection.hIncrBy(rawKey, rawFields,delta);
			}
		}, true);
	}
	
	
	public Long hIncrBy(String key,String field, long delta) {
		final byte[] rawFields = rawByte(field);
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.hIncrBy(rawKey, rawFields,delta);
			}
		}, true);
	}
	
	public List<String> hMGet(String key,Set<String> fields) {
		if (fields.isEmpty()) {
			return Collections.emptyList();
		}
		final byte[][] rawBytes = rawBytes(fields);
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<List<String>>() {
			public List<String> doInRedis(RedisConnection connection) {
				return deserializeValues(connection.hMGet(rawKey, rawBytes));
			}
		}, true);
	}
	
	
	public String hMSet(String key,Map<String,String> hashes) {
		Set<String> keys = hashes.keySet();
		Map<byte[], byte[]> mapdata = new HashMap<byte[], byte[]>();
		for (String string : keys) {
			mapdata.put(rawByte(string), rawByte(hashes.get(string)));
		}
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				connection.hMSet(rawKey, mapdata);
				return null;
			}
		}, true);
		
	}
	
	
	public Boolean expire(String key,long seconds) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.expire(rawKey, seconds);
			}
		}, true);
		
	}
	
	public String getSet(String key, String newValue) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(newValue);
		
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return deserializeString(connection.getSet(rawKey, rawVal));
			}
		}, true);
		
	}
	
	
	public Long del(String key) {
		final byte[][] rawBytes = new byte[1][];
		int counter = 0;
		rawBytes[counter++] = rawByte(key);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.del(rawBytes);
			}
		}, true);
	}
	
	public Long del(Set<String> keys) {
		final byte[][] rawBytes = rawBytes(keys);
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.del(rawBytes);
			}
		}, true);
	}
	
	public Boolean exists(final String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.exists(rawKey);
			}
		}, true);
		
	}
	
	public Long incr(final String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.incr(rawKey);
			}
		}, true);
	}
	
	
	public Long incrBy(String key, final long delta) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.incrBy(rawKey, delta);
			}
		}, true);
	}

	public Double incrBy(String key, final double delta) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Double>() {
			public Double doInRedis(RedisConnection connection) {
				return connection.incrBy(rawKey, delta);
			}
		}, true);
		
	}
	
	
	public Long decrBy(String key, final long delta) {
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.decrBy(rawKey, delta);
			}
		}, true);
	}
	
	
	public Long decr(String key) {
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.decr(rawKey);
			}
		}, true);
	}

	public String getRange(String key, final long start, final long end) {
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				byte[] rawReturn = connection.getRange(rawKey, start, end);
				return deserializeString(rawReturn);
			}
		}, true);
	}

	public List<String> mGet(Set<String> keys) {
		if (keys.isEmpty()) {
			return Collections.emptyList();
		}

		final byte[][] rawBytes = rawBytes(keys);

		return template.execute(new RedisCallback<List<String>>() {
			public List<String> doInRedis(RedisConnection connection) {
				List<byte[]> rawReturn = connection.mGet(rawBytes);
				return deserializeValues(rawReturn);
			}
		}, true);
	}

	public void set(String key, String value) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(value);
		
		template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				connection.set(rawKey, rawVal);
				return null;
			}
		}, true);
	}

	public void setEx(String key,long seconds,String value) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(value);
		
		template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				connection.setEx(rawKey, seconds, rawVal);
				return null;
			}
		}, true);
	}
	
	
	public boolean setNXRT(String key,long seconds,String value) {
		String result = template.execute(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				JedisCommands commands = (JedisCommands) connection.getNativeConnection();
				return commands.set(key, value, "NX", "PX", seconds);
			}
		});
		return !isEmptyString(result);
	}
	
	
	/**
	 * 判断传入的字符串是否为空串
	 * 
	 * @return
	 */
	private boolean isEmptyString(String str) {
		return str == null ? true : str.trim().equals("") ? true : false;
	}
	
	public boolean releaseLock(String key,String value) {
		try {// 释放锁的时候，有可能因为持锁之后方法执行时间大于锁的有效期，此时有可能已经被另外一个线程持有锁，所以不能直接删除
			List<String> keys = new ArrayList<String>();
			keys.add(key);
			List<String> args = new ArrayList<String>();
			args.add(value);

			// 使用lua脚本删除redis中匹配value的key，可以避免由于方法执行时间过长而redis锁自动过期失效的时候误删其他线程的锁
			// spring自带的执行脚本方法中，集群模式直接抛出不支持执行脚本的异常，所以只能拿到原redis的connection来执行脚本
			Long result = template.execute(new RedisCallback<Long>() {
				public Long doInRedis(RedisConnection connection) throws DataAccessException {
					Object nativeConnection = connection.getNativeConnection();
					// 集群模式和单机模式虽然执行脚本的方法一样，但是没有共同的接口，所以只能分开执行
					// 集群模式
					if (nativeConnection instanceof JedisCluster) {
						return (Long) ((JedisCluster) nativeConnection).eval("UNLOCK_LUA", keys, args);
					}
					// 单机模式
					else if (nativeConnection instanceof Jedis) {
						return (Long) ((Jedis) nativeConnection).eval("UNLOCK_LUA", keys, args);
					}
					return 0L;
				}
			});
			
			return result != null && result > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public void pSetEx(String key,long milliseconds,String value) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(value);
		
		template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				connection.pSetEx(rawKey, milliseconds, rawVal);
				return null;
			}
		}, true);
		
	}

	public Boolean setNX(String key, String value) {
		final byte[] rawByte = rawByte(key);
		final byte[] rawValue = rawByte(value);

		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.setNX(rawByte, rawValue);
			}
		}, true);
		
	}

	public Long size(String key) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.strLen(rawKey);
			}
		}, true);
	}
	
	
	public Long sAdd(final String key,final Set<String> values) {
		final byte[] rawKey = rawByte(key);
		final byte[][] rawValues = rawBytes(values);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.sAdd(rawKey, rawValues);
			}
		}, true);
	}
	
	
	public Long sRem(String key, Set<String> values) {
		final byte[] rawKey = rawByte(key);
		final byte[][] rawValues = rawBytes(values);
		
		return template.execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection) {
				return connection.sRem(rawKey, rawValues);
			}
		}, true);
	}
	
	
	public String sPop(String key) {
		final byte[] rawKey = rawByte(key);
		
		return template.execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection) {
				return deserializeString(connection.sPop(rawKey));
			}
		}, true);
	}
	
	
	public Boolean sIsMember(String key, String value) {
		final byte[] rawKey = rawByte(key);
		final byte[] rawVal = rawByte(value);
		
		return template.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection) {
				return connection.sIsMember(rawKey,rawVal);
			}
		}, true);
	}
	
	
	public Cursor<byte[]> sScan(String key, ScanOptions options) {
		final byte[] rawKey = rawByte(key);
		return template.execute(new RedisCallback<Cursor<byte[]>>() {
			public Cursor<byte[]> doInRedis(RedisConnection connection) {
				return connection.sScan(rawKey,options);
			}
		}, true);
	}
	
	
	byte[][] rawBytes(Set<String> keys) {
		byte[][] rawBytes = new byte[keys.size()][];
		int counter = 0;
		for (String hashKey : keys) {
			rawBytes[counter++] = rawByte(hashKey);
		}
		return rawBytes;
	}
	
	byte[] rawByte(String value) {
		return value.getBytes();
	}

	byte[] rawString(String key) {
		return key.getBytes();
	}
	
	Set<String> deserializeValues(Set<byte[]> rawValues) {
		if(null==rawValues) {
			return null;
		}
		Set<String> sets = new HashSet<String>();
		for (byte[] bs : rawValues) {
			sets.add(deserializeString(bs));
		}
		return sets;
	}
	
	List<String> deserializeValues(List<byte[]> rawValues) {
		if(null==rawValues) {
			return null;
		}
		List<String> lists = new ArrayList<String>();
		for (byte[] bs : rawValues) {
			lists.add(deserializeString(bs));
		}
		return lists;
	}

	String deserializeString(byte[] value) {
		if(null==value) {
			return null;
		}
		return new String(value);
	}

}
