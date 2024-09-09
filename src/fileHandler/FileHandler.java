package fileHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileHandler {
    static private final ObjectMapper mapper = new ObjectMapper();

    static public void writeObjectToFile(Object obj, String dir) throws JsonProcessingException {
        String json = mapper.writeValueAsString(obj);

        try {
            File f = new File(dir);
            f.createNewFile();
        } catch (IOException _) { }

        try {
            FileWriter fw = new FileWriter(dir);
            fw.write(json);
            fw.close();
        } catch (IOException _) {}
    }

    static public <T> T readObjectFromFile(T obj, String dir) throws JsonProcessingException {
        String json = "";

        try {
            File f = new File(dir);
            Scanner reader = new Scanner(f);
            json = reader.nextLine();

            reader.close();
        } catch (FileNotFoundException _) {}

        if (json.isEmpty()) {
            return obj;
        }

        return mapper.readValue(json, mapper.constructType(obj.getClass()));
    }
}
