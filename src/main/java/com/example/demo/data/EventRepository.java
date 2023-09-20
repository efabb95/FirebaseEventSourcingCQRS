package com.example.demo.data;

import com.example.demo.dto.event.BaseEvent;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class EventRepository {

  private static final String EVENT_COLLECTION_NAME = "event-store";
  private static final String USER_ID = "userId";
  private static final String DATE_FIELD = "date";

  private final Firestore firestore;

  public EventRepository(Firestore firestore) {
    this.firestore = firestore;
  }

  public void saveEvent(BaseEvent event) throws ExecutionException, InterruptedException {
    firestore.collection(EVENT_COLLECTION_NAME).add(event).get();
  }

  public List<BaseEvent> getEventsByUserId(Long userId)
      throws ExecutionException, InterruptedException {
    CollectionReference collection = firestore.collection(EVENT_COLLECTION_NAME);
    Query query =
        collection.whereEqualTo(USER_ID, userId).orderBy(DATE_FIELD, Query.Direction.ASCENDING);
    ApiFuture<QuerySnapshot> querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
    return documents.stream()
        .map(document -> document.toObject(BaseEvent.class))
        .collect(Collectors.toList());
  }

  public List<BaseEvent> getEventsByUserIdAndDate(Long userId, Date date)
      throws ExecutionException, InterruptedException {
    CollectionReference collection = firestore.collection(EVENT_COLLECTION_NAME);
    Query query =
        collection
            .whereEqualTo(USER_ID, userId)
            .whereGreaterThan(DATE_FIELD, date)
            .orderBy(DATE_FIELD, Query.Direction.ASCENDING);
    ApiFuture<QuerySnapshot> querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
    return documents.stream()
        .map(document -> document.toObject(BaseEvent.class))
        .collect(Collectors.toList());
  }
}
