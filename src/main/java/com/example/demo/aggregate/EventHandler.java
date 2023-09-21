package com.example.demo.aggregate;

import com.example.demo.dto.event.BalanceVariation;
import com.example.demo.dto.event.BaseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import static com.example.demo.constants.EventType.*;

@Component
public class EventHandler {

  private final ObjectMapper mapper = new ObjectMapper();

  public void handle(BaseEvent event, UserModel aggregate) {
    switch (event.getEventType()) {
      case CREATE_USER_EVENT:
        aggregate.handleUserCreationEvent(event.getUserId());
        break;
      case ADD_USER_POINTS:
        aggregate.handleAddPointsEvent(
            getBalanceVariation(event).getPoints());
        break;
      case REMOVE_USER_POINTS:
        aggregate.handleRemovePointsEvent(
            getBalanceVariation(event).getPoints());
        break;
      default:
        break;
    }
  }

  private BalanceVariation getBalanceVariation(BaseEvent event) {
    return mapper.convertValue(event.getEventPayload(), BalanceVariation.class);
  }
}
