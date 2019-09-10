package com.cmcc.identification.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmcc.identification.entity.FaceImages;
import com.cmcc.identification.entity.feigin.Face;
import com.cmcc.identification.remote.BusinessHallRemote;
import com.cmcc.identification.remote.FaceServiceRemote;
import com.cmcc.identification.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/face-service")
public class FaceWeb {

    @Autowired
    private FaceServiceRemote faceServiceRemote;

    //人脸抓拍图片上报
    @PostMapping("feature")
    public R faceImages(@RequestBody FaceImages faceImages) {
        R result = null;
        try {
            //1.调用人脸服务获取数据
            String faceFeature = faceServiceRemote.faceFeature(new Face(faceImages.getMac_address(), faceImages.getBase64_face(), faceImages.getTimestamp(), ""),
                    faceImages.getMac_address(),
                    "",
                    faceImages.getOrg_id());
            Map<String, Object> faceFeatureMap = JSONObject.parseObject(faceFeature, new TypeReference<Map<String, Object>>() {
            });
            Integer code = (Integer) faceFeatureMap.get("code");
            if (code != 200) {
                return R.ERROR(code, (String) faceFeatureMap.get("error_msg"));
            }
            Map<String, Object> data = (Map<String, Object>) faceFeatureMap.get("data");
            //2.处理返回结果
            result = R.OK(data);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

}
