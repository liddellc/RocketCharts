# Altimieter Data Parser

Written to be run as a GCP cloud function that is invoked any time a new file is uploaded into the /inbox folder.

# Run local

## Env. Variables

You'll need to define the following environment variables.

```
export GOOGLE_APPLICATION_CREDENTIALS="{path_to_your_credentials_json_file}"
export GCP_PROJECT_ID={your_project_id_here}
```

For more information on how to create your application credentials file visit [this page](https://cloud.google.com/docs/authentication/application-default-credentials).

## Starting the local cloud function server

To run the cloud function locally execute the following command. This uses the [Function Framework for Java](https://github.com/GoogleCloudPlatform/functions-framework-java#configuration-on-the-command-line) to run a local web server that listens for cloud events.

```
mvn function:run
```

## Upload a file to Cloud Storage

In the cloud storage bucket for your project, create two folders called `/inbox` and `/processed`. Upload a test file to the `/inbox` folder

## Invoke the Cloud Function

In a separate terminal window, run the following command.

```
curl localhost:8080 \
  -X POST \
  -H "Content-Type: application/json" \
  -d '{
        "context": {
          "eventId": "1147091835525187",
          "timestamp": "2020-04-23T07:38:57.772Z",
          "eventType": "google.storage.object.finalize",
          "resource": {
             "service": "storage.googleapis.com",
             "name": "projects/_/buckets/MY_BUCKET/MY_FILE.txt",
             "type": "storage#object"
          }
        },
        "data": {
          "bucket": "{your_bucked_id_here}",
          "contentType": "text/plain",
          "kind": "storage#object",
          "md5Hash": "...",
          "metageneration": "1",
          "name": "inbox/{your_test_filename_here}",
          "size": "352",
          "storageClass": "MULTI_REGIONAL",
          "timeCreated": "2020-04-23T07:38:57.230Z",
          "timeStorageClassUpdated": "2020-04-23T07:38:57.230Z",
          "updated": "2020-04-23T07:38:57.230Z"
        }
      }'
```

From my own experience, only the `bucket` and `name` fields under the `data` element matter when running this locally.
