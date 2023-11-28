package com.rookie.whisper.entity.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author WebRookie
 * @date 2023/11/28 20:04
 * @description
 **/
@Data
public class UserInfoRequest implements Serializable {

    private Long userId;
}
