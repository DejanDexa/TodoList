import Model.ToDoList;
import java.io.*;
import java.text.ParseException;


public class Main implements Serializable{

    public static ToDoList myTask = new ToDoList();
    static String fileName = "Data/todo.md";


    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        ToDoListController controller=new ToDoListController(new ToDoList(), fileName);
        controller.startTodoList(fileName);
        controller.performActions();
    }
}
