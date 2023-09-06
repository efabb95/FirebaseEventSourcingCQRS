package com.example.demo.aggregate;

import com.example.demo.command.BalanceVariationCommand;
import com.example.demo.command.BalanceVariationStatus;
import com.example.demo.command.CreateUserCommand;
import com.example.demo.es.events.DecreasedUserPointsEvent;
import com.example.demo.es.queries.User;
import com.example.demo.es.repository.UserDocument;
import com.example.demo.es.service.DecreasedPointsService;
import com.example.demo.es.service.IncreasedPointsService;
import com.example.demo.es.service.Service;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Objects;

@Component
public class Aggregate {

    private final Service userService;
    private final IncreasedPointsService increasedPointsService;
    private final DecreasedPointsService decreasedPointsService;

    public Aggregate(Service userService, IncreasedPointsService increasedPointsService, DecreasedPointsService decreasedPointsService) {
        this.userService = userService;
        this.increasedPointsService = increasedPointsService;
        this.decreasedPointsService = decreasedPointsService;
    }


    public User handleCreateUserCommand(CreateUserCommand command) throws Exception {
        User user = new User(command.getUserId(), 0);
        //writeRepository.addUser(user.getUserid(), user);
        userService.add(user);
        return user;
    }

    public User handleUserPointsEvent(BalanceVariationCommand command) throws Exception {
        final var variationStatus = command.getVariationStatus();
        User user = new User(command.getUserId(),command.getPoints());
        if (Objects.equals(variationStatus, BalanceVariationStatus.POSITIVE_VARIATION)) {
            increasedPointsService.add(user);
        } else if (Objects.equals(variationStatus, BalanceVariationStatus.NEGATIVE_VARIATION)) {
            decreasedPointsService.add(user);
            return user;
        }
        return user;
    }

    public Long handleRead(String documentId) throws Exception {
        return userService.get(documentId);
    }

    public Flux<UserDocument> read() throws Exception {
        return userService.getAll();
    }

}
