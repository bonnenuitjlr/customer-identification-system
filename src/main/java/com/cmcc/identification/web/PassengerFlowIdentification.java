package com.cmcc.identification.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmcc.identification.entity.*;
import com.cmcc.identification.entity.feigin.Face;
import com.cmcc.identification.remote.FaceServiceRemote;
import com.cmcc.identification.vo.CharacteristicsLibraryVo;
import com.cmcc.identification.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/passenger-flow-identification")
public class PassengerFlowIdentification {

    @Autowired
    private FaceServiceRemote faceServiceRemote;
    //人脸抓拍图片上报
    @PostMapping("faceImages")
    public R faceImages(@RequestBody FaceImages faceImages) {
        R result = null;
        try {
            //1.调用人脸服务获取数据
            String faceFeature = faceServiceRemote.faceFeature(new Face(faceImages.getMac_address(),faceImages.getOrg_id(),faceImages.getBase64_face()));
            Map<String, Object> faceFeatureMap = JSONObject.parseObject(faceFeature, new TypeReference<Map<String, Object>>() {});
            Integer code = (Integer) faceFeatureMap.get("code");
            if(code!=200){
                return R.ERROR(code,(String)faceFeatureMap.get("msg"));
            }
            Map<String, Object> data = (Map<String, Object>) faceFeatureMap.get("data");

            //2.处理返回结果
            result = R.OK(data);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //特征库入库
    @PostMapping("characteristicsLibrary")
    public R addCharacteristicsLibrary(@RequestBody CharacteristicsLibrary characteristicsLibrary) {
        R result = null;
        try {
            result = R.OK("成功上报");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //特征库删除
    @DeleteMapping("characteristicsLibrary")
    public R deletedCharacteristicsLibrary(@RequestBody CharacteristicsLibraryVo characteristicsLibraryVo) {
        R result = null;
        try {
            List<String> list = new ArrayList<String>();
            for(String uuid:characteristicsLibraryVo.getUuid()){
                list.add(uuid);
            }
            result = R.OK(list);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //特征库清库
    @DeleteMapping("allCharacteristicsLibrary")
    public R deletedAllCharacteristicsLibrary(@RequestBody CharacteristicsLibrary characteristicsLibrary) {
        R result = null;
        try {
            result = R.OK("清库成功");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
    //识别结果上报（运营分析系统侧）
    @PostMapping("identifyResults")
    public R identifyResults(@RequestBody IdentifyResults identifyResults) {
        R result = null;
        try {
            result = R.OK("成功上报");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
    //实时客流
    @GetMapping("realTimePassengerFlow")
    public R realTimePassengerFlow(@RequestParam("camera_id")String camera_id,
                             @RequestParam("org_id")String org_id,
                             @RequestParam("prd_id")String prd_id,
                             @RequestParam("cty_id")String cty_id) {
        R result = null;
        try {
            result = R.OK("10");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
    //统计客流
    @GetMapping("statisticsPassengerFlow")
    public R statisticsPassengerFlow(@RequestParam("org_id")String org_id,
                             @RequestParam("timerange")String[] timerange,
                             @RequestParam("prd_id")String prd_id,
                             @RequestParam("cty_id")String cty_id) {
        R result = null;
        try {
            Map<String,Object> map = new HashMap<String, Object>();
            List<String> totals = new ArrayList<String>();
            List<String> genders = new ArrayList<String>();
            List<String> ages = new ArrayList<String>();
            totals.add("10");
            totals.add("200");
            genders.add("1");
            genders.add("2");
            ages.add("18");
            ages.add("29");
            ages.add("39");
            ages.add("64");
            ages.add("65");
            map.put("total",totals);
            map.put("gender",genders);
            map.put("age",ages);
            result = R.OK(map);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
    //客户图像摘要信息
    @PostMapping("customerInfo")
    public R customerInfo(@RequestBody CustomerInfo customerInfo) {
        R result = null;
        try {
            result = R.OK("成功上报");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //常客 上报潜在的到厅用户
    @PostMapping("frequenter")
    public R frequenter(@RequestBody Frequenter frequenter) {
        R result = null;
        try {
            result = R.OK("成功上报");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
}
