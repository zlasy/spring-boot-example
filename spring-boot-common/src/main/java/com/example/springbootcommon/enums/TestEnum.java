package com.example.springbootcommon.enums;

public class TestEnum {

    public static void main(String[] args) {
        VersionEnum v1 = VersionEnum.VERSION_1_1;
        System.out.println(v1.equalTo("1.1"));
        System.out.println(v1.equalTo("1.2"));
    }
}
