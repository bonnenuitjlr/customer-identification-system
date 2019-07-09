package com.cmcc.identification.remote;

import com.cmcc.identification.entity.feigin.CharacteristicsLibraryFeign;
import feign.hystrix.FallbackFactory;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class GclibRemoteFeedback implements FallbackFactory<GclibRemote> {
    @Override
    public GclibRemote create(Throwable cause) {
        return new GclibRemote() {
            @Override
            public String fdbPortrait(CharacteristicsLibraryFeign characteristicsLibraryFeign) {
                return null;
            }

            @Override
            public String storePersonPut(List identity_ids, String org_id) {
                return null;
            }

            @Override
            public String storePersonDelete(List identity_ids, String org_id) {
                return null;
            }

            @Override
            public String storePersonReset(String org_id) {
                return null;
            }

            @Override
            public String storePersonSearch(String org_id, String user_pic) {
                return null;
            }
        };
    }
}
