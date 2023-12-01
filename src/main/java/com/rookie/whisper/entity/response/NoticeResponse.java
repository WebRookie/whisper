package com.rookie.whisper.entity.response;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author WebRookie
 * @date 2023/11/29 19:19
 * @description
 **/
@Data
public class NoticeResponse {
    private Long noticeId;

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
     * # 实体类去接返回结果，是需要在返回字段中去格式化时间
     * DATE_FORMAT(create_time, '%Y-%m-%d %H:%i:%s') as createTime
     * 如果是Map接收结果就可以在sql中写了
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;


}
