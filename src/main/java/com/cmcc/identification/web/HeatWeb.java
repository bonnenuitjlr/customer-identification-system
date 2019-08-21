package com.cmcc.identification.web;

import com.cmcc.identification.entity.ThermalMonitoring;
import com.cmcc.identification.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heat-service")
public class HeatWeb {

    //热力上报接口
    @PostMapping("heatcomputation")
    public R thermalMonitoring(@RequestBody ThermalMonitoring thermalMonitoring) {
        R result = null;
        try {
            result = R.OK("成功上报");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
//        return thermalCacheManager.heatAreaReport(thermalMonitoring);
    }
}
