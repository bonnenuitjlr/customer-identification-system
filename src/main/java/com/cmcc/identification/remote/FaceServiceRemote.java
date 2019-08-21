package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.config.TokenAopConfig;
import com.cmcc.identification.entity.feigin.Face;
import com.cmcc.identification.token.TokenMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${remote.face-service.name}", url = "${remote.face-service.url}", fallbackFactory = com.cmcc.identification.remote.FaceServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface FaceServiceRemote {

    //通过人脸图片提取特征向量接口
    @RequestMapping(value = "${remote.face-service.face-feature}", method = RequestMethod.POST)
//    String faceFeature(@RequestParam("mac_address") String mac_address,@RequestParam("org_id") String org_id,@RequestParam("base64_face") String base64_face);
    String faceFeature(@RequestBody Face face);
}
