package com.cmcc.identification.remote;

import feign.hystrix.FallbackFactory;

public class FaceServiceRemoteFeedback implements FallbackFactory<FaceServiceRemote> {

    @Override
    public FaceServiceRemote create(Throwable cause) {
        return new FaceServiceRemote() {
            @Override
            public String faceFeature(String image) {
                return null;
            }
        };
    }
}
