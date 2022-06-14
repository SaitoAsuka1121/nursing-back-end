package com.flight.flight.controller;

import com.flight.flight.bo.Result;
import com.flight.flight.dto.LoginDTO;
import com.flight.flight.ibo.LoginIBO;
import com.flight.flight.service.InfoService;
import com.flight.flight.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("info")
public class InfoController {
    @Resource
    InfoService infoService;
    @GetMapping("elder")
    public Result elder(){
        return infoService.elder();
    }
}
