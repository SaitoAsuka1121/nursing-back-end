package com.flight.flight.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author liu
 */
@Data
public class User {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String account;
    private String password;
    private String name;
    private String address;
    private String sex;
    private Integer age;
    private String salt;


}
