package com.flight.flight.dto;

import lombok.Data;

@Data
public class UserAddDTO {
    private String account;
    private String name;
    private String address;
    private String sex;
    private Integer age;
}
