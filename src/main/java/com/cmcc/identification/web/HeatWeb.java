package com.cmcc.identification.web;

import com.cmcc.identification.cache.ThermalCacheManager;
import com.cmcc.identification.entity.ThermalSceneChart;
import com.cmcc.identification.vo.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heat-service")
public class HeatWeb {
	
	@Autowired
	private ThermalCacheManager thermalCacheManager;

    //查询热力图
	@GetMapping("thermalChart")
	public R thermalChart(@RequestParam("camera_id")String camera_id,
                        @RequestParam("org_id")String org_id,
                        @RequestParam("prd_id")String prd_id,
                        @RequestParam("cty_id")String cty_id,
                        @RequestParam("isfullview")String isfullview,
                        @RequestParam("timerange")String[] timerange) {
//      R result = null;
//      try {
//          Map<String,Object> map = new HashMap<String, Object>();
//          List<String> heats1 = new ArrayList<String>();
//          heats1.add("热力分布1");
//          heats1.add("热力分布2");
//          map.put("base64_snapshot","热力背景图");
//          map.put("heat",heats1);
//          result = R.OK(map);
//      } catch (Exception e) {
//          result = R.ERROR(500, e.toString());
//      }
//      return result;
		return thermalCacheManager.thermalChart(camera_id, org_id, prd_id, cty_id, isfullview, timerange);
  }
    
	//上报摄像头实景图
    @PostMapping("heatcomputation")
    public R heatcomputation(@RequestBody ThermalSceneChart thermalSceneChart) {
//        R result = null;
//        try {
//            result = R.OK("成功上报");
//        } catch (Exception e) {
//            result = R.ERROR(500, e.toString());
//        }
//        return result;
        return thermalCacheManager.heatcomputation(thermalSceneChart);
    }
}
