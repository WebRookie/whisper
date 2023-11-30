package com.rookie.whisper.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息表
 * @TableName user
 */
@TableName(value ="user")
public class User implements Serializable {
    /**
     * 用户Id
     */
    @TableId(type = IdType.AUTO)
    private Long id;


    /**
     * 用户头像
     */
    private String avatar;

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
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 最后一次登陆时间
     */
    private Date lastLoginTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户Id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 用户openId
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 用户openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * 用户性别: 1-男性、2-女性
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 用户性别: 1-男性、2-女性
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 用户手机账号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 用户手机账号
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 关联id
     */
    public Long getRelatedId() {
        return relatedId;
    }

    /**
     * 关联id
     */
    public void setRelatedId(Long relatedId) {
        this.relatedId = relatedId;
    }

    /**
     * 关联状态:0-普通用户、1-正常关联、2-已解除关联、3-申请关联中、4-申请解除关联中）
     */
    public Integer getRelatedStatus() {
        return relatedStatus;
    }

    /**
     * 关联状态:0-普通用户、1-正常关联、2-已解除关联、3-申请关联中、4-申请解除关联中）
     */
    public void setRelatedStatus(Integer relatedStatus) {
        this.relatedStatus = relatedStatus;
    }

    /**
     * 用户状态： 0-正常使用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 用户状态： 0-正常使用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 关联时间
     */
    public Date getRelatedTime() {
        return relatedTime;
    }

    /**
     * 关联时间
     */
    public void setRelatedTime(Date relatedTime) {
        this.relatedTime = relatedTime;
    }

    /**
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 最后一次登陆时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", openId='" + openId + '\'' +
                ", gender=" + gender +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", relatedId=" + relatedId +
                ", relatedStatus=" + relatedStatus +
                ", status=" + status +
                ", relatedTime=" + relatedTime +
                ", createTime=" + createTime +
                ", avatar=" + avatar +
                ", updateTime=" + updateTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }

    /**
     * 最后一次登陆时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}