package com.example.demo.service.impl;

import com.example.demo.constants.EventType;
import com.example.demo.data.EventRepository;
import com.example.demo.dto.BalanceVariationEvent;
import com.example.demo.dto.BaseEvent;
import com.example.demo.service.CommandService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class CommandServiceImpl implements CommandService {

    private final EventRepository eventRepository;

    public CommandServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public void createUser(Long userId) throws ExecutionException, InterruptedException {
        BaseEvent event = new BaseEvent(userId, EventType.CREATE_USER_EVENT, null);
        eventRepository.save(event);
    }

    @Override
    public void addUserPoints(Long userId, Integer points) throws ExecutionException, InterruptedException {
        BalanceVariationEvent balanceVariationEvent = new BalanceVariationEvent(points);
        BaseEvent event = new BaseEvent(userId, EventType.ADD_USER_POINTS, balanceVariationEvent);
        eventRepository.save(event);
    }

    @Override
    public void removeUserPoints(Long userId, Integer points) throws ExecutionException, InterruptedException {
        BalanceVariationEvent balanceVariationEvent = new BalanceVariationEvent(points);
        BaseEvent event = new BaseEvent(userId, EventType.REMOVE_USER_POINTS, balanceVariationEvent);
        eventRepository.save(event);
    }
}
