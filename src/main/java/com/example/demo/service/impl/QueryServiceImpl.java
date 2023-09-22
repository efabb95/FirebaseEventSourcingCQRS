package com.example.demo.service.impl;

import com.example.demo.aggregate.EventHandler;
import com.example.demo.aggregate.UserModel;
import com.example.demo.dto.Snapshot;
import com.example.demo.service.EventService;
import com.example.demo.service.QueryService;
import com.example.demo.service.SnapshotService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class QueryServiceImpl implements QueryService {

  private final EventService eventService;
  private final EventHandler eventHandler;
  private final SnapshotService snapshotService;

  public QueryServiceImpl(
          EventService eventService,
      EventHandler eventHandler,
      SnapshotService snapshotService) {
    this.eventService = eventService;
    this.eventHandler = eventHandler;
    this.snapshotService = snapshotService;
  }

  @Override
  public UserModel getUser(Long userId) throws ExecutionException, InterruptedException {
    Snapshot snapshot = snapshotService.getSnapshot(userId);
    if (snapshot != null){
      return getFromSnapshot(snapshot);
    }
    return this.getFromHistory(userId);
  }

  private UserModel getFromSnapshot(Snapshot snapshot) throws ExecutionException, InterruptedException {
    final Long userId = snapshot.getUserId();
    eventService
            .getEventsByUserIdAndDate(userId, snapshot.getLastEventDate())
            .forEach(event -> eventHandler.handle(event, snapshot));
    return snapshot.toUserModel();
  }

  private UserModel getFromHistory(Long userId) throws ExecutionException, InterruptedException {
    UserModel userModel = new UserModel();
    eventService.getEventsByUserId(userId).forEach(event -> eventHandler.handle(event, userModel));
    return userModel;
  }
}
