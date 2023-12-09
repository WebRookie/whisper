package com.rookie.whisper.service;

import com.rookie.whisper.entity.Message;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rookie.whisper.utils.PageVo;

import java.util.Map;

/**
* @author edy
* @description 针对表【message(信息表)】的数据库操作Service
* @createDate 2023-11-28 17:28:04
*/
public interface MessageService extends IService<Message> {

    /**
     * 分页获取用户信息列表
     * @param pageVo
     * @return
     */
    Map<String,Object> getMessageByPage(PageVo pageVo);
}
