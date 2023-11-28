package com.rookie.whisper.exception;

import com.rookie.whisper.common.CodeEnum;

/**
 * @author WebRookie
 * @date 2023/11/28 16:26
 * @description 自定义异常类
 **/
public class CustomException extends RuntimeException{


    private final int code;

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }

    public CustomException(CodeEnum codeEnum) {
        super(codeEnum.getMessage());
        this.code = codeEnum.getCode();
    }

    public int getCode() {
        return code;
    }

}
