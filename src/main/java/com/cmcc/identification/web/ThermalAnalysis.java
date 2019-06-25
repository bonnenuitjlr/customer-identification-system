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
            result = R.OK("成功上报"+thermalMonitoring.toString());
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
                          @RequestParam("timerange")String timerange) {
        R result = null;
        try {
            result = R.OK("10"+"camera_id:{"+camera_id+"};org_id:{"+org_id+"};prd_id:{"+prd_id+"};cty_id:{"+cty_id+"};isfullview:{"+isfullview+"};timerange:{"+timerange+"}");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
}
