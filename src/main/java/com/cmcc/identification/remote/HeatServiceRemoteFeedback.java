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
			public String heatAreaReport(Map<String, Object> requestMap) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public String queryHeatChart(String camera_id, String org_id, String isfullview, String[] timerange) {
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
