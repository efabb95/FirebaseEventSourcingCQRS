package com.example.demo.service.impl;

import com.example.demo.aggregate.EventHandler;
import com.example.demo.data.EventRepository;
import com.example.demo.data.SnapshotRepository;
import com.example.demo.dto.Snapshot;
import com.example.demo.dto.event.BaseEvent;
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
  private final EventHandler eventHandler;

  public SnapshotServiceImpl(
      EventRepository eventRepository,
      SnapshotRepository snapshotRepository,
      EventHandler eventHandler) {
    this.eventRepository = eventRepository;
    this.snapshotRepository = snapshotRepository;
    this.eventHandler = eventHandler;
  }

  @Override
  public Snapshot getSnapshot(Long userId) throws ExecutionException, InterruptedException {
    return snapshotRepository.getByUserId(userId);
  }

  @Override
  public void saveSnapshot(Long userId, Date eventDate) throws ExecutionException, InterruptedException {
    Snapshot snapshot = snapshotRepository.getByUserId(userId);
    if (snapshot == null) {
      List<BaseEvent> events = eventRepository.getEventsByUserId(userId);
      this.save(events, new Snapshot(this.getLastEventDate(events)));
    } else {
      final long deltaMillis = eventDate.getTime() - snapshot.getLastEventDate().getTime();
      if (deltaMillis > DAY_MILLIS_THRESHOLD) {
        List<BaseEvent> events =
            eventRepository.getEventsByUserIdAndDate(userId, snapshot.getLastEventDate());
        snapshot.setLastEventDate(this.getLastEventDate(events));
        this.save(events, snapshot);
      }
    }
  }

  private void save(List<BaseEvent> events, Snapshot snapshot)
      throws ExecutionException, InterruptedException {
    events.forEach(event -> eventHandler.handle(event, snapshot));
    snapshotRepository.save(snapshot);
  }

  private Date getLastEventDate(List<BaseEvent> events){
    return events.stream().map(BaseEvent::getDate).max(Date::compareTo).get();
  }
}
