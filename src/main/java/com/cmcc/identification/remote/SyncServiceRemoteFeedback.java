package com.cmcc.identification.remote;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SyncServiceRemoteFeedback implements FallbackFactory<SyncServiceRemote> {
    @Override
    public SyncServiceRemote create(Throwable cause) {
        return new SyncServiceRemote() {
            @Override
            public String syncVisitor(String store_id, String time_range, int time_interval, int page_id, int page_size) {
                return null;
            }
        };
    }
}
