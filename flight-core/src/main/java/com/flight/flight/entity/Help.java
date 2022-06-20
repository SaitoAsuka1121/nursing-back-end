package com.flight.flight.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data

public class Help {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String title;
    private String begin;
    private String end;
    private String name;
    private String content;
    private String type;
    private String message;
    private String phone;
    private String userId;
    private String fromId;
}
