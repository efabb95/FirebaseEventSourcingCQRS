package com.example.demo.service;

import com.example.demo.model.UserModel;

import java.util.Date;
import java.util.concurrent.ExecutionException;

public interface SnapshotService {

    UserModel getSnapshot(Long userId) throws ExecutionException, InterruptedException;

    void saveSnapshot(Long userId, Date eventDate) throws ExecutionException, InterruptedException;
}
