package com.cmcc.identification.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmcc.identification.helper.RedisHelper;
import com.cmcc.identification.remote.FaceServiceRemote;
import com.cmcc.identification.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/face-service")
public class FaceWeb {

    @Autowired
    private FaceServiceRemote faceServiceRemote;
    @Resource
    private RedisHelper redisHelper;

    //人脸抓拍图片上报
    @PostMapping("feature")
    public R faceImages(@RequestParam("mac_address") String mac_address,
                        @RequestParam("base64_face") String base64_face,
                        @RequestParam("base64_image") String base64_image,
                        @RequestParam("timestamp") String timestamp,
                        @RequestParam("org_id") String org_id,
                        @RequestParam("prd_id") String prd_id,
                        @RequestParam("cty_id") String cty_id,
                        @RequestParam("source") String source) {
        R result = null;
        try {
            //1.通过mac地址获取cameraId
            String cameraId = redisHelper.getCameraIdByMacAddress(mac_address);
            //2.通过org_id获取storeId
            String storeId = redisHelper.getStoreIdByOrgId(org_id);
            //3.通过系统时间获取report_tag
            String report_tag = cameraId + "_" + System.currentTimeMillis() / 1000 + "000000";
            //4调取接口获取返回结果
            String faceFeature = faceServiceRemote.faceFeature(mac_address, base64_face, timestamp, report_tag, mac_address, cameraId, storeId);
            Map<String, Object> faceFeatureMap = JSONObject.parseObject(faceFeature, new TypeReference<Map<String, Object>>() {
            });
            Integer code = (Integer) faceFeatureMap.get("code");
            //当返回错误信息时直接报错
            if (code != 200) {
                return R.ERROR(code, (String) faceFeatureMap.get("error_msg"));
            }
            Map<String, Object> data = (Map<String, Object>) faceFeatureMap.get("data");
            //5.处理返回结果
            result = R.OK(data);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

}
