package com.rookie.whisper.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.entity.Notice;
import com.rookie.whisper.entity.User;
import com.rookie.whisper.exception.CustomException;

import com.rookie.whisper.mapper.NoticeMapper;
import com.rookie.whisper.mapper.UserMapper;
import com.rookie.whisper.service.NoticeService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author edy
 * @description 针对表【notice(通知消息表)】的数据库操作Service实现
 * @createDate 2023-11-28 17:28:53
 */
@Slf4j
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
        implements NoticeService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRelation(long userId, long receiveId) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        Map<String, Object> sendUser = userMapper.selectUserById(userId);
        Map<String, Object> receiveUser = userMapper.selectUserById(receiveId);
        int sendUserRelatedStatus = (int) sendUser.get("relatedStatus");
        int receiveUserRelatedStatus = (int) receiveUser.get("relatedStatus");
        // 用户处于非可绑定状态时、或者绑定者等于自己时
        if (sendUserRelatedStatus != 0 || receiveUserRelatedStatus != 0) {
            throw new CustomException(CodeEnum.USER_NOT_AVAILABLE);
        }
        // 发送方状态更改申请中...
        updateWrapper.set("related_status", 3).eq("id", userId);
        userMapper.update(null, updateWrapper);
//        this.update(updateWrapper);
        // 发送信息
        Notice sendNotice = new Notice();
        sendNotice.setContent("用户" + (sendUser.get("nickname") != null ? sendUser.get("nickname") : "微信用户") + "ID为：" + userId + "申请与您绑定关系");
        sendNotice.setCreateTime(new Date());
        sendNotice.setUserId(receiveId);
        sendNotice.setSendId(userId);
        noticeMapper.insert(sendNotice);
    }

    /**
     * 响应绑定信息
     *
     * @param sendUserId    发送方Id
     * @param sendName      发送方昵称
     * @param receiveUserId 接收方Id
     * @param kind          类型： 2-同意、3-拒绝(同步数据库)
     * @return 处理结果
     */
    @Transactional(rollbackFor = Exception.class)
    public int sendNotice(Long sendUserId, String sendName, Long receiveUserId, int kind) {
        Notice sendNotice = new Notice();
        StringBuilder content = new StringBuilder("用户")
                .append(sendName)
                .append("ID为：")
                .append(sendUserId);
        if (kind == 2) {
            content.append("已成功和您绑定关系，恭喜恭喜🎉！");
        } else if (kind == 3) {
            content.append("已拒绝和您绑定关系！不要灰心！");
        }
        sendNotice.setContent(content.toString());
        sendNotice.setCreateTime(new Date());
        sendNotice.setUserId(receiveUserId);
        sendNotice.setSendId(null);
        noticeMapper.insert(sendNotice);
        return 1;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void editRelation(Long userId, Long noticeId, Integer status, Long sendUserId) {
        User sendUser = userMapper.selectById(sendUserId);
        LambdaUpdateWrapper<Notice> updateWrapper = new UpdateWrapper<Notice>().lambda();
        // 发送方状态已经绑定
        if (sendUser.getRelatedId() != null) {
            // 修改状态为已过时
            int timeOutFlag = 4;
            updateWrapper.set(Notice::getStatus, timeOutFlag)
                    .eq(Notice::getId, noticeId);
            noticeMapper.update(null, updateWrapper);
            throw new CustomException(CodeEnum.BIND_REQUEST_TIMEOUT);
        }
        // 同意方
        User receiveUser = userMapper.selectById(userId);
        // 同意
        if (status == 2) {
            LambdaUpdateWrapper<User> userLambdaUpdateWrapper = new UpdateWrapper<User>().lambda();
            userLambdaUpdateWrapper.set(User::getRelatedId, sendUserId)
                    .set(User::getRelatedStatus, 1)
                    .eq(User::getId, userId);
            // 更新接受方关联Id
            userMapper.update(null, userLambdaUpdateWrapper);
            LambdaUpdateWrapper<User> sendUserLambdaUpdateWrapper = new UpdateWrapper<User>().lambda();
            sendUserLambdaUpdateWrapper.set(User::getRelatedId, userId)
                    .set(User::getRelatedStatus, 1)
                    .eq(User::getId, sendUserId);
            // 更新发送方管理Id
            userMapper.update(null, sendUserLambdaUpdateWrapper);
            // 发送成功消息
            sendNotice(sendUserId, sendUser.getNickname(), userId, status);
            sendNotice(userId, receiveUser.getNickname(), sendUserId, status);
            log.info("双方同意信息已经发送");
        }
        // 拒绝
        if (status == 3) {
            // 发送方状态可以更改为正常状态
            LambdaUpdateWrapper<User> lambdaUpdateWrapper = new UpdateWrapper<User>().lambda();
            lambdaUpdateWrapper.set(User::getRelatedStatus, 0)
                    .eq(User::getId, userId);
            userMapper.update(null, lambdaUpdateWrapper);
            // 双方发送失败消息
            sendNotice(sendUserId, sendUser.getNickname(), userId, status);
            sendNotice(userId, receiveUser.getNickname(), sendUserId, status);
            log.info("双方拒绝信息已发送");
        }
        // 更改消息状态
        updateWrapper.set(Notice::getStatus, status)
                .eq(Notice::getId, noticeId);
        this.update(updateWrapper);
    }
}




