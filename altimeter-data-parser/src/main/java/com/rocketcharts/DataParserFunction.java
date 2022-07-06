package com.rocketcharts;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.rocketcharts.models.events.GcsEvent;
import com.rocketcharts.output.FirestoreOutput;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Example Cloud Storage-triggered function.
 * This function can process any event from Cloud Storage.
 */
public class DataParserFunction implements BackgroundFunction<GcsEvent> {
  private static final Logger logger = Logger.getLogger(DataParserFunction.class.getName());

  @Override
  public void accept(GcsEvent event, Context context) {
    logger.log(Level.INFO, "Event: {0}", context.eventId());
    logger.log(Level.INFO, "Event Type: {0}", context.eventType());
    logger.info("Bucket: " + event.getBucket());
    logger.info("File: " + event.getName());
    logger.info("Metageneration: " + event.getMetageneration());
    logger.info("Created: " + event.getTimeCreated());
    logger.info("Updated: " + event.getUpdated());

    String projectId = System.getenv("GCP_PROJECT_ID");
    logger.log(Level.INFO, "ProjectId: {0}", projectId);
 
    if(context.eventType().equals("google.storage.object.finalize") && event.getName().startsWith("inbox/")) {
        Flight flight = new Flight(projectId, event.getBucket(), event.getName());
        flight.readFile();

        FirestoreOutput fsOutput = new FirestoreOutput(projectId);
        fsOutput.write(flight);

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId source = BlobId.of(event.getBucket(), event.getName());
        BlobId dest = BlobId.of(event.getBucket(), event.getName().replace("inbox", "processed"));
        
        Blob blob = storage.get(source);
        blob.copyTo(dest);
        blob.delete();
    }
  }
}