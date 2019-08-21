package com.cmcc.identification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.identification.cache.GclibCacheManager;
import com.cmcc.identification.util.MD5Util;
import com.cmcc.identification.vo.CharacteristicsLibraryVo;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class IdentificationApplicationTests {
	
    @Test
    public void contextLoads() {
    }

    @Test
    public void CharacteristicsLibraryVoTest() {
    	CharacteristicsLibraryVo vo = new CharacteristicsLibraryVo();
    	String[] uuid = {"001","002"};
    	vo.setUuid(uuid);
    	vo.setOrg_id("0001");
    	vo.setPrd_id("0002");
    	vo.setCty_id("0003");
    	Map<String,Object> map = featureDelete_transMap(vo);
    	System.out.println(JSONObject.toJSON(map));
    	System.out.println(JSONObject.toJSON(vo));
    }
    
    public Map<String,Object> featureDelete_transMap(CharacteristicsLibraryVo gclib){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("org_id", gclib.getOrg_id());
		
		List<Map<String,Object>> list = new ArrayList<>();
		String[] sl = gclib.getUuid();
		for(String s : sl) {
			Map<String,Object> tmp = new HashMap<String,Object>();
			tmp.put("db_no", 3);
			tmp.put("uuid", s);
			tmp.put("type", 0);
			String identity_id = MD5Util.MD5(gclib.getOrg_id() + s);
			tmp.put("identity_id", identity_id);
			list.add(tmp);
		}
		String value = JSONObject.toJSONString(list);
		map.put("identity_ids", value);
		return map;
	}
}
