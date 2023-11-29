package com.rookie.whisper.entity.response;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author WebRookie
 * @date 2023/11/29 16:09
 * @description
 **/
@Data
public class UserInfoResponse {

    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户openId
     */
    private String openId;

    /**
     * 用户性别: 1-男性、2-女性
     */
    private Integer gender;

    /**
     * 用户手机账号
     */
    private String phone;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 关联id
     */
    private Long relatedId;

    /**
     * 关联状态:0-普通用户、1-正常关联、2-已解除关联、3-申请关联中、4-申请解除关联中）
     */
    private Integer relatedStatus;

    /**
     * 用户状态： 0-正常使用
     */
    private Integer status;

    /**
     * 关联时间
     */
    private Date relatedTime;

    /**
     * 最后一次登陆时间
     */

    private Date lastLoginTime;

}
