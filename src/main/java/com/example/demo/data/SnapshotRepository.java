package com.example.demo.data;

import com.example.demo.aggregate.UserModel;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;

@Component
public class SnapshotRepository {

    private static final String SNAPSHOT_COLLECTION_NAME = "snapshot-store";
    private static final String USER_ID = "userId";
    private final Firestore firestore;


    public SnapshotRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public UserModel getByUserId(Long userId) throws ExecutionException, InterruptedException {
        CollectionReference collection = firestore.collection(SNAPSHOT_COLLECTION_NAME);
        Query query = collection.whereEqualTo(USER_ID, userId).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        QuerySnapshot result = querySnapshot.get();
        if (!result.isEmpty()) {
            QueryDocumentSnapshot querySnapshotResult = result.getDocuments().get(0);
            return querySnapshotResult.toObject(UserModel.class);
        } else {
            return null;
        }
    }

    public void save(UserModel snapshot) throws ExecutionException, InterruptedException {
        CollectionReference collection = firestore.collection(SNAPSHOT_COLLECTION_NAME);
        Query query = collection.whereEqualTo(USER_ID, snapshot.getUserId()).limit(1);
        ApiFuture<QuerySnapshot> querySnapshot = query.get();
        QuerySnapshot result = querySnapshot.get();
        if (!result.isEmpty()) {
            QueryDocumentSnapshot querySnapshotResult = result.getDocuments().get(0);
            querySnapshotResult.getReference().update(snapshot.toMap()).get();
        }else{
            firestore.collection(SNAPSHOT_COLLECTION_NAME).add(snapshot).get();
        }
    }
}
