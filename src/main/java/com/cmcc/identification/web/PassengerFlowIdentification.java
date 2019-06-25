package com.cmcc.identification.web;

import com.cmcc.identification.entity.*;
import com.cmcc.identification.vo.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/passenger-flow-identification")
public class PassengerFlowIdentification {

    //人脸抓拍图片上报
    @PostMapping("faceImages")
    public R faceImages(@RequestBody FaceImages faceImages) {
        R result = null;
        try {
            result = R.OK("成功上报"+faceImages.toString());
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
            result = R.OK("成功上报"+characteristicsLibrary.toString());
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //特征库删除
    @DeleteMapping("characteristicsLibrary")
    public R deletedCharacteristicsLibrary(@RequestBody CharacteristicsLibrary characteristicsLibrary) {
        R result = null;
        try {
            result = R.OK("删除成功"+characteristicsLibrary.toString());
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
            result = R.OK("清库成功"+characteristicsLibrary.toString());
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
            result = R.OK("成功上报"+identifyResults.toString());
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
            result = R.OK("realTimePassengerFlow"+"camera_id:{"+camera_id+"};org_id:{"+org_id+"};prd_id:{"+prd_id+"};cty_id:{"+cty_id+"}");
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
    //统计客流
    @GetMapping("statisticsPassengerFlow")
    public R statisticsPassengerFlow(@RequestParam("org_id")String org_id,
                             @RequestParam("timerange")String timerange,
                             @RequestParam("prd_id")String prd_id,
                             @RequestParam("cty_id")String cty_id) {
        R result = null;
        try {
            result = R.OK("statisticsPassengerFlow"+"org_id:{"+org_id+"};timerange:{"+timerange+"};prd_id:{"+prd_id+"};cty_id:{"+cty_id+"}");
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
            result = R.OK("成功上报"+customerInfo.toString());
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
            result = R.OK("成功上报"+frequenter.toString());
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }
    
}
