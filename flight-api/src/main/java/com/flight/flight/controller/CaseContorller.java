package com.flight.flight.controller;

import com.flight.flight.bo.Result;
import com.flight.flight.dto.CaseAddDTO;
import com.flight.flight.ibo.CaseAddIBO;
import com.flight.flight.ibo.HelpAddIBO;
import com.flight.flight.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("case")
public class CaseContorller {
    @Resource
    private CaseService caseService;
    @GetMapping("all")
    public Result all(){
        return caseService.list();
    }
    @PostMapping("add")
    public Result add(@RequestBody CaseAddDTO caseAddDTO){
        log.info(caseAddDTO.toString());
        CaseAddIBO caseAddIBO = new CaseAddIBO();
        BeanUtils.copyProperties(caseAddDTO,caseAddIBO);
        log.info(caseAddIBO.toString());
        return caseService.add(caseAddIBO);
    }
    @GetMapping("del")
    public Result del(@RequestParam String id){
        return caseService.del(id);
    }
}
