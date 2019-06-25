package com.cmcc.identification.vo;

import lombok.Data;

/**
 * 用于特征库删除
 */
@Data
public class CharacteristicsLibraryVo {

    /**
     *用户的uuid
     */
    private String[] uuid;

    /**
     *营业厅ID
     */
    private String org_id;

    /**
     *5位省代码
     */
    private String prd_id;

    /**
     *区域id
     */
    private String cty_id;
    
}
