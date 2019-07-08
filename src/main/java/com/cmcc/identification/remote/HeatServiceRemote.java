package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.entity.feigin.Thermal;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="${remote.heat-service.name}", fallbackFactory = com.cmcc.identification.remote.FaceServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface HeatServiceRemote {
    
    @RequestMapping(value = "${remote.heat-service.heatcomputation}" ,method = RequestMethod.POST)
    String heatcomputation(@RequestBody Thermal thermal);
    
}
