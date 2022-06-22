package com.flight.flight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flight.flight.bo.ErrorCode;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.User;
import com.flight.flight.ibo.UserAddIBO;
import com.flight.flight.mapper.UserMapper;
import com.flight.flight.service.InfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class InfoServiceImpl implements InfoService {
    @Resource
    private UserMapper userMapper;

    @Override
    public Result list() {
        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper<>();
        wapper.select(User::getId,User::getName,User::getAccount,User::getAge,User::getSex);
        return Result.success( userMapper.selectList(wapper));
    }

    @Override
    public Result add(UserAddIBO UserAddIBO) {
        User aCase = new User();
        BeanUtils.copyProperties(UserAddIBO,aCase);
        int insert = userMapper.insert(aCase);
        if(insert==1){
            return Result.success("插入成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }
    @Override
    public Result del(String id){
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id);
        int i = userMapper.delete(queryWrapper);
        if(i==1){
            return Result.success("删除成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
    }

    @Override
    public Result search(String name) {
        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper<>();
        wapper.like(User::getName,name);
        return Result.success(userMapper.selectList(wapper));
    }
}
