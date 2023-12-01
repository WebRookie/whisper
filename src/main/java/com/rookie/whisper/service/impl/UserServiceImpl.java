package com.rookie.whisper.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.entity.User;
import com.rookie.whisper.entity.response.NoticeResponse;
import com.rookie.whisper.exception.CustomException;
import com.rookie.whisper.mapper.NoticeMapper;
import com.rookie.whisper.mapper.UserMapper;
import com.rookie.whisper.service.UserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

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
    public List<NoticeResponse> getUserNotice(PageVo pageVo) {
        Page<NoticeResponse> page = new Page<>(pageVo.getPageNum(), pageVo.getPageSize());
        Map<String, Object> map = pageVo.getParam();
        Long userId = ((Integer) map.get("userId")).longValue();
        List<NoticeResponse> noticeList = noticeMapper.selectUserNotice(page, userId);
        log.info("查询数组：{}", noticeList);
        return noticeList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateUserInfo(Map<String, Object> map) {
        Map<String, Object> updateUser = userMapper.selectUserById(((Integer) map.get("userId")).longValue());
        if (updateUser == null) {
            throw new CustomException(40000, "用户不存在");
        }
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = new UpdateWrapper<User>().lambda();
        lambdaUpdateWrapper.set(User::getNickname, map.get("nickname"))
                .set(User::getAvatar, map.get("avatar"))
                .set(User::getGender, map.get("gender"))
                .set(User::getPhone, map.get("phone"))
                .set(User::getEmail, map.get("email"));
        lambdaUpdateWrapper.eq(User::getId, map.get("userId"));
        userMapper.update(null, lambdaUpdateWrapper);

        //        lambdaUpdateWrapper.eq(User::getNickname ,map.get("nickname")).eq();
//        updateUser.put("nickname", map.get("nickname"));
//        updateUser.put("gender", map.get("gender"));
//        updateUser.put("phone", map.get("phone"));
//        updateUser.put("avatar", map.get("avatar"));
//        updateUser.put("email", map.get("email"));

        return 1;
    }


    /**
     * 微信登录获取openId
     *
     * @param jsCode 前端登录jsCode
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




