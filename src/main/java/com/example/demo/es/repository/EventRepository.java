package com.example.demo.es.repository;

import com.example.demo.es.events.Event;
import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository<T> extends FirestoreReactiveRepository<T> {}