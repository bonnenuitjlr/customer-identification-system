package com.cmcc.identification.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cmcc.identification.remote.HeatServiceRemote;
import com.cmcc.identification.util.RedisUtil;
import com.cmcc.identification.vo.R;
import com.cmcc.identification.vo.Response;
import com.cmcc.identification.vo.ThermalSceneChartVo;

@Service
public class ThermalCacheManager {
	
	@Autowired
	private HeatServiceRemote heatServiceRemote;
	
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
	
	public R thermalChart(String mac_address, String org_id, String prd_id, 
			String cty_id, String isfullview, String[] timerange) {
//		(@RequestParam("org_id") String org_id,@RequestParam("store_id") String store_id, 
//	    		@RequestParam("attributes") String attributes, @RequestParam("time_ranges") String time_ranges,
//	    		@RequestParam("floor_id") Integer floor_id)
		String store_id = getStoreIdByOrgId(org_id);
		String attributes = "";
		if (isfullview == "0") {
			List<String> tmp = new ArrayList<String>();
			tmp.add("heat_map");
			attributes = JSON.toJSONString(tmp);
		}else {
			List<String> tmp = new ArrayList<String>();
			tmp.add("heat_map_origin_g_camera");
			attributes = JSON.toJSONString(tmp);
		}
		String time_ranges = parseTimeRange(timerange);
		Integer floor_id = new Integer(0);
		
		String qhc = heatServiceRemote.queryHeatChart(org_id,store_id,attributes,time_ranges,floor_id);
		// 返回数据解析
		Response response = JSONObject.parseObject(qhc,Response.class);
		if(!StringUtils.isEmpty(response.getData()) && response.getCode() == 200) {
			@SuppressWarnings("unchecked")
			Map<String,Object> data = (Map<String,Object>)response.getData();
			Object resData = null;
			String image = "";													// 只有查询非全景图像时才有值
			if(isfullview == "0") {
				resData = data.get("heat_map");
			}else {
				resData = data.get("heat_map_origin_g_camera");
				String camera_id = redisUtil.hGet(mac_key, mac_address);
				image = redisUtil.hGet(mac_key_image, camera_id);
			}
			Map<String,Object> returnData = new HashMap<String,Object>();		// 构造返回数据样式
			returnData.put("heat", resData);
			returnData.put("base64_snapshot", image);
			return R.OK(returnData);
		}
		return R.ERROR(response.getCode(), response.getMsg());
	}
	
	public R heatcomputation(ThermalSceneChartVo thermalSceneChartVo) {
		Map<String,Object> requestMap = thermalScene_transMap(thermalSceneChartVo); 
		String hc = heatServiceRemote.heatcomputation(requestMap);
		Response response = JSONObject.parseObject(hc,Response.class);
		if(response.getCode() == 200) {
			return R.OK(response.getData());
		}
		return R.ERROR(response.getCode(), response.getMsg());
	}
	
	private Map<String,Object> thermalScene_transMap(ThermalSceneChartVo thermal){
		Map<String,Object> map = new HashMap<String,Object>();
		String mac_address = thermal.getMac_address();		
		String camera_id = getCameraIdByMacAddress(mac_address);					// 根据mac_address 获取摄像头id camera_id
		String base64_image = thermal.getBase64_image();
		redisUtil.hSet(mac_key_image, camera_id, base64_image);						// 把该摄像头最新传入的图片存入redis
		
		map.put("camera_id", camera_id);
		map.put("org_id",thermal.getOrg_id());
		map.put("base64_image", base64_image);
		map.put("timestamp", thermal.getTimestamp());
		map.put("duration", thermal.getDuration());
		map.put("configuration", thermal.getConfiguration());
		return map;
	}
	
	/*
	 *  把mac_address转化为摄像头id，并维护mac集合
	 */
	private synchronized String getCameraIdByMacAddress(String mac_address) {
		
		boolean flag = redisUtil.hExists(mac_key, mac_address);
		if(flag) {
			return redisUtil.hGet(mac_key, mac_address);
		}else {
			long tmpMax = redisUtil.incr(mac_key_max);
			redisUtil.hSet(mac_key, mac_address, String.valueOf(tmpMax));
			return String.valueOf(tmpMax);
		}
	}
	
	/*
	 *  把org_id转化为store_id，并维护店铺id集合
	 */
	private synchronized String getStoreIdByOrgId(String org_id) {
		
		boolean flag = redisUtil.hExists(org_key, org_id);
		if(flag) {
			return redisUtil.hGet(org_key, org_id);
		}else {
			long tmpMax = redisUtil.incr(org_key_max);
			redisUtil.hSet(org_key, org_id, String.valueOf(tmpMax));
			return String.valueOf(tmpMax);
		}
	}
	
	/*
	 * 格式化时间范围
	 */
	private String parseTimeRange(String[] timerange) {
		List<List<String>> result = new ArrayList<>();
		for(int i = 0; i<timerange.length; i+=2) {
			List<String> tmprange = new ArrayList<String>();
			tmprange.add(timerange[i]);
			tmprange.add(timerange[i+1]);
			result.add(tmprange);
		}
		return JSON.toJSONString(result);
	}
//	
//	public R heatAreaReport(ThermalMonitoring thermalMonitoring) {
//		Map<String,Object> requestMap = TransMapUtil.object2Map(thermalMonitoring);
//		String har = heatServiceRemote.heatAreaReport(requestMap);
//		Response response = JSONObject.parseObject(har,Response.class);
//		if(!StringUtils.isEmpty(response.getData()) && response.getCode() == 200) {
//			return R.OK(ErrorConst.SUCC, "成功上报");
//		}
//		return R.ERROR(ErrorConst.R501, ErrorConst.getMessage(ErrorConst.R501));
//	}

}
