package com.cmcc.identification.cache;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.cmcc.identification.entity.ThermalMonitoring;
import com.cmcc.identification.remote.BusinessHallRemote;
import com.cmcc.identification.util.ErrorConst;
import com.cmcc.identification.util.TransMapUtil;
import com.cmcc.identification.vo.R;
import com.cmcc.identification.vo.Response;

@Service
public class ThermalCacheManager {
	
	@Autowired
	private BusinessHallRemote businessHallRemote;
	
	public R heatAreaReport(ThermalMonitoring thermalMonitoring) {
		Map<String,Object> requestMap = TransMapUtil.object2Map(thermalMonitoring);
		String har = businessHallRemote.heatAreaReport(requestMap);
		Response response = JSONObject.parseObject(har,Response.class);
		if(!StringUtils.isEmpty(response.getData()) && response.getCode() == 200) {
			return R.OK(ErrorConst.SUCC, "成功上报");
		}
		return R.ERROR(ErrorConst.R501, ErrorConst.getMessage(ErrorConst.R501));
	}
	
	public R thermalChart(String camera_id, String org_id, String prd_id, 
			String cty_id, String isfullview, String[] timerange) {
		String qhc = businessHallRemote.queryHeatChart(camera_id,org_id,prd_id,cty_id,isfullview,timerange);
		Response response = JSONObject.parseObject(qhc,Response.class);
		if(!StringUtils.isEmpty(response.getData()) && response.getCode() == 200) {
			return R.OK(response.getData());
		}
		return R.ERROR(ErrorConst.Q601, ErrorConst.getMessage(ErrorConst.Q601));
	}

}
