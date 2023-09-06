package com.example.demo.es.service;

import com.example.demo.es.events.Event;
import com.example.demo.es.events.UserCreatedEvent;
import com.example.demo.es.repository.EventRepository;
import com.example.demo.es.queries.User;
import com.example.demo.es.repository.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.Objects;
import java.util.Optional;


@org.springframework.stereotype.Service
public class Service {

    private final EventRepository<UserDocument> repository;

    public Service(EventRepository<UserDocument> repository) {
        this.repository = repository;
    }


    public Flux<UserDocument> getAll() throws Exception {
        return repository.findAll();
    }
    public Long get(String id) throws Exception {

        if (!StringUtils.hasText(id)) {
            throw new Exception();
        }
        return repository.count().block();
    }

    public void add(User user) throws Exception {

        UserDocument doc =
                Optional.ofNullable(user)
                        .map(u -> new UserDocument(u.getUserId(), 0))
                        .orElseThrow(Exception::new);

        final var id = Objects.requireNonNull(repository.save(doc).block()).getUserId();
        /*user.getUserId();

        return user;*/
    }
}
