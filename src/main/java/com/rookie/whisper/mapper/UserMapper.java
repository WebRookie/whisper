package com.rookie.whisper.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rookie.whisper.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rookie.whisper.entity.response.NoticeResponse;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 根据用户Id查询用户信息
     * @param userId 用户Id
     * @return 用户实体信息
     */
    Map<String, Object> selectUserById(Long userId);


}




