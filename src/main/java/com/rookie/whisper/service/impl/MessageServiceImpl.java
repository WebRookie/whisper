package com.rookie.whisper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.service.MessageService;
import com.rookie.whisper.model.domain.Message;
import com.rookie.whisper.mapper.MessageMapper;
import org.springframework.stereotype.Service;

/**
* @author edy
* @description 针对表【message(信息表)】的数据库操作Service实现
* @createDate 2023-11-10 15:13:32
*/
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message>
    implements MessageService {

}




