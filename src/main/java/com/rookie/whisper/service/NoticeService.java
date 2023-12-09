package com.rookie.whisper.service;

import com.rookie.whisper.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rookie.whisper.entity.response.NoticeResponse;
import com.rookie.whisper.utils.PageVo;

import java.util.List;

/**
* @author edy
* @description 针对表【notice(通知消息表)】的数据库操作Service
* @createDate 2023-11-28 17:28:53
*/
public interface NoticeService extends IService<Notice> {


    /**
     * 申请绑定两人关系
     * @param userId 发送方
     * @param receiveId 接受方
     */
    void setRelation(long userId, long receiveId);

    /**
     * 处理申请状态
     * @param userId 用户Id
     * @param noticeId 消息Id
     * @param status 状态
     * @param sendUserId 发送方Id
     */
    void editRelation(Long userId, Long noticeId, Integer status, Long sendUserId);

    /**
     * 查询用户的信息通知
     * @param pageVo
     * @return
     */
    List<NoticeResponse> getUserNotice(PageVo pageVo);
}
