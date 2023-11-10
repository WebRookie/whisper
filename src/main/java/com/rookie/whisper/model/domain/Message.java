package com.rookie.whisper.model.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 信息表
 * @TableName message
 */
@TableName(value ="message")
@Data
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
}