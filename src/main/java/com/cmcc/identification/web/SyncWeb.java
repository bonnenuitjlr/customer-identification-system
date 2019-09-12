package com.cmcc.identification.web;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.cmcc.identification.helper.RedisHelper;
import com.cmcc.identification.remote.SyncServiceRemote;
import com.cmcc.identification.vo.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("/sync-service")
public class SyncWeb {

    @Autowired
    private SyncServiceRemote syncServiceRemote;
    @Resource
    private RedisHelper redisHelper;

    //统计客流
    @GetMapping("statisticsPassengerFlow")
    public R statisticsPassengerFlow(@RequestParam("org_id") String org_id,
                                     @RequestParam("timerange") String timerange,
                                     @RequestParam("prd_id") String prd_id,
                                     @RequestParam("cty_id") String cty_id) {
        R result = null;
        try {
            //1.通过org_id获取storeId
            String storeId = redisHelper.getStoreIdByOrgId(org_id);
            //2.调取客流分析接口获取数据
            String syncService = syncServiceRemote.syncVisitor(storeId, timerange, 24 * 3600, 0, 20);
            Map<String, Object> syncServiceMap = JSONObject.parseObject(syncService, new TypeReference<Map<String, Object>>() {
            });
            Integer code = (Integer) syncServiceMap.get("code");
            //当返回错误信息时直接报错
            if (code != 200) {
                return R.ERROR(code, (String) syncServiceMap.get("error_msg"));
            }
            //3.处理返回结果
            //获取data中的数据
            List<Map<String, Object>> dataList = (List<Map<String, Object>>) syncServiceMap.get("data");
            //用于存储每个时间粒子的访客量
            List<Integer> visitCnt1List = new ArrayList<Integer>();
            List<Object> genderList = new ArrayList<Object>();
            List<Object> ageList = new ArrayList<Object>();
            //遍历data数据
            for (Map<String, Object> data : dataList
            ) {
                //访客量
                Integer visitCnt1 = (Integer) data.get("visit_cnt");
                visitCnt1List.add(visitCnt1);
                //男性顾客数量
                Integer male = (Integer) data.get("male");
                //女性顾客数量
                Integer female = (Integer) data.get("female");
                Map<String, Object> genderMap = minScale(male, female);
                genderList.add(genderMap);
                //儿童顾客数量
                Integer children = (Integer) data.get("children");
                //青年顾客数量
                Integer teenager = (Integer) data.get("teenager");
                //中年顾客数量
                Integer middle_age = (Integer) data.get("middle_age");
                //老年顾客数量
                Integer old_age = (Integer) data.get("old_age");
                Map<String, Object> ageMap = minScale(children, teenager, middle_age, old_age);
                ageList.add(ageMap);

            }
            Collections.reverse(visitCnt1List);
            Object[] total = visitCnt1List.toArray();
            Collections.reverse(genderList);
            Object[] gender = genderList.toArray();
            Collections.reverse(ageList);
            Object[] age = ageList.toArray();
            //对数据结果进行封装
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("total", total);
            map.put("gender", gender);
            map.put("age", age);
            result = R.OK(map);
        } catch (Exception e) {
            result = R.ERROR(500, e.toString());
        }
        return result;
    }

    //计算两个数的比例
    public static Map<String, Object> minScale(int a, int b) {
        Map<String, Object> map = new HashMap<String, Object>();
        int tmp = a;
        if (a > b) {
            tmp = b;
        }
        for (int i = tmp; i > 0; i--) {
            if (a % i == 0 && b % i == 0) {
                map.put("0", a / i);
                map.put("1", b / i);
                return map;
            }
        }
        if (map == null) {
            map.put("0", a);
            map.put("1", b);
        }
        return map;
    }

    //计算四个数的比例
    public static Map<String, Object> minScale(int a, int b, int c, int d) {
        Map<String, Object> map = new HashMap<String, Object>();
        int intArray[] = {a, b, c, d};
        int tmp = Arrays.stream(intArray).min().getAsInt();
        for (int i = tmp; i > 0; i--) {
            if (a % i == 0 && b % i == 0 && c % i == 0 && d % i == 0) {
                map.put("0", a / i);
                map.put("1", b / i);
                map.put("2", c / i);
                map.put("3", d / i);
                return map;
            }
        }
        if (map == null) {
            map.put("0", a);
            map.put("1", b);
            map.put("2", c);
            map.put("3", d);
        }
        return map;
    }
}
