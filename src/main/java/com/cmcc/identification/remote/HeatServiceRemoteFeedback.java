package com.cmcc.identification.remote;

import com.cmcc.identification.entity.feigin.Thermal;
import feign.hystrix.FallbackFactory;

public class HeatServiceRemoteFeedback implements FallbackFactory<HeatServiceRemote> {
    @Override
    public HeatServiceRemote create(Throwable cause) {
        return new HeatServiceRemote() {
            @Override
            public String heatcomputation(Thermal thermal) {
                return null;
            }
        };
    }
}
