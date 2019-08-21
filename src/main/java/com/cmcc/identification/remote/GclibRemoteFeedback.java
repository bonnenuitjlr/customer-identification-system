package com.cmcc.identification.remote;

import feign.hystrix.FallbackFactory;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GclibRemoteFeedback implements FallbackFactory<GclibRemote> {
    @Override
    public GclibRemote create(Throwable cause) {
        return new GclibRemote() {

			@Override
			public String featureStorage(Map<String, Object> requestMap) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String featureDelete(Map<String, Object> requestMap) {
				// TODO Auto-generated method stub
				return null;
			}
        };
    }
}
