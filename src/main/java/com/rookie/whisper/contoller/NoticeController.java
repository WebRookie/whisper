package com.rookie.whisper.contoller;

import com.rookie.whisper.common.BaseResponse;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.common.ResultUtils;
import com.rookie.whisper.entity.request.EditRelationRequestVo;
import com.rookie.whisper.entity.response.SendRelationRequest;
import com.rookie.whisper.service.NoticeService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author WebRookie
 * @date 2023/12/1 10:04
 * @description
 **/
@RestController
@RequestMapping("/notice")
public class NoticeController {


    @Resource
    private NoticeService noticeService;


    @PostMapping("/setRelation")
    public BaseResponse setRelation(@RequestBody SendRelationRequest sendRelationRequest) {
        if (sendRelationRequest == null || sendRelationRequest.getReceiveId() == null || sendRelationRequest.getUserId() == null) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }
        Long userId = sendRelationRequest.getUserId();
        Long receiveId = sendRelationRequest.getReceiveId();
        noticeService.setRelation(userId, receiveId);
        return ResultUtils.success("ok");
    }

    @PostMapping("/editRelation")
    public BaseResponse editRelation(@RequestBody EditRelationRequestVo editRelationRequestVo) {
        if (editRelationRequestVo.getNoticeId() == null || editRelationRequestVo.getStatus() == null || editRelationRequestVo.getUserId() == null || editRelationRequestVo.getSendUserId() == null) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }

        noticeService.editRelation(editRelationRequestVo.getUserId(),editRelationRequestVo.getNoticeId(), editRelationRequestVo.getStatus(), editRelationRequestVo.getSendUserId());
        return ResultUtils.success("操作成功！");

    }
}
