package Utils;

import Model.ToDoList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManipulation {

    public ToDoList readFromFile(String fileLocation, ToDoList myTask) {
        Path destination = Paths.get(fileLocation);
        try {
            File file = destination.toFile();
            if (file.exists()) {
                ObjectInputStream is = new ObjectInputStream(new FileInputStream(file));
                myTask = (ToDoList) is.readObject();
                is.close();
            }
        } catch (Exception e) {
            System.err.println("Error when loading file");
            e.printStackTrace();
        }
        return myTask;
    }

    public void saveToFile(String fileName, ToDoList myTask) {
        Path destination = Paths.get(fileName);
        try {
            ObjectOutputStream outputSaveToFile = new ObjectOutputStream(new FileOutputStream(destination.toString()));
            outputSaveToFile.writeObject(myTask);
            outputSaveToFile.close();
        } catch (IOException exc) {
            System.err.println("Error saving to file");
            exc.printStackTrace();
        }
    }
}
