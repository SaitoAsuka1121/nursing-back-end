package com.flight.flight.controller;

import com.flight.flight.bo.Result;
import com.flight.flight.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
