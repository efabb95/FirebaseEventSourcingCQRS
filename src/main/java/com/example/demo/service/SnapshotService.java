package com.example.demo.service;

import com.example.demo.dto.Snapshot;

import java.util.concurrent.ExecutionException;

public interface SnapshotService {

    Snapshot getSnapshot(Long userId) throws ExecutionException, InterruptedException;

    void saveSnapshot(Long userId) throws ExecutionException, InterruptedException;
}
