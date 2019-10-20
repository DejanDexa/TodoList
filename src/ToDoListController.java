import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import Model.Task;
import Model.ToDoList;

public class ToDoListController implements Serializable{
    private static ToDoList myTask = new ToDoList();
    private ToDoList todoList;
    private String file;

    public ToDoListController(ToDoList todoList, String file){
        this.todoList=todoList;
        this.file=file;
    }

    private static void addNewTask(Scanner scanner) throws ParseException {
        askUserToInsertData("new task name");
        String name = scanner.nextLine();
        askUserToInsertData("short description");
        String description = scanner.nextLine();
        askUserToInsertData("category");
        String category = scanner.nextLine();
        askUserToInsertData("due date");
        String dueDate = scanner.nextLine();
        Date date = new SimpleDateFormat("yyyy MM dd").parse(dueDate); // TODO localdate

        boolean status = false;

        String details = "";
        if (!status) {
            details = "In progress";
            //System.out.println("In progress");
        } else {
            details = "Done";
            //System.out.println("Done");
        }


        Task newTask = Task.createTask(name, description, category, date, details);
        if (myTask.addNewTask(newTask)) {
            System.out.println("New task added: \nname = " + name + ", \ndescription = " + description + ", \ncategory = " + category + ", \ndate = " + date + ", \nstatus = " + details);
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    private static void askUserToInsertData(String param) {
        System.out.println("Enter "+param+": ");
    }

    public void startTodoList(String filename) {
        readFromFile(filename);
        System.out.println("###################################################");
        System.out.println("##                                       \\O      ##");
        System.out.println("##              Welcome to ToDoLy!        |\\     ##");
        System.out.println("##                                       / \\     ##");
        System.out.println("###################################################");
        System.out.println("\n>> You have X tasks todo and Y tasks are done! <<");
        System.out.println("\n-> Please pick an option from the Main Menu:");
    }

    public void printMenu(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to quit\n" +
                "1  - to show a list of existing tasks\n" +
                "2  - to add a new task\n" +
                "3  - to update an existing task\n" +
                "4  - to remove an existing task\n" +
                "5  - query if an existing task exists\n" +
                "6  - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }

    public void readFromFile(String fileLocation) {
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
    }

    public void saveToFile(String fileName) {
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

    public void performActions(Scanner scanner) throws ParseException {
        boolean quit = false;
        while (!quit) {

            //toDo create an Action interface or class or method where user can choose a valid action

            System.out.println("\nEnter action: (press 6 to show all available actions)");
            String input = scanner.nextLine();
            int action = 6;
            //if (input.matches("[a-zA-Z-789-]+")) {
            if (input.matches("[0-6-]+")) {
                //System.out.println("That's not a valid action! Please pick option from the main menu");
                action = Integer.parseInt(input);
            } else {
                //action = Integer.parseInt(input);
                System.out.println("That's not a valid action! Please pick an option from the main menu");
            }


            switch (action) {
                case 0:
                    //save to file....?
                    saveToFile(file);
                    System.out.println("\nExit...");
                    //quit = true;
                    break;

                case 1:
                    myTask.printTasks();
                    break;

                case 2:
                    addNewTask(scanner);
                    break;

                /*case 3:
                    updateTask(scanner);
                    break;*/

                case 4:
                    removeTask(scanner);
                    break;

                case 5:
                    queryTask(scanner);
                    break;

                case 6:
                    this.printMenu();
                    break;

                default:
                    System.out.println("That's not a valid action! Please pick option from the main menu");
                    this.printMenu();
            }

        }
    }
    private static void queryTask(Scanner scanner) {
        System.out.println("Enter existing task name: ");
        String name = scanner.nextLine();
        Task existingTaskRecord = myTask.queryTask(name);
        if (existingTaskRecord == null) {
            System.out.println("Task not found.");
            return;
        }
        System.out.println("Name: " + existingTaskRecord.getName() +
                " Description: " + existingTaskRecord.getDescription() +
                " Category: " + existingTaskRecord.getCategory() +
                " Due date: " + existingTaskRecord.getDueDate() +
                " Status: " + existingTaskRecord.getStatus());
    }

    private static void removeTask(Scanner scanner) {
        System.out.println("Enter existing task name: ");
        String name = scanner.nextLine();
        Task existingTaskRecord = myTask.queryTask(name);
        if (existingTaskRecord == null) {
            System.out.println("Task not found.");
            return;
        }

        if (myTask.removeTask(existingTaskRecord)) {
            System.out.println("Successfully deleted");
        } else {
            System.out.println("Error deleting task");
        }
    }
}
