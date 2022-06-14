package com.flight.flight.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    String name;
    String account;
    String password;
    String check_password;
}
