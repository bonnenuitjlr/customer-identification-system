package com.cmcc.identification.web;

import com.cmcc.identification.entity.BusinessHall;
import com.cmcc.identification.entity.ThermalCamera;
import com.cmcc.identification.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/business-hall")
public class BusinessHallManagement {

    //厅店信息更新
    @PostMapping("businessHall")
    public R businessHall(@RequestBody BusinessHall businessHall) {
        R result = null;
        try {
            result = R.OK("上传成功"+businessHall.toString());
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //热力摄像头配置请求
    @PostMapping("thermalCamera")
    public R thermalCamera(@RequestBody ThermalCamera thermalCamera) {
        R result = null;
        try {
            Map<String,Object> map = new HashMap<String,Object>();
            //假数据
            List<Object> camera_position = new ArrayList<>();
            camera_position.add("p1");
            camera_position.add("p2");
            camera_position.add("p3");
            camera_position.add("p4");
            camera_position.add("c1");
            camera_position.add("c2");
            camera_position.add("c3");
            camera_position.add("c4");
            List<Object> store_size = new ArrayList<>();
            store_size.add("长");
            store_size.add("宽");
            map.put("camera_position",camera_position);
            map.put("store_size",store_size);
            map.put("request",thermalCamera.toString());
            result = R.OK(map);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
        
    }

}
