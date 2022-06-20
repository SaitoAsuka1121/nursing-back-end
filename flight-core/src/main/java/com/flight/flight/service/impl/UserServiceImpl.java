package com.flight.flight.service.impl;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.flight.flight.bo.ErrorCode;
import com.flight.flight.bo.Result;
import com.flight.flight.entity.User;
import com.flight.flight.ibo.LoginIBO;
import com.flight.flight.ibo.RegisterIBO;
import com.flight.flight.ibo.ReturnParm;
import com.flight.flight.ibo.UpdateuserIBO;
import com.flight.flight.mapper.UserMapper;
import com.flight.flight.service.UserService;
import com.flight.flight.utils.JwtUtils;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * @author liu
 */

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserService userService;
    @Resource
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public Result login(LoginIBO loginParam) {
        String account = loginParam.getAccount();
        String password= loginParam.getPassword();
        //格式错误
        if(StringUtils.isBlank(account) || StringUtils.isBlank(account) ){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
        }
        User user = userService.findUserSlat(account);
        //账号错误
        if(user==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(), ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String slat = user.getSalt();
        password = DigestUtils.md5Hex(password + slat);
        User sysUser= userService.findUser(account,password);
        // 账号密码错误
        if(sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        log.info(sysUser.toString());
        String token = JwtUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),1, TimeUnit.DAYS);
        ReturnParm returnParm = new ReturnParm();
        returnParm.setToken(token);
        returnParm.setUid(sysUser.getId());
        returnParm.setName(sysUser.getName());
        return Result.success(returnParm);
    }

    @Override
    public Result register(RegisterIBO registerParam) {
        String account = registerParam.getAccount();
        String password = registerParam.getPassword();
        String nickname = registerParam.getName();
        // 格式错误
        System.out.println(registerParam);
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password) || StringUtils.isBlank(nickname)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        //重复注册
        User user = userService.findUserByAccount(account);
        if(user!=null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.ACCOUNT_EXIST.getMsg());
        }
        //生成随机加密盐
        String slat = getRandomSlat();
        // 插入准备
        user = new User();
        user.setAccount(account);
        user.setPassword(DigestUtils.md5Hex(password+slat));
        user.setName(nickname);
        user.setSalt(slat);
        Faker faker = new Faker(Locale.CHINA);
        //插入数据库
        userMapper.insert(user);
        //生成Token
        String token = JwtUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }
    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    @Override
    public Result updateUser(UpdateuserIBO updateUserParam, String id) {
        String email = updateUserParam.getEmail();
        String mobilePhoneNumber = updateUserParam.getMobilePhoneNumber();
        String nickname = updateUserParam.getNickname();
        String password = updateUserParam.getPassword();
        return null;
    }

    /**
     * 生成随机加密盐
     * @return String
     */
    public String getRandomSlat(){
        String randStr = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        int len =4;
        for (int i=0;i< len ;i++){
            stringBuilder.append(randStr.charAt(random.nextInt(randStr.length())));
        }
        return stringBuilder.toString();
    }
    /**
     * 查找用户加密盐
     * @return User
     */
    @Override
    public User findUserSlat(String account){
        LambdaQueryWrapper<User> slat= new LambdaQueryWrapper();
        slat.eq(User::getAccount,account);
        slat.select(User::getSalt,User::getId);
        slat.last("limit 1");
        return userMapper.selectOne(slat);
    }
    @Override
    public User findUserByAccount(String account){
        LambdaQueryWrapper<User> slat= new LambdaQueryWrapper();
        slat.eq(User::getAccount,account);
        slat.select(User::getSalt);
        slat.last("limit 1");
        return userMapper.selectOne(slat);
    }
    /**
     * 验证用户账号密码
     * @return User
     */
    @Override
    public User findUser(String account, String password) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getAccount,account);
        queryWrapper.eq(User::getPassword,password);
        queryWrapper.select(User::getAccount,User::getName,User::getId);
        queryWrapper.last("limit 1");
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public User checkToken(String token) {
        if(StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> stringObjectMap = JwtUtils.checkToken(token);
        if(stringObjectMap == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_"+token);
        if(StringUtils.isBlank(userJson)){
            return null;
        }
        return JSON.parseObject(userJson, User.class);
    }

    @Override
    public Result del(String id) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getId,id);
        int i = userMapper.delete(queryWrapper);
        if(i==1){
            return Result.success("删除成功");
        }
        return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
    }
}
