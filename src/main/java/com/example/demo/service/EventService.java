package com.example.demo.service;

import com.example.demo.dto.event.BaseEvent;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public interface EventService {
  List<BaseEvent> getEventsByUserId(Long userId) throws ExecutionException, InterruptedException;

  List<BaseEvent> getEventsByUserIdAndDate(Long userId, Date date)
      throws ExecutionException, InterruptedException;

  void createUserEvent(Long userId) throws ExecutionException, InterruptedException;

  void addUserPointsEvent(Long userId, Integer points)
      throws ExecutionException, InterruptedException;

  void removeUserPointsEvent(Long userId, Integer points)
      throws ExecutionException, InterruptedException;
}
