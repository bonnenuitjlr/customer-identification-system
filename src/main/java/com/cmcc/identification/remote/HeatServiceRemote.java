package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.entity.feigin.Thermal;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="${remote.heat-service.name}", fallbackFactory = com.cmcc.identification.remote.HeatServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface HeatServiceRemote {
    
    @RequestMapping(value = "${remote.heat-service.heatcomputation}" ,method = RequestMethod.POST)
    String heatcomputation(@RequestBody Thermal thermal);
    
    
    /**
     * 热力区域上报
     * @param requestMap ThermalMonitoring 类中的相关参数
     * @return 
     */
    @RequestMapping(value = "${remote.heat-service.heatAreaReport}", method = RequestMethod.POST)
    public String heatAreaReport(@RequestParam(required = false) Map<String, Object> requestMap);
    
    /**
     * 查询热力图
     * @param camera_id 摄像头id
     * @param org_id 店铺id
     * @param prd_id 5位省代码
     * @param cty_id 区域id
     * @param isfullview 全景实景热力标识
     * @param timerange 时间范围  字符串组
     * @return
     */
    @RequestMapping(value = "${remote.heat-service.queryHeatChart}", method = RequestMethod.GET)
    public String queryHeatChart(@RequestParam("camera_id") String camera_id, @RequestParam("org_id") String org_id, @RequestParam("prd_id") String prd_id, 
    		@RequestParam("cty_id") String cty_id, @RequestParam("isfullview") String isfullview, @RequestParam("timerange") String[] timerange);
}
