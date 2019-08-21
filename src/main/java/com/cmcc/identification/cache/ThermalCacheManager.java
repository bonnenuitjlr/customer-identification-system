package com.cmcc.identification.cache;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.identification.entity.ThermalSceneChart;
import com.cmcc.identification.remote.HeatServiceRemote;
import com.cmcc.identification.util.ErrorConst;
import com.cmcc.identification.vo.R;
import com.cmcc.identification.vo.Response;

@Service
public class ThermalCacheManager {
	
	@Autowired
	private HeatServiceRemote heatServiceRemote;
	
	public R thermalChart(String camera_id, String org_id, String prd_id, 
			String cty_id, String isfullview, String[] timerange) {
		String qhc = heatServiceRemote.queryHeatChart(camera_id,org_id,isfullview,timerange);
		Response response = JSONObject.parseObject(qhc,Response.class);
		if(!StringUtils.isEmpty(response.getData()) && response.getCode() == 200) {
			return R.OK(response.getData(), response.getIsfullview());
		}
		return R.ERROR(ErrorConst.Q601, ErrorConst.getMessage(ErrorConst.Q601));
	}
	
	public R heatcomputation(ThermalSceneChart thermalSceneChart) {
		Map<String,Object> requestMap = thermalScene_transMap(thermalSceneChart); 
		String hc = heatServiceRemote.heatcomputation(requestMap);
		Response response = JSONObject.parseObject(hc,Response.class);
		if(response.getCode() == 200) {
			return R.OK(response.getData(), response.getIsfullview());
		}
		return R.ERROR(ErrorConst.R502, ErrorConst.getMessage(ErrorConst.R502));
	}
	
	private Map<String,Object> thermalScene_transMap(ThermalSceneChart thermal){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("camera_id", thermal.getCamera_id());
		map.put("org_id",thermal.getOrg_id());
		map.put("base64_image", Integer.valueOf(thermal.getBase64_image()));
		map.put("timestamp", thermal.getTimestamp());
		map.put("duration", thermal.getDuration());
		map.put("configuration", thermal.getConfiguration());
		return map;
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
