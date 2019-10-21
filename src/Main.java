import Model.Task;
import Model.ToDoList;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main implements Serializable{

    public static ToDoList myTask = new ToDoList();
    static String fileName = "..\\Data\\todo.md";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        ToDoListController controller=new ToDoListController(new ToDoList(), fileName);
        controller.startTodoList(fileName);
        controller.printMenu();
        controller.performActions(scanner);
    }
}
