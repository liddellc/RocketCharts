package com.rocketcharts.dataparser;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger logger = Logger.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        Flight flight = null;

        if(args.length > 0)
            flight = new Flight(args[0]);

        if (flight != null) {
            flight.readFile();
        }

        File f = new File(args[0].replace(".csv", ".json"));
        try( FileOutputStream fos = new FileOutputStream(f); ) {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(fos, flight);
        } catch(Exception e) {
            logger.log(Level.SEVERE, "Error encountered.", e);
        }
    }
}
