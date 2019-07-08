package com.cmcc.identification.remote;

import feign.hystrix.FallbackFactory;

public class SyncServiceRemoteFeedback implements FallbackFactory<SyncServiceRemote> {
    @Override
    public SyncServiceRemote create(Throwable cause) {
        return new SyncServiceRemote() {
            @Override
            public String syncVisitor(int visit_time, int age) {
                return null;
            }
        };
    }
}
