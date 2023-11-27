package com.rookie.whisper.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WebRookie
 * @date 2023/11/10 15:51
 * @description 通用返回类
 **/
@Data
public class BaseResponse<T> implements Serializable {

    private String message;

    private int code;

    private T data;


    public BaseResponse(String message, T data, int code) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


    public BaseResponse(T data, int code) {
        this("", data, code);
    }

    public BaseResponse(int code, String message) {
        this(message,null, code);
    }


    public BaseResponse(CodeEnum codeEnum) {
        this(codeEnum.getMessage(), null, codeEnum.getCode());
    }

    public BaseResponse(CodeEnum codeEnum, T data) {
        this(codeEnum.getMessage(), data, codeEnum.getCode());
    }


}
