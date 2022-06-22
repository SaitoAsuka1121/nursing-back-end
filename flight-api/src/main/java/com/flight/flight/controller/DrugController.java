package com.flight.flight.controller;

import com.flight.flight.bo.Result;
import com.flight.flight.dto.CaseAddDTO;
import com.flight.flight.dto.DrugAddDTO;
import com.flight.flight.ibo.DrugAddIBO;
import com.flight.flight.service.DrugService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("drug")
@Slf4j
public class DrugController {
    @Resource
    private  DrugService drugService;
    @GetMapping("all")
    public Result list() {
        return drugService.list();
    }

    @PostMapping("add")
    public Result add(@RequestBody DrugAddDTO drugAddDTO) {
        DrugAddIBO drugAddIBO = new DrugAddIBO();
        BeanUtils.copyProperties(drugAddDTO,drugAddIBO);
        return drugService.add(drugAddIBO);
    }
    @GetMapping("del")
    public Result del(@RequestParam  String id) {
        return drugService.del(id);
    }

    @GetMapping("like")
    public Result search(@RequestParam String name) {
        return drugService.search(name);
    }
}
