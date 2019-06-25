package com.cmcc.identification.web;

import com.cmcc.identification.entity.FaceImages;
import com.cmcc.identification.entity.ThermalCamera;
import com.cmcc.identification.entity.ThermalMonitoring;
import com.cmcc.identification.vo.R;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/thermal-analysis")
public class ThermalAnalysis {

    //热力监控区域上报
    @PostMapping("thermalMonitoring")
    public R thermalMonitoring(@RequestBody ThermalMonitoring thermalMonitoring) {
        R result = null;
        try {
            result = R.OK("成功上报");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

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
    }
    
}
