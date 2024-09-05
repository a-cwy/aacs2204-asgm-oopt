package fileHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Scanner;

public class FileHandler {
    static private final ObjectMapper mapper = new ObjectMapper();

    static public void writeObjectToFile(Object obj, String dir) throws JsonProcessingException {
        String json = mapper.writeValueAsString(obj);
        System.out.println(json);

        try {
            File f = new File(dir);
            if (f.createNewFile()) {
                System.out.println("File created: " + f.getAbsolutePath());
            } else {
                System.out.println("File already exists: " + f.getAbsolutePath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileWriter fw = new FileWriter(dir);
            fw.write(json);
            fw.close();
            System.out.println("File written.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static public Object readObjectFromFile(String dir,  Type T) throws JsonProcessingException {
        String json = "";

        try {
            File f = new File(dir);
            Scanner reader = new Scanner(f);
            json = reader.nextLine();

            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Object retval;
        retval = mapper.readValue(json, mapper.constructType(T));
        return retval;
    }
}
