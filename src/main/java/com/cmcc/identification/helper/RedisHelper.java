package com.cmcc.identification.helper;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.cmcc.identification.util.RedisUtil;

@Component
public class RedisHelper {
	
	@Resource
    public RedisUtil redisUtil;
	
	@Value("${redis.uniquekey.mac_key}")
    private String mac_key;

    @Value("${redis.uniquekey.mac_key_max}")
    private String mac_key_max;

    @Value("${redis.uniquekey.org_key}")
    private String org_key;

    @Value("${redis.uniquekey.org_key_max}")
    private String org_key_max;

    @Value("${redis.uniquekey.mac_key_image}")
    private String mac_key_image;
	
	/*
     *  把mac_address转化为摄像头id，并维护mac集合
     */
    public synchronized String getCameraIdByMacAddress(String mac_address) {

        boolean flag = redisUtil.hExists(mac_key, mac_address);
        if (flag) {
            return redisUtil.hGet(mac_key, mac_address);
        } else {
            long tmpMax = redisUtil.incr(mac_key_max);
            redisUtil.hSet(mac_key, mac_address, String.valueOf(tmpMax));
            return String.valueOf(tmpMax);
        }
    }

    /*
     *  把org_id转化为store_id，并维护店铺id集合
     */
    public synchronized String getStoreIdByOrgId(String org_id) {

        boolean flag = redisUtil.hExists(org_key, org_id);
        if (flag) {
            return redisUtil.hGet(org_key, org_id);
        } else {
            long tmpMax = redisUtil.incr(org_key_max);
            redisUtil.hSet(org_key, org_id, String.valueOf(tmpMax));
            return String.valueOf(tmpMax);
        }
    }

}
