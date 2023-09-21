package com.example.demo.service.impl;

import com.example.demo.dto.event.BalanceVariationEvent;
import com.example.demo.dto.event.BaseEvent;
import com.example.demo.aggregate.UserModel;
import com.example.demo.service.EventHandlerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import static com.example.demo.constants.EventType.*;

@Service
public class EventHandlerServiceImpl implements EventHandlerService {

  private final ObjectMapper mapper = new ObjectMapper();

  @Override
  public void handle(BaseEvent event, UserModel aggregate) {
    switch (event.getEventType()) {
      case CREATE_USER_EVENT:
        aggregate.handleUserCreationEvent(event.getUserId());
        break;
      case ADD_USER_POINTS:
        aggregate.handleAddPointsEvent(
            getBalanceVariationEvent(event).getPoints());
        break;
      case REMOVE_USER_POINTS:
        aggregate.handleRemovePointsEvent(
            getBalanceVariationEvent(event).getPoints());
        break;
      default:
        break;
    }
  }

  private BalanceVariationEvent getBalanceVariationEvent(BaseEvent event) {
    return mapper.convertValue(event.getEventPayload(), BalanceVariationEvent.class);
  }
}
