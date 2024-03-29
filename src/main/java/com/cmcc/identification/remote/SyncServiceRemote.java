package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${remote.sync-service.name}", url = "${remote.sync-service.url}", fallbackFactory = com.cmcc.identification.remote.SyncServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface SyncServiceRemote {

    @RequestMapping(value = "${remote.sync-service.sync-visitor}", method = RequestMethod.GET)
    String syncVisitor(@RequestParam("store_id") String store_id,
                       @RequestParam("time_range") String time_range,
                       @RequestParam("time_interval") int time_interval,
                       @RequestParam("page_id") int page_id,
                       @RequestParam("page_size") int page_size);

}
