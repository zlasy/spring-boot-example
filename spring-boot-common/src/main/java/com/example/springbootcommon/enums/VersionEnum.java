package com.example.springbootcommon.enums;

import lombok.Getter;

@Getter
public enum VersionEnum {

    VERSION_1_0("1.0","上线第一版"),
    VERSION_1_1("1.1","兼容登录sessionId超时版"),
    VERSION_1_2("1.2","转人工增加分流反馈");

    private VersionEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }
    /** 枚举值码 */
    private String code;

    /** 枚举描述 */
    private String description;

    public boolean equalTo(String v){
        return this.code.equals(v);
    }
}
