package com.example.demo.service.impl;

import com.example.demo.data.EventRepository;
import com.example.demo.dto.BaseEvent;
import com.example.demo.model.UserModel;
import com.example.demo.service.EventHandlerService;
import com.example.demo.service.QueryService;
import com.example.demo.service.SnapshotService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class QueryServiceImpl implements QueryService {

    private final EventRepository eventRepository;
    private final EventHandlerService eventHandlerService;
    private final SnapshotService snapshotService;

    public QueryServiceImpl(EventRepository eventRepository, EventHandlerService eventHandlerService, SnapshotService snapshotService) {
        this.eventRepository = eventRepository;
        this.eventHandlerService = eventHandlerService;
        this.snapshotService = snapshotService;
    }


    @Override
    public UserModel getUser(Long userId) throws ExecutionException, InterruptedException {
        UserModel snapshot = snapshotService.getSnapshot(userId);
        final Date date = snapshot.getLastEventDate();
        List<BaseEvent> events = eventRepository.getEventsByUserIdAndDate(userId, date);
        events.forEach(event-> eventHandlerService.handle(event, snapshot));
        return snapshot;
    }
}
