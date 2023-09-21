package com.example.demo.service;

import java.util.concurrent.ExecutionException;

public interface CommandService {
  void createUser(Long userId) throws Exception;

  void addUserPoints(Long userId, Integer points) throws Exception;

  void removeUserPoints(Long userId, Integer points)
          throws Exception;
}
