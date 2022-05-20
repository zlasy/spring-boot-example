package com.example.springboot.architecture.designpattern.responsibilitychain.pojo;

import lombok.Data;

@Data
public class DeliverInfo {

    Long id;

    Integer schoolId;

    String deliverCode;

    Integer saleType;

    Boolean isFail;

    String failMsg;
}
