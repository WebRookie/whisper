package com.rookie.whisper.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 通知消息表
 * @TableName notice
 */
@TableName(value ="notice")
public class Notice implements Serializable {
    /**
     * 通知id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 通知类型：0-系统消息、1-用户通知
     */
    private Integer noticeType;

    /**
     * 通知状态: 0-正常
     */
    private Integer status;

    /**
     * 消息标题
     */
    private String title;

    /**
     * 通知内容
     */
    private String content;

    /**
     * 接受用户id
     */
    private Long userId;

    /**
     * 发送方id，系统发送为null
     */
    private Long sendId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除， 0-未删除、1-已删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 通知id
     */
    public Long getId() {
        return id;
    }

    /**
     * 通知id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 通知类型：0-系统消息、1-用户通知
     */
    public Integer getNoticeType() {
        return noticeType;
    }

    /**
     * 通知类型：0-系统消息、1-用户通知
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * 通知状态: 0-正常
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 通知状态: 0-正常
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 消息标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 通知内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 通知内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 接受用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 接受用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 发送方id，系统发送为null
     */
    public Long getSendId() {
        return sendId;
    }

    /**
     * 发送方id，系统发送为null
     */
    public void setSendId(Long sendId) {
        this.sendId = sendId;
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
     * 是否删除， 0-未删除、1-已删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除， 0-未删除、1-已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}