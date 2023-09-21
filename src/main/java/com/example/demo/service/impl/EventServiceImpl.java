package com.example.demo.service.impl;

import com.example.demo.constants.EventType;
import com.example.demo.data.EventRepository;
import com.example.demo.dto.event.BalanceVariation;
import com.example.demo.dto.event.BaseEvent;
import com.example.demo.service.EventService;
import com.example.demo.service.SnapshotService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class EventServiceImpl implements EventService {

  private final EventRepository eventRepository;
  private final SnapshotService snapshotService;

  public EventServiceImpl(EventRepository eventRepository, SnapshotService snapshotService) {
    this.eventRepository = eventRepository;
    this.snapshotService = snapshotService;
  }

  @Override
  public List<BaseEvent> getEventsByUserId(Long userId) throws ExecutionException, InterruptedException {
    return eventRepository.getEventsByUserId(userId);
  }

  @Override
  public List<BaseEvent> getEventsByUserIdAndDate(Long userId, Date date)
      throws ExecutionException, InterruptedException {
    return eventRepository.getEventsByUserIdAndDate(userId, date);
  }

  @Override
  public void createUserEvent(Long userId) throws ExecutionException, InterruptedException {
    BaseEvent event = new BaseEvent(userId, EventType.CREATE_USER_EVENT, null);
    saveEventAndSnapshot(event);
  }

  @Override
  public void addUserPointsEvent(Long userId, Integer points)
      throws ExecutionException, InterruptedException {
    BalanceVariation balanceVariation = new BalanceVariation(points);
    BaseEvent event = new BaseEvent(userId, EventType.ADD_USER_POINTS, balanceVariation);
    saveEventAndSnapshot(event);
  }

  @Override
  public void removeUserPointsEvent(Long userId, Integer points)
      throws ExecutionException, InterruptedException {
    BalanceVariation balanceVariation = new BalanceVariation(points);
    BaseEvent event = new BaseEvent(userId, EventType.REMOVE_USER_POINTS, balanceVariation);
    saveEventAndSnapshot(event);
  }

  private void saveEventAndSnapshot(BaseEvent event)
      throws ExecutionException, InterruptedException {
    eventRepository.saveEvent(event);
    snapshotService.saveSnapshot(event.getUserId(), event.getDate());
  }
}