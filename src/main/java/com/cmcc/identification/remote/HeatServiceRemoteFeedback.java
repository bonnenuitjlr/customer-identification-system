package com.cmcc.identification.remote;

import java.util.Map;

import org.springframework.stereotype.Component;

import feign.hystrix.FallbackFactory;

@Component
public class HeatServiceRemoteFeedback implements FallbackFactory<HeatServiceRemote> {
    @Override
    public HeatServiceRemote create(Throwable cause) {
        return new HeatServiceRemote() {

            @Override
            public String queryHeatChart(String org_id, String store_id, String attributes, String time_ranges,
                                         Integer floor_id) {
                // TODO Auto-generated method stub
                return null;
            }

            @Override
            public String heatcomputation(Map<String, Object> requestMap) {
                // TODO Auto-generated method stub
                return null;
            }

        };
    }
}
