package com.flight.flight.service;

import com.flight.flight.bo.Result;
import com.flight.flight.ibo.DrugAddIBO;
import com.flight.flight.ibo.UserAddIBO;


public interface InfoService {

    Result list();
    Result add(UserAddIBO userAddIBO);
    Result del(String id);
    Result search(String name);
}
