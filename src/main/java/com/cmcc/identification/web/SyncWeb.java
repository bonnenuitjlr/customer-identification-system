package com.cmcc.identification.web;

import com.cmcc.identification.vo.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sync-service")
public class SyncWeb {

    //查询热力图
    @GetMapping("thermalChart")
    public R thermalChart(@RequestParam("camera_id")String camera_id,
                          @RequestParam("org_id")String org_id,
                          @RequestParam("prd_id")String prd_id,
                          @RequestParam("cty_id")String cty_id,
                          @RequestParam("isfullview")String isfullview,
                          @RequestParam("timerange")String[] timerange) {
        R result = null;
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            List<String> heats1 = new ArrayList<String>();
            heats1.add("热力分布1");
            heats1.add("热力分布2");
            map.put("base64_snapshot","热力背景图");
            map.put("heat",heats1);
            result = R.OK(map);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
//        return thermalCacheManager.thermalChart(camera_id, org_id, prd_id, cty_id, isfullview, timerange);
    }

    //实时客流
    @GetMapping("realTimePassengerFlow")
    public R realTimePassengerFlow(@RequestParam("camera_id")String camera_id,
                                   @RequestParam("org_id")String org_id,
                                   @RequestParam("prd_id")String prd_id,
                                   @RequestParam("cty_id")String cty_id) {
        R result = null;
        try {
            result = R.OK("10");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //统计客流
    @GetMapping("statisticsPassengerFlow")
    public R statisticsPassengerFlow(@RequestParam("org_id")String org_id,
                                     @RequestParam("timerange")String[] timerange,
                                     @RequestParam("prd_id")String prd_id,
                                     @RequestParam("cty_id")String cty_id) {
        R result = null;
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            List<String> totals = new ArrayList<String>();
            List<String> genders = new ArrayList<String>();
            List<String> ages = new ArrayList<String>();
            totals.add("10");
            totals.add("200");
            genders.add("1");
            genders.add("2");
            ages.add("18");
            ages.add("29");
            ages.add("39");
            ages.add("64");
            ages.add("65");
            map.put("total",totals);
            map.put("gender",genders);
            map.put("age",ages);
            result = R.OK(map);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
}
