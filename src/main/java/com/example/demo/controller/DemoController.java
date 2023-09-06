package com.example.demo.controller;

import com.example.demo.aggregate.Aggregate;
import com.example.demo.command.BalanceVariationStatus;
import com.example.demo.command.CreateUserCommand;
import com.example.demo.command.BalanceVariationCommand;
import com.example.demo.es.events.DecreasedUserPointsEvent;
import com.example.demo.es.queries.User;
import com.example.demo.es.repository.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class DemoController {

    @Autowired
    private final Aggregate aggregate;
    //private final UserService userService;

    public DemoController(Aggregate aggregate) {
        this.aggregate = aggregate;
        //  this.userService = userService;
    }


    //@PostMapping("/{userId}")
    //@ResponseStatus(HttpStatus.CREATED)
    //public User create(@RequestBody User user) throws Exception {
    //    return userService.add(user);
    //}

    @GetMapping("/read/{documentId}")
    public Long read(@PathVariable("documentId") String documentId) throws Exception {
        return aggregate.handleRead(documentId);
    }

    @GetMapping("/read/")
    public Flux<UserDocument> read() throws Exception {
        return aggregate.read();
    }

    @PostMapping("{userId}/points/add/{points}")
    public User addUserPoints(
            @PathVariable("userId") String userId, @PathVariable("points") Integer points) throws Exception {
        return aggregate.handleUserPointsEvent(new BalanceVariationCommand(userId, points, BalanceVariationStatus.POSITIVE_VARIATION));
    }

    @PostMapping("{userId}/points/remove/{points}")
    public User removeUserPoints(
            @PathVariable("userId") String userId, @PathVariable("points") Integer points) throws Exception {
        return aggregate.handleUserPointsEvent(new BalanceVariationCommand(userId, points, BalanceVariationStatus.NEGATIVE_VARIATION));
    }

    @PostMapping("/{userId}")
    public User createUser(@PathVariable("userId") String userId) throws Exception {
        return aggregate.handleCreateUserCommand(new CreateUserCommand(userId));
    }

}
