package com.rocketcharts.dataparser.output;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.rocketcharts.dataparser.Flight;

public class FirestoreOutput {
    private Firestore firestoreDB;
    
    public FirestoreOutput(String projectId) {
        FirestoreOptions firestoreOptions;
        try {
            firestoreOptions = FirestoreOptions.getDefaultInstance().toBuilder()
                .setProjectId(projectId)
                .setCredentials(GoogleCredentials.getApplicationDefault())
                .build();
            firestoreDB = firestoreOptions.getService();
        }
        catch(Exception e) {
            handleException(e);
        }
    }

    public boolean write(Flight flight) {
        boolean result = true;

        try {
            DocumentReference docRef = firestoreDB.collection("flights").document();
            //asynchronously write data
            ApiFuture<WriteResult> wResult = docRef.set(flight);
            Logger.getLogger(getClass().getName()).info("Update time : " + wResult.get().getUpdateTime());
        }
        catch(Exception e) {
            handleException(e);
            Thread.currentThread().interrupt();
            result = false;
        }

        return result;
    }

    private void handleException(Exception e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while writing data to Firestore DB.", e);
    }

    public Firestore getFirestoreDB() { return firestoreDB; }
}
