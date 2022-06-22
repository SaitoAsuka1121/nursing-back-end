package com.flight.flight.controller;

import com.flight.flight.bo.Result;
import com.flight.flight.dto.DrugAddDTO;
import com.flight.flight.dto.LoginDTO;
import com.flight.flight.dto.UserAddDTO;
import com.flight.flight.ibo.DrugAddIBO;
import com.flight.flight.ibo.LoginIBO;
import com.flight.flight.ibo.UserAddIBO;
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

    @GetMapping("all")
    public Result list() {
        return infoService.list();
    }

    @PostMapping("add")
    public Result add(@RequestBody UserAddDTO userAddDTO) {
        UserAddIBO userAddIBO = new UserAddIBO();
        BeanUtils.copyProperties(userAddDTO, userAddIBO);
        return infoService.add(userAddIBO);
    }

    @GetMapping("del")
    public Result del(@RequestParam String id) {
        return infoService.del(id);
    }

    @GetMapping("like")
    public Result search(@RequestParam String name) {
        return infoService.search(name);
    }
}
