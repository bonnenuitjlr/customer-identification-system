package com.cmcc.identification.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.identification.entity.CharacteristicsLibrary;
import com.cmcc.identification.remote.GclibRemote;
import com.cmcc.identification.util.MD5Util;
import com.cmcc.identification.util.RedisUtil;
import com.cmcc.identification.vo.CharacteristicsLibraryVo;
import com.cmcc.identification.vo.R;
import com.cmcc.identification.vo.Response;

@Service
public class GclibCacheManager {

    @Autowired
    private GclibRemote gclibRemote;

    @Autowired
    private RedisUtil redisUtil;

    @Value("${redis.uniquekey.org_key}")
    private String org_key;

    public R featureStorage(CharacteristicsLibrary gclib) {
        Map<String, Object> requestMap = featureStorage_transMap(gclib);
        String har = gclibRemote.featureStorage(requestMap);
        if (StringUtils.isEmpty(har)) {
            return R.ERROR(500, "调用接口异常");
        }
        Response response = JSONObject.parseObject(har, Response.class);
        if (response.getCode() == 200) {
            return R.OK(response.getData());
        }
        return R.ERROR(response.getCode(), response.getMsg());
    }

    public R featureDelete(CharacteristicsLibraryVo gclib) {
        Map<String, Object> requestMap = featureDelete_transMap(gclib);
        String har = gclibRemote.featureDelete(requestMap);
        if (StringUtils.isEmpty(har)) {
            return R.ERROR(500, "调用接口异常");
        }
        Response response = JSONObject.parseObject(har, Response.class);
        if (response.getCode() == 200) {
            return R.OK(response.getData());
        }
        return R.ERROR(response.getCode(), response.getMsg());
    }

    private Map<String, Object> featureStorage_transMap(CharacteristicsLibrary gclib) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("user_pic", gclib.getBase64_face());
        map.put("uuid", gclib.getUuid());
        map.put("type", Integer.valueOf(gclib.getType()));
        map.put("org_id", gclib.getOrg_id());
        map.put("store_id", redisUtil.hGet(org_key, gclib.getOrg_id()));
        map.put("db_no", 3);
        String identity_id = MD5Util.MD5(gclib.getOrg_id() + gclib.getUuid());
        map.put("identity_id", identity_id);
        return map;
    }

    private Map<String, Object> featureDelete_transMap(CharacteristicsLibraryVo gclib) {
        Map<String, Object> map = new HashMap<String, Object>();

        List<Map<String, Object>> list = new ArrayList<>();
        String[] sl = gclib.getUuid();
        for (String s : sl) {
            Map<String, Object> tmp = new HashMap<String, Object>();
            tmp.put("db_no", 3);
            tmp.put("uuid", s);
            tmp.put("type", 0);
            String identity_id = MD5Util.MD5(gclib.getOrg_id() + s);
            tmp.put("identity_id", identity_id);
            list.add(tmp);
        }
        String value = JSONObject.toJSONString(list);
        map.put("identity_ids", value);
        map.put("org_id", gclib.getOrg_id());
        map.put("store_id", redisUtil.hGet(org_key, gclib.getOrg_id()));
        return map;
    }

}
