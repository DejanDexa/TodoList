import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

import Constants.EnvConstant;
import Model.ToDoList;
import Utils.FileManipulation;
import Utils.ScreenPrint;

public class ToDoListController implements Serializable {

    private static final String regexSubMenu = "[0-5-]+";

    private ToDoList toDoList;
    private String file;
    private ScreenPrint sp = new ScreenPrint();
    private FileManipulation fm = new FileManipulation();

    public ToDoListController(ToDoList todoList, String file) {
        this.toDoList = todoList;
        this.file = file;
    }

    public void startTodoList(String filename) {
        toDoList = fm.readFromFile(filename, toDoList);
        int toDoTask=toDoList.getNumberOfToDos();
        int totalNumberOfTasks=toDoList.getSize();
        sp.printStartApplication(totalNumberOfTasks-toDoTask, toDoTask);
        sp.printMenu();
    }

    public void performActions(Scanner scanner) throws ParseException {
        boolean quit = false;
        while (!quit) {

            System.out.println("\nEnter action: (press 6 to show all available actions)");
            String input = scanner.nextLine();
            int action = 6;

            if (input.matches(EnvConstant.REGEX_MENU)) {
                action = Integer.parseInt(input);
            } else {
                System.out.println("That's not a valid action! Please pick an option from the main menu");
            }
            switch (action) {
                case 0:
                    fm.saveToFile(file, toDoList);
                    System.out.println("\nExit...");
                    quit = true;
                    break;
                case 1:
                    toDoList.printTaskListOrdered(scanner);
                    break;
                case 2:
                    toDoList.addNewTask(scanner);
                    break;
                case 3:
                    toDoList.updateTask(scanner);
                    break;
                case 4:
                    toDoList.removeTask(scanner);
                    break;
                case 5:
                    toDoList.queryTask(EnvConstant.TASK_NAME,scanner);
                    break;
                case 6:
                    sp.printMenu();
                    break;
                default:
                    System.out.println("That's not a valid action! Please pick option from the main menu");
                    sp.printMenu();
            }
        }
    }
}
