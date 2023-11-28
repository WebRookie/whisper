package com.rookie.whisper.mapper;

import com.rookie.whisper.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author edy
* @description 针对表【user(用户信息表)】的数据库操作Mapper
* @createDate 2023-11-28 17:29:53
* @Entity com.rookie.whisper.entity.User
*/
public interface UserMapper extends BaseMapper<User> {


    /**
     * 根据openId查询用户
     * @param openId 用户openId
     * @return User
     */
    User selectUserByOpenId(String openId);
}




