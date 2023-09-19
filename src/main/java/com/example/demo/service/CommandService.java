package com.example.demo.service;

import java.util.concurrent.ExecutionException;

public interface CommandService {
    void createUser(Long userId) throws ExecutionException, InterruptedException;
    void addUserPoints(Long userId, Integer points) throws ExecutionException, InterruptedException;
    void removeUserPoints(Long userId, Integer points) throws ExecutionException, InterruptedException;
}
