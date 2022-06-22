package com.flight.flight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flight.flight.bo.ErrorCode;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.Drug;
import com.flight.flight.ibo.DrugAddIBO;
import com.flight.flight.mapper.DrugMapper;
import com.flight.flight.service.DrugService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DrugServiceImpl implements DrugService {
    @Resource
    private DrugMapper drugMapper;
    @Override
    public Result list() {
        LambdaQueryWrapper<Drug> wapper = new LambdaQueryWrapper<>();
        wapper.select(Drug::getId,Drug::getName,Drug::getBegin,Drug::getEnd,Drug::getPrice);
        return Result.success( drugMapper.selectList(wapper));
    }

    @Override
    public Result add(DrugAddIBO drugAddIBO) {
        Drug aCase = new Drug();
        BeanUtils.copyProperties(drugAddIBO,aCase);
        int insert = drugMapper.insert(aCase);
        if(insert==1){
            return Result.success("插入成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }
    @Override
    public Result del(String id){
        LambdaQueryWrapper<Drug> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Drug::getId,id);
        int i = drugMapper.delete(queryWrapper);
        if(i==1){
            return Result.success("删除成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
    }

    @Override
    public Result search(String name) {
        LambdaQueryWrapper<Drug> wapper = new LambdaQueryWrapper<>();
        wapper.like(Drug::getName,name);
        return Result.success(drugMapper.selectList(wapper));
    }
}
