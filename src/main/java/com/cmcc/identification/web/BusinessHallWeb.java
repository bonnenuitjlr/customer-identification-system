package com.cmcc.identification.web;

import com.cmcc.identification.entity.BusinessHall;
import com.cmcc.identification.vo.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/business-hall")
public class BusinessHallWeb {

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

}
