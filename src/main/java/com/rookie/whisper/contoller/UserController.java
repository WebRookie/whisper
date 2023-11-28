package com.rookie.whisper.contoller;

import com.rookie.whisper.common.BaseResponse;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.common.ResultUtils;
import com.rookie.whisper.entity.User;
import com.rookie.whisper.entity.request.UserInfoRequest;
import com.rookie.whisper.exception.CustomException;
import com.rookie.whisper.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author WebRookie
 * @date 2023/11/28 17:32
 * @description
 **/

@RestController
@RequestMapping(("/user"))
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse<User> login(@RequestBody String jsCode) {
        if (StringUtils.isBlank(jsCode)) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }
        User user = userService.userLogin(jsCode);
        return ResultUtils.success(user);
    }


    @PostMapping("/getUserInfo")
    public BaseResponse<User> getUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        System.out.println(userInfoRequest);
        System.out.println(userInfoRequest.getUserId());
        if(userInfoRequest == null) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }
        Long userId = userInfoRequest.getUserId();
        User user = userService.getUserInfo(userId);
        return ResultUtils.success(user);
    }
}
