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
    
    /**
     * 热力区域上报
     * @param requestMap ThermalMonitoring 类中的相关参数
     * @return 
     */
    @RequestMapping(value = "${remote.businessHall.heatAreaReport}", method = RequestMethod.POST)
    public String heatAreaReport(@RequestParam(required = false) Map<String, Object> requestMap);
    
    /**
     * 查询热力图
     * @param camera_id 摄像头id
     * @param org_id 店铺id
     * @param prd_id 5位省代码
     * @param cty_id 区域id
     * @param isfullview 全景实景热力标识
     * @param timerange 时间范围  字符串组
     * @return
     */
    @RequestMapping(value = "${remote.businessHall.queryHeatChart}", method = RequestMethod.GET)
    public String queryHeatChart(@RequestParam("camera_id") String camera_id, @RequestParam("org_id") String org_id, @RequestParam("prd_id") String prd_id, 
    		@RequestParam("cty_id") String cty_id, @RequestParam("isfullview") String isfullview, @RequestParam("timerange") String[] timerange);
    
    
}
