package com.flight.flight.controller;


import com.flight.flight.bo.Result;
import com.flight.flight.dto.LoginDTO;
import com.flight.flight.dto.RegisterDTO;
import com.flight.flight.ibo.LoginIBO;
import com.flight.flight.ibo.RegisterIBO;
import com.flight.flight.service.UserService;
import com.flight.flight.test.Fake;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Slf4j
@RestController

@Api(value = "用户管理")
@RequestMapping("user")
public class UserController {
    @Resource
    UserService userService;
    @PostMapping("login")
    @ApiOperation(value = "loginDTO", notes = "通过账户密码登录")
    public Result login(@RequestBody LoginDTO loginDTO){
        LoginIBO loginParam = new LoginIBO();
        BeanUtils.copyProperties(loginDTO,loginParam);
        return userService.login(loginParam);
    }
    @PostMapping("register")
    @ApiOperation(value = "registerDTO", notes = "通过账户密码注册")
    public Result register(@RequestBody RegisterDTO registerDTO){
        RegisterIBO registerParam = new RegisterIBO();
        BeanUtils.copyProperties(registerDTO,registerParam);
        return userService.register(registerParam);
    }
    @GetMapping("faker")
    public Result faker(){
        RegisterIBO registerParam = new RegisterIBO();
        Fake faker = new Fake().faker();
        System.out.println(faker);
        BeanUtils.copyProperties(faker,registerParam);
        return userService.register(registerParam);
    }
}
