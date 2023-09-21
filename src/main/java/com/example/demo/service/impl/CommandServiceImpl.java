package com.example.demo.service.impl;

import com.example.demo.dto.Snapshot;
import com.example.demo.service.CommandService;
import com.example.demo.service.EventService;
import com.example.demo.service.SnapshotService;
import org.springframework.stereotype.Service;

@Service
public class CommandServiceImpl implements CommandService {

  private final EventService eventService;
  private final SnapshotService snapshotService;

  public CommandServiceImpl(EventService eventService, SnapshotService snapshotService) {
    this.eventService = eventService;
    this.snapshotService = snapshotService;
  }

  @Override
  public void createUser(Long userId) throws Exception {
    final Snapshot snapshot = snapshotService.getSnapshot(userId);
    if (snapshot != null) {
      throw new Exception("User already created");
    }
    eventService.createUserEvent(userId);
  }

  @Override
  public void addUserPoints(Long userId, Integer points) throws Exception {
    final Snapshot snapshot = snapshotService.getSnapshot(userId);
    if (snapshot == null) {
      eventService.createUserEvent(userId);
    }
    eventService.addUserPointsEvent(userId, points);
  }

  @Override
  public void removeUserPoints(Long userId, Integer points) throws Exception {
    final Snapshot snapshot = snapshotService.getSnapshot(userId);
    if (snapshot == null) {
      eventService.createUserEvent(userId);
    }
    eventService.removeUserPointsEvent(userId, points);
  }
}
