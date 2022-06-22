package com.flight.flight.service;

import com.flight.flight.bo.Result;
import com.flight.flight.ibo.CaseAddIBO;
import com.flight.flight.ibo.HelpAddIBO;

public interface CaseService {
    Result list();
    Result add(CaseAddIBO caseAddIBO);
    Result del(String id);
}
