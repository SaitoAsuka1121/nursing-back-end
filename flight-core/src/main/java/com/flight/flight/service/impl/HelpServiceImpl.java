package com.flight.flight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flight.flight.bo.ErrorCode;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.Help;
import com.flight.flight.ibo.HelpAddIBO;
import com.flight.flight.ibo.HelpAgreeIBO;
import com.flight.flight.ibo.HelpMessageIBO;
import com.flight.flight.mapper.HelpMapper;
import com.flight.flight.service.HelpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class HelpServiceImpl implements HelpService{
    @Resource
    private HelpMapper helpMapper;
    public Result all(){
        LambdaQueryWrapper<Help> wrapper = new LambdaQueryWrapper<>();
        return getResult(wrapper);
    }

    private Result getResult(LambdaQueryWrapper<Help> wrapper) {
        wrapper.select(Help::getId,Help::getTitle,Help::getBegin,Help::getEnd,Help::getContent,Help::getType,Help::getMessage,Help::getName);
        List<Help> helps = helpMapper.selectList(wrapper);
        return Result.success(helps);
    }

    public Result list(){
        LambdaQueryWrapper<Help> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Help::getUserId,"-1");
        return getResult(wrapper);
    }

    @Override
    public Result add(HelpAddIBO helpAddIBO) {
        Help help = new Help();
        BeanUtils.copyProperties(helpAddIBO,help);
        help.setUserId("-1");
        help.setType("无人接受");
        int insert = helpMapper.insert(help);
        if(insert==1){
            return Result.success("插入成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
    }

    @Override
    public Result agree(HelpAgreeIBO helpAgreeIBO) {
        String oid = helpAgreeIBO.getOid();
        String uid = helpAgreeIBO.getUid();
        Help help = new Help();
        help.setId(oid);
        help.setType("进行中");
        help.setUserId(uid);
        int i = helpMapper.updateById(help);
        if(i==1){
            return Result.success("接受成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
    }

    @Override
    public Result message(HelpMessageIBO helpMessageIBO) {
        String oid = helpMessageIBO.getOid();
        String message = helpMessageIBO.getMessage();
        Help help = new Help();
        help.setId(oid);
        help.setMessage(message);
        int i = helpMapper.updateById(help);
        if(i==1){
            return Result.success("评价成功!");
        }
        return Result.fail(ErrorCode.SYSTEM_ERROR.getCode(), ErrorCode.SYSTEM_ERROR.getMsg());
    }

    @Override
    public Result history(String id) {
        LambdaQueryWrapper<Help> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Help::getUserId,id);
        List<Help> helps = helpMapper.selectList(wrapper);
        return Result.success(helps);
    }

    @Override
    public Result release(String id) {
        LambdaQueryWrapper<Help> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Help::getFromId,id);
        List<Help> helps = helpMapper.selectList(wrapper);
        return Result.success(helps);
    }

    @Override
    public Result cancel(String id) {
        Help help = new Help();
        help.setId(id);
        help.setType("进行中");
        help.setUserId("-1");
        int i = helpMapper.updateById(help);
        if(i==1){
            return Result.success("取消成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
    }

    @Override
    public Result complete(String id) {
        Help help = new Help();
        help.setId(id);
        help.setType("已完成");

        int i = helpMapper.updateById(help);
        if(i==1){
            return Result.success("成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());

    }
}
