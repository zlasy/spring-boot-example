package com.example.dubbo.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResult<T> {
    boolean success;
    String code;
    String error;
    String message;
    String path;
    long time;
    T data;
}