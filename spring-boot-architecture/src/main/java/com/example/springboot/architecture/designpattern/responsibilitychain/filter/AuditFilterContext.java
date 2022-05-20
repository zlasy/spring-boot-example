package com.example.springboot.architecture.designpattern.responsibilitychain.filter;

import com.example.springboot.architecture.designpattern.responsibilitychain.pojo.DeliverInfo;
import lombok.Data;

@Data
public class AuditFilterContext {

    private DeliverInfo deliverInfo;
}
