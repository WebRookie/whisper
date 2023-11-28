package com.rookie.whisper.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rookie.whisper.entity.Notice;
import com.rookie.whisper.service.NoticeService;
import com.rookie.whisper.mapper.NoticeMapper;
import org.springframework.stereotype.Service;

/**
* @author edy
* @description 针对表【notice(通知消息表)】的数据库操作Service实现
* @createDate 2023-11-28 17:28:53
*/
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice>
    implements NoticeService{

}




