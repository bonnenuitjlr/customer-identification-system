package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="${remote.gclib.name}",url = "${remote.gclib.url}", fallbackFactory = com.cmcc.identification.remote.GclibRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface GclibRemote {

	/**
     * 特征库入库
     * @param requestMap CharacteristicsLibrary 传到底库的相关参数
     * @return 
     */
    @RequestMapping(value = "${remote.gclib.fdb-portrait}", method = RequestMethod.POST)
    public String featureStorage(@RequestParam(required = false) Map<String, Object> requestMap);
    
    /**
     * 特征库删除
     * @param requestMap CharacteristicsLibraryVo 传到底库的相关参数
     * @return 
     */
    @RequestMapping(value = "${remote.gclib.store-person}", method = RequestMethod.DELETE)
    public String featureDelete(@RequestParam(required = false) Map<String, Object> requestMap);
    
}
