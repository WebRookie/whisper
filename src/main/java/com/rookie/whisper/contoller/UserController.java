package com.rookie.whisper.contoller;

import com.rookie.whisper.common.BaseResponse;
import com.rookie.whisper.common.CodeEnum;
import com.rookie.whisper.common.ResultUtils;
import com.rookie.whisper.entity.User;
import com.rookie.whisper.entity.request.UserInfoRequest;
import com.rookie.whisper.entity.response.NoticeResponse;
import com.rookie.whisper.service.UserService;
import com.rookie.whisper.utils.PageVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
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
    public BaseResponse getUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        if (userInfoRequest == null || userInfoRequest.getUserId() == null) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }
        long userId = userInfoRequest.getUserId();
        Map<String, Object> userMap = userService.getUserInfo(userId);
        return ResultUtils.success(userMap);
    }





    @PostMapping("updateUserInfo")
    public BaseResponse updateUserInfo(@RequestBody Map<String, Object> map) {
        if (map.get("userId") == null) {
            return ResultUtils.error(CodeEnum.REQUEST_PARAM_ERROR);
        }
        int result = userService.updateUserInfo(map);
        if (result != 0) {
            return ResultUtils.success("更新成功！");
        } else {
            return ResultUtils.error(CodeEnum.SERVICE_ERROR);
        }
    }


}
