package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.entity.feigin.Face;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${remote.face-service.name}", url = "${remote.face-service.url}", fallbackFactory = com.cmcc.identification.remote.FaceServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface FaceServiceRemote {

    //智能抓拍摄像头人脸上报
    @RequestMapping(value = "${remote.face-service.face-feature}", method = RequestMethod.POST)
    String faceFeature(@RequestBody Face face,
                       @RequestHeader(name = "Camera-Mac", required = true) String cameraMac,
                       @RequestHeader(name = "Camera-Id", required = true) String cameraId,
                       @RequestHeader(name = "Store-Id", required = true) String storeId);

}
