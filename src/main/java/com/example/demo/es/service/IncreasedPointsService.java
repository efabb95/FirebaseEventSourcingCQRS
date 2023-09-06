package com.example.demo.es.service;

import com.example.demo.es.events.IncreasedUserPointsEvent;
import com.example.demo.es.queries.User;
import com.example.demo.es.repository.EventRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;
import java.util.Optional;

@Service
public class IncreasedPointsService {
    private final EventRepository<IncreasedUserPointsEvent> repository;

    public IncreasedPointsService(EventRepository<IncreasedUserPointsEvent> repository) {
        this.repository = repository;
    }

    public IncreasedUserPointsEvent get(String id) throws Exception {

        if (!StringUtils.hasText(id)) {
            throw new Exception();
        }

        return repository
                .findById(id)
                .map(doc -> new IncreasedUserPointsEvent(doc.getUserId(), doc.getPoints()))
                .block();
    }

    public void add(User user) throws Exception {
        IncreasedUserPointsEvent doc =
                Optional.ofNullable(user)
                        .map(u -> new IncreasedUserPointsEvent(u.getUserId(), u.getPoints()))
                        .orElseThrow(Exception::new);

        final var id = Objects.requireNonNull(repository.save(doc).block()).getUserId();
    }
}