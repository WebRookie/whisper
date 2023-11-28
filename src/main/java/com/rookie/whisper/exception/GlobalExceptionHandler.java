package com.rookie.whisper.exception;

import com.rookie.whisper.common.BaseResponse;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author WebRookie
 * @date 2023/11/28 16:26
 * @description
 **/

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public BaseResponse customExceptionHandler(CustomException e) {
        log.error( "customException: ",e);
        return ResultUtils.requestError(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException:" , e);
        return ResultUtils.error(CodeEnum.SERVICE_ERROR);
    }
}
