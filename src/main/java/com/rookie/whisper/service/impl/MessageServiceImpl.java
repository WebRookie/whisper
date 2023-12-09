package com.rookie.whisper.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.entity.Message;
import com.rookie.whisper.service.MessageService;
import com.rookie.whisper.mapper.MessageMapper;
import com.rookie.whisper.utils.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author edy
 * @description 针对表【message(信息表)】的数据库操作Service实现
 * @createDate 2023-11-28 17:28:04
 */
@Service
@Slf4j
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
        implements MessageService {

    @Resource
    private MessageMapper messageMapper;

    @Override
    public Map<String, Object> getMessageByPage(PageVo pageVo) {
        Map<String, Object> requestMap = pageVo.getParam();
        Page<Message> page = new Page<>(pageVo.getPageNum(), pageVo.getPageSize());
        Map<String, Object> map = new HashMap<>();
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("send_user_id", requestMap.get("userId"))
                .eq("receive_user_id", requestMap.get("relateUserId"))
                .or()
                .eq("send_user_id", requestMap.get("relateUserId"))
                .eq("receive_user_id", requestMap.get("userId"))
                .orderByDesc("create_time");
        List<Message> messageList = messageMapper.selectPage(page, queryWrapper).getRecords();


        Long count = messageMapper.selectCount(queryWrapper);
        log.info("查询结果为: {}", messageList);
        map.put("count", count);
        map.put("list", messageList);
        return map;
    }
}




