package com.flight.flight.test;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.ToString;

import java.util.Locale;
@ToString
@Data
public class Fake {
    String name;
    String account;
    String password;
    String check_password;
    public Fake faker() {
        Faker faker = new Faker(Locale.CHINA);
        Fake a = new Fake();
        a.name = faker.name().fullName();
        a.account = faker.phoneNumber().cellPhone();
        a.password = faker.number().digits(10);
        a.check_password = a.password;
        return a;
    }

}
