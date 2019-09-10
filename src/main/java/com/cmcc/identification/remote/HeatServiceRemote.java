package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;

import java.util.Map;

import com.cmcc.identification.entity.feigin.Thermal;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${remote.heat-service.name}", fallbackFactory = com.cmcc.identification.remote.HeatServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface HeatServiceRemote {
    /**
     * 热力区域上报
     *
     * @param requestMap ThermalMonitoring 类中的相关参数
     * @return
     */
    @RequestMapping(value = "${remote.heat-service.heatAreaReport}", method = RequestMethod.POST)
    public String heatAreaReport(@RequestParam(required = false) Map<String, Object> requestMap);


    /**
     * 查询热力图
     *
     * @param org_id
     * @param store_id
     * @param attributes
     * @param time_ranges
     * @param floor_id
     * @return
     */
    @RequestMapping(value = "${remote.heat-service.queryHeatChart}", method = RequestMethod.GET)
    public String queryHeatChart(@RequestParam("org_id") String org_id, @RequestParam("store_id") String store_id,
                                 @RequestParam("attributes") String attributes, @RequestParam("time_ranges") String time_ranges,
                                 @RequestParam("floor_id") Integer floor_id);


    @RequestMapping(value = "${remote.heat-service.heatcomputation}", method = RequestMethod.POST)
    public String heatcomputation(@RequestParam(required = false) Map<String, Object> requestMap);
}
