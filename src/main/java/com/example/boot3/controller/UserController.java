package com.example.boot3.controller;

import com.example.boot3.common.model.result.ApiResult;
import com.example.boot3.config.security.component.SecurityDetailsContextHolder;
import com.example.boot3.config.security.component.SecurityUserDetails;
import com.example.boot3.model.dto.UserLoginResultDTO;
import com.example.boot3.model.po.UserPO;
import com.example.boot3.model.vo.request.UserLoginRequest;
import com.example.boot3.model.vo.request.UserRegisterRequest;
import com.example.boot3.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alex Meng
 * @createDate 2023-12-23 23:12
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private IUserService adminUserService;

    @PostMapping("/register")
    public ApiResult<String> register(@RequestBody UserRegisterRequest request) {
        adminUserService.register(request.getUsername(), request.getPassword(), request.getNikeName());
        return ApiResult.success("用户注册成功");
    }

    @PostMapping("/login")
    public ApiResult<UserLoginResultDTO> doLogin(@RequestBody UserLoginRequest request) {
        UserLoginResultDTO loginResult = adminUserService.login(request.getUsername(), request.getPassword());
        return ApiResult.success(loginResult);
    }

    @GetMapping("/current")
    public ApiResult<UserPO> current() {
        SecurityUserDetails userDetails = SecurityDetailsContextHolder.getContext();
        UserPO user = userDetails.getUser();
        return ApiResult.success(user);
    }

    @PostMapping("/logout")
    public ApiResult<String> doLogout() {
        SecurityUserDetails userDetails = SecurityDetailsContextHolder.getContext();
        UserPO user = userDetails.getUser();
        adminUserService.logout(user.getUsername());
        return ApiResult.success("退出登录成功");
    }
}