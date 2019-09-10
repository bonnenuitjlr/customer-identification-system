package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.entity.feigin.CharacteristicsLibraryFeign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "${remote.gclib.name}", url = "${remote.gclib.url}", fallbackFactory = com.cmcc.identification.remote.GclibRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface GclibRemote {

    @RequestMapping(value = "${remote.gclib.fdb-portrait}", method = RequestMethod.POST)
    String fdbPortrait(@RequestBody CharacteristicsLibraryFeign characteristicsLibraryFeign);


    @RequestMapping(value = "${remote.gclib.store-person}", method = RequestMethod.DELETE)
    String storePersonDelete(@RequestParam("identity_ids") List identity_ids, String org_id);

}
