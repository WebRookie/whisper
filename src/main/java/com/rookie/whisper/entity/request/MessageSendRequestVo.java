package com.rookie.whisper.entity.request;

import lombok.Data;

/**
 * @author WebRookie
 * @date 2023-12-09 18:06:43
 * @description
 */
@Data
public class MessageSendRequestVo {
    /**
     * 接受方用户id
     */
    private Long userId;

    /**
     * 发送方用户Id
     */
    private Long sendUserId;


    /**
     * 发送图片
     */
    private String img;

    /**
     * 发送内容
     */
    private String content;
}

