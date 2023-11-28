package com.rookie.whisper.service.impl;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.entity.User;
import com.rookie.whisper.exception.CustomException;
import com.rookie.whisper.service.UserService;
import com.rookie.whisper.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;
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
    public User getUserInfo(Long userId) {
        return null;
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




