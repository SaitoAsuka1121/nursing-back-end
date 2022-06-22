package com.flight.flight.service;

import com.flight.flight.bo.Result;
import com.flight.flight.ibo.CaseAddIBO;
import com.flight.flight.ibo.DrugAddIBO;

public interface DrugService {
    Result list();
    Result add(DrugAddIBO caseAddIBO);
    Result del(String id);
    Result search(String name);
}
