package com.cmcc.identification.remote;

import java.util.Map;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MruleRemoteFeedback implements FallbackFactory<MruleRemote> {

    @Override
    public MruleRemote create(Throwable cause) {
        return new MruleRemote() {
            @Override
            public String checkRule(String ruleId, String ruleParam) {
                return null;
            }

			@Override
			public String addOrUpdateComplexRule(Map<String, Object> params) {
				return null;
			}

			@Override
			public String userLabelList(Integer pageNum, Integer pageSize, String searchString) {
				return null;
			}


        };
    }
}
