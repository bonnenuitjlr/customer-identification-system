package com.cmcc.identification.entity.feigin;

import lombok.Data;

@Data
public class Face {

    String mac_address;
    String base64_face;
    String timestamp;
    String report_tag;

    public Face() {
    }

    public Face(String mac_address, String base64_face, String timestamp, String report_tag) {
        this.mac_address = mac_address;
        this.timestamp = timestamp;
        this.base64_face = base64_face;
        this.report_tag = report_tag;
    }
}
