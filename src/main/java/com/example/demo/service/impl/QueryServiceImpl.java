package com.example.demo.service.impl;

import com.example.demo.data.EventRepository;
import com.example.demo.dto.BaseEvent;
import com.example.demo.model.UserModel;
import com.example.demo.service.EventHandlerService;
import com.example.demo.service.QueryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class QueryServiceImpl implements QueryService {

    private final EventRepository eventRepository;
    private final EventHandlerService eventHandlerService;

    public QueryServiceImpl(EventRepository eventRepository, EventHandlerService eventHandlerService) {
        this.eventRepository = eventRepository;
        this.eventHandlerService = eventHandlerService;
    }


    @Override
    public UserModel getUser(Long userId) throws ExecutionException, InterruptedException {
        List<BaseEvent> events = eventRepository.getByUserId(userId);
        UserModel userModel = new UserModel();
        events.forEach(event-> eventHandlerService.handle(event, userModel));
        return userModel;
    }
}
