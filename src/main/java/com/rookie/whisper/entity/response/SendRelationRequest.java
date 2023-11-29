package com.rookie.whisper.entity.response;

import lombok.Data;

/**
 * @author WebRookie
 * @date 2023/11/29 17:05
 * @description
 **/
@Data
public class SendRelationRequest {


    private Long userId;

    private Long receiveId;


}
