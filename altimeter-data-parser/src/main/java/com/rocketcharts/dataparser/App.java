package com.rocketcharts.dataparser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Flight flight = null;

        try {
            if(args.length > 0)
                flight = new Flight(args[0]);

            if (flight != null) {
                flight.readFile();
            }

            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(System.out, flight);
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
