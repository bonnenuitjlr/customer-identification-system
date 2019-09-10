package com.cmcc.identification;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cmcc.identification.util.RedisUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {})
public class RedisTest {
	
	@Resource
	public RedisUtil redisUtil;
	
	@Resource
	public RedisTemplate redisTemplate;
	
//	@Resource
//	public RedisConfiguration redisConfiguration;
//	
//	@Resource
//	public RedisTemplate redisTemplate;
	
	@Test
	public void catJedisCon() {
		
	}
	
	@Test
	public void catJedis() {
//		redisUtil.set("xin", "yu");
		String res = redisUtil.get("xin");
		System.out.println("redis-key: xin="+res);
	}
	
	@Test
	public void hSetTest() {
		String key = "HKEY";
		Map<String,String> kv = new HashMap<String,String>();
		kv.put("k1","v1");
		kv.put("k2","v2");
		kv.put("k3","v3");
		redisUtil.hMSet(key, kv);
		System.out.println("redis-hset: HKEY-k1 exist="+redisUtil.hExists(key, "k1"));				// true
		System.out.println("redis-hset: HKEY-k4 exist="+redisUtil.hExists(key, "k4"));				// false
		System.out.println("redis-hset: HKEY-k1 value="+redisUtil.hGet(key, "k1"));					// v1
		System.out.println("redis-hset: HKEY-k4-v4 add="+redisUtil.hSet(key, "k4", "v4"));			// true
		System.out.println("redis-hset: HKEY all value="+redisUtil.hVals(key));						// [v3, v1, v2, v4]
	}

}
