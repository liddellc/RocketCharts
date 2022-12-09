package com.rocketcharts;

import com.google.cloud.functions.BackgroundFunction;
import com.google.cloud.functions.Context;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.rocketcharts.exceptions.InvalidAltimeterException;
import com.rocketcharts.models.FlightData;
import com.rocketcharts.models.events.GcsEvent;
import com.rocketcharts.output.FirestoreOutput;
import com.google.cloud.ReadChannel;

import java.io.BufferedReader;
import java.nio.channels.Channels;
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

    String prefix = "inbox/";

    if (context.eventType().equals("google.storage.object.finalize") && event.getName().startsWith(prefix)) {
      AltimeterDataFile altDataFile;
      try {
        altDataFile = new AltimeterDataFile(getReader(projectId, event.getBucket(), event.getName()));
        FlightData flightData = altDataFile.parseFile();
        flightData.setMetaData(altDataFile.parseMetaDataFromFilename(event.getName().substring(prefix.length())));

        FirestoreOutput fsOutput = new FirestoreOutput(projectId);
        fsOutput.write(flightData);

        Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
        BlobId source = BlobId.of(event.getBucket(), event.getName());
        BlobId dest = BlobId.of(event.getBucket(), event.getName().replace("inbox", "processed"));

        Blob blob = storage.get(source);
        blob.copyTo(dest);
        blob.delete();
      } catch (InvalidAltimeterException e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage());
      }
    }
  }

  private BufferedReader getReader(String projectId, String bucketName, String objectName) {
    if (projectId == null || bucketName == null || objectName == null)
      return null;

    try {
      Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
      ReadChannel sReader = storage.reader(BlobId.of(bucketName, objectName));
      return new BufferedReader(Channels.newReader(sReader, "UTF8"));

    } catch (Exception e) {
      return null;
    }

  }
}