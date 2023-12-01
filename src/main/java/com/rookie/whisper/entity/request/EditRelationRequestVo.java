package com.rookie.whisper.entity.request;

import lombok.Data;

/**
 * @author WebRookie
 * @date 2023/12/1 10:13
 * @description
 **/
@Data
public class EditRelationRequestVo {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 发送方用户Id
     */
    private Long sendUserId;

    /**
     * 处理状态：2-同意、3-拒绝
     */
    private Integer status;

    /**
     * 通知Id
     */
    private Long noticeId;


}
