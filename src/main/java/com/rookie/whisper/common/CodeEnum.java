package com.rookie.whisper.common;

/**
 * @author WebRookie
 * @date 2023/11/27 16:20
 * @description
 **/
public enum CodeEnum {
     // 正常成功
     SUCCESS("ok", 20000),
     // 请求出错
     REQUEST_ERROR("请求出错", 40001)
     ;

     /**
      * 返回信息
      */
     private String message;

     /**
      * 返回信息自定义状态码
      */
     private int code;


     CodeEnum(String message, int code){
          this.message = message;
          this.code = code;
     }

     public String getMessage() {
          return message;
     }


     public int getCode() {
          return code;
     }
}
