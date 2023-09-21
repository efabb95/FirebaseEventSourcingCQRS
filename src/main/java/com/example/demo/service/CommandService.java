package com.example.demo.service;

public interface CommandService {
  void createUser(Long userId) throws Exception;

  void addUserPoints(Long userId, Integer points) throws Exception;

  void removeUserPoints(Long userId, Integer points)
          throws Exception;
}
