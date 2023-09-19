package com.example.demo.data;

import com.example.demo.dto.BaseEvent;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Component
public class EventRepository {

  private static final String COLLECTION_NAME = "event-store";
  private static final String USER_ID = "userId";

  private final Firestore firestore;

  public EventRepository(Firestore firestore) {
    this.firestore = firestore;
  }

  public void save(BaseEvent event) throws ExecutionException, InterruptedException {
    firestore.collection(COLLECTION_NAME).add(event).get();
  }

  public List<BaseEvent> getByUserId(Long userId) throws ExecutionException, InterruptedException {
    CollectionReference collection = firestore.collection(COLLECTION_NAME);
    Query query = collection.whereEqualTo(USER_ID, userId);
    ApiFuture<QuerySnapshot> querySnapshot = query.get();
    List<QueryDocumentSnapshot> documents = querySnapshot.get().getDocuments();
    return documents.stream()
        .map(document -> document.toObject(BaseEvent.class)).sorted(Comparator.comparing(BaseEvent::getDate))
        .collect(Collectors.toList());
  }
}
