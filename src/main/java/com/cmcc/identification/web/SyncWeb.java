package com.cmcc.identification.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmcc.identification.entity.feigin.SyscEntity;
import com.cmcc.identification.remote.SyncServiceRemote;
import com.cmcc.identification.util.DateUtils;
import com.cmcc.identification.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/sync-service")
public class SyncWeb {

    @Autowired
    private SyncServiceRemote syncServiceRemote;

    //统计客流
    @GetMapping("statisticsPassengerFlow")
    public R statisticsPassengerFlow(@RequestParam("org_id") String org_id,
                                     @RequestParam("timerange") Long[] timerange,
                                     @RequestParam("prd_id") String prd_id,
                                     @RequestParam("cty_id") String cty_id) {
        R result = null;
        try {
            //1.调取客流分析接口获取数据
            String syncService = syncServiceRemote.syncVisitor(new SyscEntity(org_id, timerange, 24 * 3600, 0, 20));
            Map<String, Object> syncServiceMap = JSONObject.parseObject(syncService, new TypeReference<Map<String, Object>>() {
            });
            Integer code = (Integer) syncServiceMap.get("code");
            if (code != 200) {
                return R.ERROR(code, (String) syncServiceMap.get("error_msg"));
            }
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) syncServiceMap.get("data");
            //2.处理返回结果
            //访客量
            Integer visit_cnt = 0;
            //男性顾客数量
            Integer male = 0;
            //女性顾客数量
            Integer female = 0;
            //儿童顾客数量
            Integer children = 0;
            //青年顾客数量
            Integer teenager = 0;
            //中年顾客数量
            Integer middle_age = 0;
            //老年顾客数量
            Integer old_age = 0;
            for (Map<String, Object> data : dataList
            ) {
                Long date = (Long) data.get("date");
                //判断是否为当天数据
                boolean today = DateUtils.isToday(date);
                if (today) {
                    male = male + (Integer) data.get("male");
                    female = female + (Integer) data.get("female");
                    children = children + (Integer) data.get("children");
                    teenager = teenager + (Integer) data.get("teenager");
                    middle_age = middle_age + (Integer) data.get("middle_age");
                    old_age = old_age + (Integer) data.get("old_age");
                }
            }
            //计算男女比例
            Integer[] gender = minScale(male, female);
            //计算年龄比例
            Integer[] age = minScale(children, teenager, middle_age, old_age);

            result = R.OK();

        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //计算两个数的比例
    public static Integer[] minScale(int a, int b) {
        int tmp = a;
        if (a > b) {
            tmp = b;
        }
        for (int i = tmp; i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                Integer[] result = {a / i, b / i};
                return result;
            }
        }
        return null;
    }

    //计算四个数的比例
    public static Integer[] minScale(int a, int b, int c, int d) {
        int intArray[] = {a, b, c, d};
        int tmp = Arrays.stream(intArray).min().getAsInt();
        for (int i = tmp; i > 0; i--) {
            if (a % i == 0 && b % i == 0 && c % i == 0 && d % i == 0) {
                Integer[] result = {a / i, b / i, c / i, d / i};
                return result;
            }
        }
        return null;
    }
}
