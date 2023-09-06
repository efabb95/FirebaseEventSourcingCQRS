package com.example.demo.es.service;

import com.example.demo.es.events.DecreasedUserPointsEvent;
import com.example.demo.es.events.Event;
import com.example.demo.es.queries.User;
import com.example.demo.es.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.Optional;

@Service
public class DecreasedPointsService {
    private final EventRepository<DecreasedUserPointsEvent> repository;

    public DecreasedPointsService(EventRepository<DecreasedUserPointsEvent> repository) {
        this.repository = repository;
    }

    public DecreasedUserPointsEvent get(String documentId) throws Exception {

        if (!StringUtils.hasText(documentId)) {
            throw new Exception();
        }

        return repository.findById(documentId).map(doc -> new DecreasedUserPointsEvent(doc.getUserId(),doc.getPoints())).block();
        /*return repository
                .findById(id)
                .map(doc -> new DecreasedUserPointsEvent(doc.getUserId(), doc.getPoints()))
                .block();*/
    }

    public void add(User user) throws Exception {
        DecreasedUserPointsEvent doc =
                Optional.ofNullable(user)
                        .map(u -> new DecreasedUserPointsEvent(u.getUserId(), u.getPoints()))
                        .orElseThrow(Exception::new);

        final var id = Objects.requireNonNull(repository.save(doc).block()).getUserId();
    }
}