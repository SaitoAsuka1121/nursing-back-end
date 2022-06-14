package com.flight.flight.service;

import com.flight.flight.bo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface InfoService {

    /**
     * 获取老人信息
     */
    Result elder();
}
