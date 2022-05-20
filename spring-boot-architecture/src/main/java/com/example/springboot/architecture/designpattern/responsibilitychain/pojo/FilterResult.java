package com.example.springboot.architecture.designpattern.responsibilitychain.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FilterResult {

    Integer code;

    String errorMsg = "";

    public static FilterResult success() {
        return new FilterResult(0, "");
    }

    public static FilterResult fail(Integer code, String errorMsg){
        return new FilterResult(code, errorMsg);
    }
}
