package com.cmcc.identification.remote;

import com.cmcc.identification.config.FeignConfiguration;
import com.cmcc.identification.entity.feigin.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@FeignClient(name="${remote.businessHall.name}", fallbackFactory = com.cmcc.identification.remote.BusinessHallRemoteFeedback.class, configuration = FeignConfiguration.class)
public interface BusinessHallRemote {

    @RequestMapping(value = "${remote.businessHall.businessHall}", method = RequestMethod.GET)
    String businessHall(@PathVariable("name") String name);
    
    @RequestMapping(value = "${remote.businessHall.businessHall1}" ,method = RequestMethod.POST)
    String businessHall1(@RequestBody User user);

    @RequestMapping(value = "${remote.businessHall.businessHall2}" ,method = RequestMethod.DELETE)
    String businessHall2(@PathVariable("name") String name);
    
    @RequestMapping(value="${remote.mrule.addOrUpdateComplexRule}",method=RequestMethod.POST)
    String addOrUpdateComplexRule(@RequestBody Map<String,Object> params ) ;

    @RequestMapping(value = "${remote.mrule.list}", method = RequestMethod.GET)
    String userLabelList(@PathVariable("pageNum") Integer pageNum,@PathVariable("pageSize")Integer pageSize,@RequestParam(value = "searchString" ) String searchString);
    
}
