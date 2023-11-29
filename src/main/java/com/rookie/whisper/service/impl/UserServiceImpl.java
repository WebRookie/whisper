package com.rookie.whisper.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.entity.Message;
import com.rookie.whisper.entity.Notice;
import com.rookie.whisper.entity.User;
import com.rookie.whisper.entity.response.NoticeResponse;
import com.rookie.whisper.entity.response.UserInfoResponse;
import com.rookie.whisper.exception.CustomException;
import com.rookie.whisper.mapper.NoticeMapper;
import com.rookie.whisper.service.UserService;
import com.rookie.whisper.mapper.UserMapper;
import com.rookie.whisper.utils.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author edy
 * @description 针对表【user(用户信息表)】的数据库操作Service实现
 * @createDate 2023-11-28 17:29:53
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Value("${wxAppId}")
    private String appId;
    @Value("${wxAppSecret}")
    private String appSecret;

    @Resource
    private UserMapper userMapper;

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public User userLogin(String jsCode) {
        JSONObject result = getUserWxLogin(jsCode);
        String openId = result.getString("openid");
        if (result == null || openId == null) {
            throw new CustomException(CodeEnum.REQUEST_CODE_ERROR);
        }
        User searchUser = userMapper.selectUserByOpenId(openId);
        if (searchUser == null) {
            // 新用户登录
            searchUser = new User();
            searchUser.setOpenId(openId);
            searchUser.setCreateTime(new Date());
            searchUser.setLastLoginTime(new Date());
            userMapper.insert(searchUser);
            log.info("用户Id: {}", searchUser.getId());
        } else {
            searchUser.setLastLoginTime(new Date());
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.like("open_id", openId);
            updateWrapper.set("last_login_time", new Date());
            userMapper.update(null, updateWrapper);
        }
        return searchUser;
    }

    @Override
    public Map<String, Object> getUserInfo(long userId) {
        return userMapper.selectUserById(userId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setRelation(long userId, long receiveId) {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        Map<String, Object> sendUser = this.getUserInfo(userId);
        Map<String, Object> receiveUser = this.getUserInfo(receiveId);
        int sendUserRelatedStatus = (int) sendUser.get("relatedStatus");
        int receiveUserRelatedStatus = (int) receiveUser.get("relatedStatus");
        // 用户处于非可绑定状态时、或者绑定者等于自己时
        if (sendUserRelatedStatus != 0 || receiveUserRelatedStatus != 0) {
            throw new CustomException(CodeEnum.USER_NOT_AVAILABLE);
        }
        // 发送方状态更改申请中...
        updateWrapper.set("related_status", 3).eq("id", userId);
        this.update(updateWrapper);
        // 发送信息
        Notice sendNotice = new Notice();
        sendNotice.setContent("用户" + sendUser.get("nickname") + "ID为：" + userId + "申请与您绑定关系");
        sendNotice.setCreateTime(new Date());
        sendNotice.setUserId(receiveId);
        sendNotice.setSendId(userId);
        noticeMapper.insert(sendNotice);
    }

    @Override
    public List<NoticeResponse> getUserNotice(PageVo pageVo) {
        Page<NoticeResponse> page = new Page<>(pageVo.getPageNum(), pageVo.getPageSize());
        List<NoticeResponse> noticeList = userMapper.selectUserNotice(page);
        log.info("查询数组：{}", noticeList);
        return noticeList;
    }


    /**
     * 微信登录获取openId
     *
     * @param jsCode 前端登录jscode
     * @return JSONObject
     */
    public JSONObject getUserWxLogin(String jsCode) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appId + "&secret=" + appSecret + "&js_code=" + jsCode + "&grant_type=authorization_code";
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonData = null;
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                jsonData = (JSONObject) JSON.parse(result);
                log.info("请求信息：{}", jsonData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }
}




