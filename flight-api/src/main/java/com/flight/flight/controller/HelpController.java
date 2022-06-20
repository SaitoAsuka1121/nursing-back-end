package com.flight.flight.controller;

import com.flight.flight.bo.Result;
import com.flight.flight.dto.HelpAddDTO;
import com.flight.flight.dto.HelpAgreeDTO;
import com.flight.flight.dto.HelpMessageDTO;
import com.flight.flight.ibo.HelpAddIBO;
import com.flight.flight.ibo.HelpAgreeIBO;
import com.flight.flight.ibo.HelpMessageIBO;
import com.flight.flight.service.HelpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("help")
public class HelpController {
    @Resource
    private HelpService helpService;
    @GetMapping("list")
    public Result list(){
        return helpService.list();
    }
    @PostMapping("add")
    public Result add(@RequestBody HelpAddDTO helpAddDTO){
        HelpAddIBO helpAddIBO = new HelpAddIBO();
        BeanUtils.copyProperties(helpAddDTO,helpAddIBO);
        return helpService.add(helpAddIBO);
    }
    @PostMapping("agree")
    public Result agree(@RequestBody HelpAgreeDTO helpAgreeDTO){
        HelpAgreeIBO helpAgreeIBO = new HelpAgreeIBO();
        BeanUtils.copyProperties(helpAgreeDTO,helpAgreeIBO);
        return helpService.agree(helpAgreeIBO);
    }
    @PostMapping("message")
    public Result message(@RequestBody HelpMessageDTO helpMessageDTO){
        HelpMessageIBO helpMessageIBO = new HelpMessageIBO();
        BeanUtils.copyProperties(helpMessageDTO,helpMessageIBO);
        return helpService.message(helpMessageIBO);
    }
    @GetMapping("history")
    public Result history(@RequestParam("id") String id){
        return helpService.history(id);
    }
    @GetMapping("release")
    public Result release(@RequestParam("id") String id){
        return helpService.release(id);
    }
    @GetMapping("cancel")
    public Result cancel(@RequestParam("id") String id){
        return helpService.cancel(id);
    }
    @GetMapping("complete")
    public Result complete(@RequestParam("id") String id){
        return helpService.complete(id);
    }
}
