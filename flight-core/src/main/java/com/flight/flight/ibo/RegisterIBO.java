package com.flight.flight.ibo;




/**
 * @author liu
 */
import lombok.Data;

@Data
public class RegisterIBO {
    String name;
    String account;
    String password;
    String check_password;
}
