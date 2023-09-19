package com.example.demo.service;

import com.example.demo.model.UserModel;

import java.util.concurrent.ExecutionException;

public interface QueryService {

    UserModel getUser(Long userId) throws ExecutionException, InterruptedException;
}
