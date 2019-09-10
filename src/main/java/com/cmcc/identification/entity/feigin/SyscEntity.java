package com.cmcc.identification.entity.feigin;

import lombok.Data;

@Data
public class SyscEntity {

    private String store_id;               //店铺id
    private Long[] time_range;             //起始时间和结束时间的时间戳 例如[1564588800，1565193600]
    private Integer time_interval;         //查询的时间的粒度，以秒为单位 若按小时查，则取值3600，默认为24 * 3600
    private Integer page_id;               //默认为1，从1开始计数.如果需要所有的数据，则赋值0
    private Integer page_size;             //每页的大小  默认20

    public SyscEntity() {
    }

    public SyscEntity(String store_id, Long[] time_range, Integer time_interval, Integer page_id, Integer page_size) {
        this.store_id = store_id;
        this.time_range = time_range;
        this.time_interval = time_interval;
        this.page_id = page_id;
        this.page_size = page_size;
    }
}
