package com.flight.flight.service;

import com.flight.flight.bo.Result;
import com.flight.flight.entity.Help;
import com.flight.flight.ibo.HelpAddIBO;
import com.flight.flight.ibo.HelpAgreeIBO;
import com.flight.flight.ibo.HelpMessageIBO;

import java.util.List;

public interface HelpService {
    Result list();

    Result add(HelpAddIBO helpAddIBO);

    Result agree(HelpAgreeIBO helpAgreeIBO);

    Result message(HelpMessageIBO helpMessageIBO);

    Result history(String id);

    Result release(String id);

    Result cancel(String id);

    Result complete(String id);
}
