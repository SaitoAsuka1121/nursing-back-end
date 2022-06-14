package com.flight.flight.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.Info;
import com.flight.flight.entity.User;
import com.flight.flight.mapper.InfoMapper;
import com.flight.flight.mapper.UserMapper;
import com.flight.flight.service.InfoService;
import com.flight.flight.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class InfoServiceImpl implements InfoService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private InfoService infoService;
    @Override
    public Result elder() {
        LambdaQueryWrapper<Info> info= new LambdaQueryWrapper<>();
        List<User> infos = userMapper.selectList(null);
        return Result.success(infos);
    }
}
