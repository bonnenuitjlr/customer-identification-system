package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.entity.feigin.CharacteristicsLibraryFeign;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name="${remote.gclib.name}", fallbackFactory = com.cmcc.identification.remote.FaceServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface GclibRemote {

    @RequestMapping(value = "${remote.gclib.fdb-portrait}" ,method = RequestMethod.POST)
    String fdbPortrait(@RequestBody CharacteristicsLibraryFeign characteristicsLibraryFeign);

    @RequestMapping(value = "${remote.gclib.store-person}" ,method = RequestMethod.PUT)
    String storePersonPut(@RequestBody List identity_ids, String org_id);

    @RequestMapping(value = "${remote.gclib.store-person}" ,method = RequestMethod.DELETE)
    String storePersonDelete(@RequestBody List identity_ids, String org_id);

    @RequestMapping(value = "${remote.gclib.store-person-reset}" ,method = RequestMethod.DELETE)
    String storePersonReset(@RequestBody String org_id);

    @RequestMapping(value = "${remote.gclib.store-person-search}" ,method = RequestMethod.POST)
    String storePersonSearch(@RequestBody String org_id,String user_pic);
    
}
