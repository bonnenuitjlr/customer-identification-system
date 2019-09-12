package com.cmcc.identification.web;

import com.cmcc.identification.cache.ThermalCacheManager;
import com.cmcc.identification.vo.R;
import com.cmcc.identification.vo.ThermalSceneChartVo;

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
    public R thermalChart(@RequestParam("mac_address") String mac_address,
                          @RequestParam("org_id") String org_id,
                          @RequestParam("prd_id") String prd_id,
                          @RequestParam("cty_id") String cty_id,
                          @RequestParam("isfullview") String isfullview,
                          @RequestParam("timerange") String[] timerange) {
        return thermalCacheManager.thermalChart(mac_address, org_id, prd_id, cty_id, isfullview, timerange);
    }

    //上报摄像头实景图
    @PostMapping("heatcomputation")
    public R heatcomputation(@RequestBody ThermalSceneChartVo thermalSceneChartVo) {
        return thermalCacheManager.heatcomputation(thermalSceneChartVo);
    }
}
