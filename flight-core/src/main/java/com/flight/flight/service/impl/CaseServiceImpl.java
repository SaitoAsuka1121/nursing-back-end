package com.flight.flight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flight.flight.bo.ErrorCode;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.Case;
import com.flight.flight.entity.Help;
import com.flight.flight.entity.User;
import com.flight.flight.ibo.CaseAddIBO;
import com.flight.flight.mapper.CaseMapper;
import com.flight.flight.service.CaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
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
        wapper.select(Case::getId,Case::getName,Case::getSex,Case::getPhone,Case::getRe_phone,Case::getCases);
        return Result.success( caseMapper.selectList(wapper));
    }

    @Override
    public Result add(CaseAddIBO caseAddIBO) {
        Case aCase = new Case();
        BeanUtils.copyProperties(caseAddIBO,aCase);
        int insert = caseMapper.insert(aCase);
        if(insert==1){
            return Result.success("插入成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }
    @Override
    public Result del(String id){
        LambdaQueryWrapper<Case> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Case::getId,id);
        int i = caseMapper.delete(queryWrapper);
        if(i==1){
            return Result.success("删除成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
    }
}
