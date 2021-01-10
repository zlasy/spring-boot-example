package com.example.springbootcommon.jackson;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class JsonUser {

    Long userId;
    String userName;
    SEX sex;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    Date creationDate;

    enum SEX{
        MALE,FEMALE;
    }
}
