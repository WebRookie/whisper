package com.rookie.whisper.common;

/**
 * @author WebRookie
 * @date 2023/11/10 15:50
 **/
public class ResultUtils {


    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(CodeEnum.SUCCESS, data);
    }

    public static <T> BaseResponse<T> requestError() {
        return new BaseResponse<>(CodeEnum.REQUEST_ERROR);
    }

    public static <T> BaseResponse<T> requestError(int code, String message, T data) {
        return new BaseResponse(message, data, code);
    }
}
