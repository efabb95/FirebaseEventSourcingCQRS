package com.example.demo.service;

import com.example.demo.dto.BaseEvent;
import com.example.demo.model.UserModel;

public interface EventHandlerService {

    void handle(BaseEvent event, UserModel aggregate);

}
