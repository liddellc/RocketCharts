package com.rocketcharts.output;

import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.rocketcharts.Flight;

public class JsonOutput {
    private String fileName;
    private FileWriter fileWriter;

    public JsonOutput(String fileName) {
        File f = new File(fileName.replace(".csv", ".json"));
        try{
            fileWriter = new FileWriter(f);
        } catch(Exception e) {
            handleException(e);
        }

    }

    public boolean write(Flight flight) {
        boolean result = true;

        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(fileWriter, flight);    
        }
        catch(Exception e) {
            handleException(e);
            result = false;
        }

        return result;
    }
 
    private void handleException(Exception e) {
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, "Error while writing JSON data.", e);
    }

    public String getFileName() { return fileName; }
    public FileWriter getFileWriter() { return fileWriter; }
}
