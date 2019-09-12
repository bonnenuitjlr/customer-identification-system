package com.cmcc.identification.remote;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class FaceServiceRemoteFeedback implements FallbackFactory<FaceServiceRemote> {

    @Override
    public FaceServiceRemote create(Throwable cause) {
        return new FaceServiceRemote() {
            @Override
            public String faceFeature(String mac, String base64_face, String timestamp, String report_tag, String cameraMac, String camera_id, String store_id) {
                return null;
            }
        };
    }
}
