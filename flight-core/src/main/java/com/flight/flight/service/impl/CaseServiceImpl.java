package com.flight.flight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.Case;
import com.flight.flight.mapper.CaseMapper;
import com.flight.flight.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class CaseServiceImpl implements CaseService {
    @Resource
    CaseMapper caseMapper;
    @Override
    public Result list() {
        LambdaQueryWrapper<Case> wapper = new LambdaQueryWrapper<>();
        wapper.select(Case::getId,Case::getName,Case::getSex,Case::getPhone,Case::getRe_phone);
        return Result.success( caseMapper.selectList(wapper));
    }
}