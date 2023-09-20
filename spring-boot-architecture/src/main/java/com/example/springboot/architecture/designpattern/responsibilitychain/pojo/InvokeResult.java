package com.example.springboot.architecture.designpattern.responsibilitychain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InvokeResult {

    Integer code;

    String errorMsg = "";

    public static InvokeResult success() {
        return new InvokeResult(0, "");
    }

    public static InvokeResult fail(Integer code, String errorMsg){
        return new InvokeResult(code, errorMsg);
    }
}
