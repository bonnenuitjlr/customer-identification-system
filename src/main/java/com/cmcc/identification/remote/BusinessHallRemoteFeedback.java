package com.cmcc.identification.remote;

import java.util.Map;

import com.cmcc.identification.entity.User;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class BusinessHallRemoteFeedback implements FallbackFactory<BusinessHallRemote> {

    @Override
    public BusinessHallRemote create(Throwable cause) {
        return new BusinessHallRemote() {

            @Override
            public String businessHall(String username) {
                return null;
            }

            @Override
            public String businessHall1(User user) {
                return null;
            }

            @Override
            public String businessHall2(String name) {
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
