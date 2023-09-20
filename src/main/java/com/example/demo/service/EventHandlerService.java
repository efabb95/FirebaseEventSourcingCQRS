package com.example.demo.service;

import com.example.demo.dto.BaseEvent;
import com.example.demo.aggregate.UserModel;

public interface EventHandlerService {

    void handle(BaseEvent event, UserModel aggregate);

}
