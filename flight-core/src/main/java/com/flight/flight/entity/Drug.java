package com.flight.flight.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Drug {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String begin;
    private String end;
    private String price;
}
