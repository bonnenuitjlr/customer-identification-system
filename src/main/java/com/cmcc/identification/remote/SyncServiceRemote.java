package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name="${remote.face-service.name}", fallbackFactory = com.cmcc.identification.remote.FaceServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface SyncServiceRemote {

    @RequestMapping(value = "${remote.face-service.face-feature}" ,method = RequestMethod.POST)
    String syncVisitor(@RequestBody int visit_time,int age);
}