package com.rookie.whisper.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rookie.whisper.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rookie.whisper.entity.response.NoticeResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author edy
* @description 针对表【notice(通知消息表)】的数据库操作Mapper
* @createDate 2023-11-28 17:28:53
* @Entity com.rookie.whisper.entity.Notice
*/
public interface NoticeMapper extends BaseMapper<Notice> {
    /**
     * 分页查询用户通知记录
     * @param page
     * @param userId
     * @return
     */
    List<NoticeResponse> selectUserNotice(Page<NoticeResponse> page, Long userId);


}




