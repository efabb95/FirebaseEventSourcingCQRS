package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.service.CommandService;
import com.example.demo.service.QueryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class DemoController {

    private final CommandService commandService;
    private final QueryService queryService;

    public DemoController(CommandService commandService, QueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }


    @PostMapping(value="{userId}/points/add/{points}")
    public void addUserPoints(
            @PathVariable("userId") Long userId, @PathVariable("points") Integer points) throws Exception {
        commandService.addUserPoints(userId, points);
    }

    @PostMapping(value="{userId}/points/remove/{points}")
    public void removeUserPoints(
            @PathVariable("userId") Long userId, @PathVariable("points") Integer points) throws Exception {
        commandService.removeUserPoints(userId, points);
    }

    @PostMapping(value="/{userId}")
    public void createUser(@PathVariable("userId") Long userId) throws Exception {
        commandService.createUser(userId);
    }

    @GetMapping(value = "/{userId}")
    public UserModel getUser(@PathVariable("userId") Long userId)throws Exception{
        return queryService.getUser(userId);
    }



}
