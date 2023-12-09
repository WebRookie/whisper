package com.rookie.whisper.contoller;

import com.rookie.whisper.common.BaseResponse;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.common.ResultUtils;
import com.rookie.whisper.entity.request.MessageSendRequestVo;
import com.rookie.whisper.service.MessageService;
import com.rookie.whisper.utils.PageVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * @author WebRookie
 * @date 2023-12-09 16:46:26
 * @description
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private MessageService messageService;

    @PostMapping("getMessageByPage")
    public BaseResponse getMessageByPage(@RequestBody PageVo pageVo) {
        if(pageVo.getParam().isEmpty() ||  null == pageVo.getParam().get("userId") || null == pageVo.getParam().get("relateUserId") ) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }
        return ResultUtils.success(messageService.getMessageByPage(pageVo));
    }


    @PostMapping("userSendMessage")
    public BaseResponse userSendMessage(@RequestBody MessageSendRequestVo messageSendRequestVo) {
        Optional<MessageSendRequestVo> sendRequestVo = Optional.ofNullable(messageSendRequestVo);

        // TODO Optional进行参数校验
        //  if(!sendRequestVo.isPresent() || )
        return null;
    }

}
