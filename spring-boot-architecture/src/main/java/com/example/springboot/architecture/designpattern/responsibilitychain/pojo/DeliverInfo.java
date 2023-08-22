package com.example.springboot.architecture.designpattern.responsibilitychain.pojo;

import lombok.Data;

@Data
public class DeliverInfo {

    Long id;

    Integer schoolId;

    String deliverCode;

    String studentCode;

    Integer saleType;

    Boolean isFail;

    String failMsg;

    public static DeliverInfo mock1(){
        DeliverInfo deliverInfo = new DeliverInfo();
        deliverInfo.setId(1L);
        deliverInfo.setSchoolId(1);
        deliverInfo.setDeliverCode("TBD000001");
        deliverInfo.setStudentCode("Stu0001");
        deliverInfo.setSaleType(1);
        deliverInfo.setIsFail(false);
        return deliverInfo;
    }

    @Override
    public String toString(){
        return "DeliverInfo{" +
                "id=" + id +
                ", schoolId=" + schoolId +
                ", deliverCode='" + deliverCode + '\'' +
                ", studentCode='" + studentCode + '\'' +
                ", saleType=" + saleType +
                ", isFail=" + isFail +
                ", failMsg='" + failMsg + '\'' +
                '}';
    }
}
