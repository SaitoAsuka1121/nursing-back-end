package com.flight.flight.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Case {
     @TableId(type = IdType.ASSIGN_ID)
     private String id;
     private String name;
     private String sex;
     private String phone;
     private String cases;
     private String re_phone;
}
