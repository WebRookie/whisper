package com.rookie.whisper.service;

import com.rookie.whisper.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author edy
* @description 针对表【user(用户信息表)】的数据库操作Service
* @createDate 2023-11-28 17:29:53
*/
public interface UserService extends IService<User> {

    /**
     * 用户登录微信
     * @param jsCode
     * @return
     */
    User userLogin(String jsCode);

    /**
     * 根据用户Id查询用户信息
     * @param userId 用户id
     * @return 用户信息
     */
    User getUserInfo(Long userId);
}
