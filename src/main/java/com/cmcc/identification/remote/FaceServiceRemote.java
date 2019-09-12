package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "${remote.face-service.name}", url = "${remote.face-service.url}", fallbackFactory = com.cmcc.identification.remote.FaceServiceRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface FaceServiceRemote {

    //智能抓拍摄像头人脸上报
    @RequestMapping(value = "${remote.face-service.face-feature}", method = RequestMethod.POST)
    String faceFeature(@RequestParam("mac") String mac,
                       @RequestParam("base64_face") String base64_face,
                       @RequestParam("timestamp") String timestamp,
                       @RequestParam("report_tag") String report_tag,
                       @RequestHeader(name = "Camera-Mac", required = true) String cameraMac,
                       @RequestHeader(name = "Camera-Id", required = true) String camera_id,
                       @RequestHeader(name = "Store-Id", required = true) String store_id);

}
