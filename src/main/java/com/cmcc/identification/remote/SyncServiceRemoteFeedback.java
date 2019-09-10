package com.cmcc.identification.remote;

import com.cmcc.identification.entity.feigin.SyscEntity;
import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class SyncServiceRemoteFeedback implements FallbackFactory<SyncServiceRemote> {
    @Override
    public SyncServiceRemote create(Throwable cause) {
        return new SyncServiceRemote() {
            @Override
            public String syncVisitor(SyscEntity syscEntity) {
                return null;
            }
        };
    }
}
