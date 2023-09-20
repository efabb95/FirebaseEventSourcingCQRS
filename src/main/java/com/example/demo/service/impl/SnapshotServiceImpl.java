package com.example.demo.service.impl;

import com.example.demo.data.EventRepository;
import com.example.demo.data.SnapshotRepository;
import com.example.demo.dto.BaseEvent;
import com.example.demo.model.UserModel;
import com.example.demo.service.EventHandlerService;
import com.example.demo.service.SnapshotService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class SnapshotServiceImpl implements SnapshotService {

  //private static final long DAY_MILLIS_THRESHOLD = 24 * 60 * 60 * 1000;
  private static final long DAY_MILLIS_THRESHOLD = 30*1000;
  private final EventRepository eventRepository;
  private final SnapshotRepository snapshotRepository;
  private final EventHandlerService eventHandlerService;

  public SnapshotServiceImpl(
      EventRepository eventRepository,
      SnapshotRepository snapshotRepository,
      EventHandlerService eventHandlerService) {
    this.eventRepository = eventRepository;
    this.snapshotRepository = snapshotRepository;
    this.eventHandlerService = eventHandlerService;
  }

  @Override
  public UserModel getSnapshot(Long userId) throws ExecutionException, InterruptedException {
    return snapshotRepository.getByUserId(userId);
  }

  @Override
  public void saveSnapshot(Long userId, Date eventDate) throws ExecutionException, InterruptedException {
    UserModel snapshot = snapshotRepository.getByUserId(userId);
    if (snapshot == null) {
      snapshot = new UserModel();
      List<BaseEvent> events = eventRepository.getEventsByUserId(userId);
      this.save(events, snapshot);
    } else {
      final Date snapshotDate = snapshot.getLastEventDate();
      long deltaMillis = eventDate.getTime() - snapshotDate.getTime();
      if (deltaMillis > DAY_MILLIS_THRESHOLD) {
        List<BaseEvent> events =
            eventRepository.getEventsByUserIdAndDate(userId, snapshot.getLastEventDate());
        save(events, snapshot);
      }
    }
  }

  private void save(List<BaseEvent> events, UserModel snapshot)
      throws ExecutionException, InterruptedException {
    events.forEach(event -> eventHandlerService.handle(event, snapshot));
    snapshotRepository.save(snapshot);
  }
}
