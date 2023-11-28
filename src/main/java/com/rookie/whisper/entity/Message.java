package com.rookie.whisper.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;

/**
 * 信息表
 * @TableName message
 */
@TableName(value ="message")
public class Message implements Serializable {
    /**
     * 信息id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 发送方Id
     */
    private Long sendUserId;

    /**
     * 接收方Id
     */
    private Long receiveUserId;

    /**
     * 文字内容
     */
    private String content;

    /**
     * 图片地址，如果多个图片用逗号隔开
     */
    private String img;

    /**
     * 信息状态：0-已发送、1-已读取、2-锁定中
     */
    private Integer status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除，0-未删除、 1-已删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 信息id
     */
    public Long getId() {
        return id;
    }

    /**
     * 信息id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 发送方Id
     */
    public Long getSendUserId() {
        return sendUserId;
    }

    /**
     * 发送方Id
     */
    public void setSendUserId(Long sendUserId) {
        this.sendUserId = sendUserId;
    }

    /**
     * 接收方Id
     */
    public Long getReceiveUserId() {
        return receiveUserId;
    }

    /**
     * 接收方Id
     */
    public void setReceiveUserId(Long receiveUserId) {
        this.receiveUserId = receiveUserId;
    }

    /**
     * 文字内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 文字内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 图片地址，如果多个图片用逗号隔开
     */
    public String getImg() {
        return img;
    }

    /**
     * 图片地址，如果多个图片用逗号隔开
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 信息状态：0-已发送、1-已读取、2-锁定中
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 信息状态：0-已发送、1-已读取、2-锁定中
     */
    public void setStatus(Integer status) {
        this.status = status;
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
     * 是否删除，0-未删除、 1-已删除
     */
    public Integer getIsDelete() {
        return isDelete;
    }

    /**
     * 是否删除，0-未删除、 1-已删除
     */
    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }
}