package com.example.dubbo.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiResult<T> {
    boolean success;
    String code;
    String error;
    String message;
    String path;
    long time;
    T data;

    public ApiResult (int code, String message){
        this.code = String.valueOf(code);
        this.message = message;
    }
}