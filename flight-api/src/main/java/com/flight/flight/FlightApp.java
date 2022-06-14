package com.flight.flight;

import com.flight.flight.controller.UserController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.MessageFormat;
@Slf4j
@EnableSwagger2
@SpringBootApplication

public class FlightApp {
    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(FlightApp.class,args);
        ConfigurableEnvironment env = ctx.getEnvironment();
        log.info(MessageFormat.format("{0}:{1}/swagger-ui/index.html#/","http://localhost",env.getProperty("server.port")));
    }


}
