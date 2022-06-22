package com.rocketcharts.dataparser;

import com.rocketcharts.dataparser.output.FirestoreOutput;
import com.rocketcharts.dataparser.output.JsonOutput;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        String projectId = System.getenv("GCP_PROJECT_ID");
 
        if(args.length != 1)
            return;

        Flight flight = new Flight(args[0]);
        flight.readFile();

        JsonOutput jsonOutput = new JsonOutput(args[0]);
        jsonOutput.write(flight);

        FirestoreOutput fsOutput = new FirestoreOutput(projectId);
        fsOutput.write(flight);
    }
}
