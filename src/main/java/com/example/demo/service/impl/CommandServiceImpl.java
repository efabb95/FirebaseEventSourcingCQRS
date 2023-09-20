package com.example.demo.service.impl;

import com.example.demo.constants.EventType;
import com.example.demo.data.EventRepository;
import com.example.demo.dto.BalanceVariationEvent;
import com.example.demo.dto.BaseEvent;
import com.example.demo.model.UserModel;
import com.example.demo.service.CommandService;
import com.example.demo.service.SnapshotService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.ExecutionException;

@Service
public class CommandServiceImpl implements CommandService {

    private final static long DAY_MILLIS_THRESHOLD = 24*60*60*1000;
    private final EventRepository eventRepository;
    private final SnapshotService snapshotService;

    public CommandServiceImpl(EventRepository eventRepository, SnapshotService snapshotService) {
        this.eventRepository = eventRepository;
        this.snapshotService = snapshotService;
    }

    @Override
    public void createUser(Long userId) throws ExecutionException, InterruptedException {
        BaseEvent event = new BaseEvent(userId, EventType.CREATE_USER_EVENT, null);
        saveAndUpdateSnapshot(event);
    }

    @Override
    public void addUserPoints(Long userId, Integer points) throws ExecutionException, InterruptedException {
        BalanceVariationEvent balanceVariationEvent = new BalanceVariationEvent(points);
        BaseEvent event = new BaseEvent(userId, EventType.ADD_USER_POINTS, balanceVariationEvent);
        saveAndUpdateSnapshot(event);
    }

    @Override
    public void removeUserPoints(Long userId, Integer points) throws ExecutionException, InterruptedException {
        BalanceVariationEvent balanceVariationEvent = new BalanceVariationEvent(points);
        BaseEvent event = new BaseEvent(userId, EventType.REMOVE_USER_POINTS, balanceVariationEvent);
        saveAndUpdateSnapshot(event);
    }

    private void saveAndUpdateSnapshot(BaseEvent event) throws ExecutionException, InterruptedException {
        eventRepository.saveEvent(event);
        snapshotService.saveSnapshot(event.getUserId(), event.getDate());
    }


}
