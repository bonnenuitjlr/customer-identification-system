package com.cmcc.identification.entity.feigin;

import lombok.Data;

@Data
public class Face {

    String mac_address;
    String org_id;
    String base64_face;

    public Face(){}

    public Face(String mac_address,String org_id,String base64_face){
        this.mac_address=mac_address;
        this.org_id=org_id;
        this.base64_face=base64_face;
    }

}
