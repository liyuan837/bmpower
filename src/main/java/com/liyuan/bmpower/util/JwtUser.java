package com.liyuan.bmpower.util;

import lombok.Data;

import java.io.Serializable;

@Data
public class JwtUser implements Serializable {
    private static final long serialVersionUID = 4221700752972709820L;
    private Integer id;
    private String loginName;
    private String userCode;
}