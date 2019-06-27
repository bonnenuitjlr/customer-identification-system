package com.cmcc.identification.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient(name="mruleconfig", fallbackFactory = com.cmcc.identification.remote.MruleRemoteFeedback.class)
public interface MruleRemote {

    @RequestMapping(value = "${remote.mrule.checkRule}", method = RequestMethod.GET)
    String checkRule(@PathVariable("ruleId") String ruleId, @RequestParam(value = "ruleParam" ) String ruleParam);

    @RequestMapping(value="${remote.mrule.addOrUpdateComplexRule}",method=RequestMethod.POST)
    String addOrUpdateComplexRule(@RequestBody Map<String,Object> params ) ;
    
    @RequestMapping(value = "${remote.mrule.list}", method = RequestMethod.GET)
    String userLabelList(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize")Integer pageSize,@RequestParam(value = "searchString" ) String searchString);
    
}
