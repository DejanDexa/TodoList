import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;

import Model.Task;
import Model.ToDoList;

public class ToDoListController implements Serializable{

    private static final String regexMenu = "[0-6-]+";
    private static final String regexSubMenu="[0-5-]+";
    private static final String TASK_NAME ="task name";
    private static final String SHORT_DESCRIPTION ="short description";
    private static final String CATEGORY ="category";
    private static final String DUE_DATE ="due date";
    private static final String STATUS="status";
    private ToDoList myTask = new ToDoList();
    private ToDoList todoList;
    private String file;

    public ToDoListController(ToDoList todoList, String file){
        this.myTask=todoList;
        this.file=file;
    }

    private void addNewTask(Scanner scanner) throws ParseException {
        String name = askAndReadChoice(TASK_NAME, scanner);
        String description = askAndReadChoice(SHORT_DESCRIPTION,scanner);
        String category = askAndReadChoice(CATEGORY,scanner);
        String dueDate = askAndReadChoice(DUE_DATE,scanner);
        LocalDate date;
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy M d");
            date = LocalDate.parse(dueDate, formatter);
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", dueDate);
            throw exc;      // Rethrow the exception.
        }


        Task newTask = new Task(name, description, category, date);
        if (myTask.addNewTask(newTask)) {
            System.out.println("New task added: \nname = " + name + ", \ndescription = " + description + ", \ncategory = " + category + ", \ndate = " + date + ", \nstatus = " +newTask.getStatusToString());
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    private String askAndReadChoice(String param, Scanner scanner) {
        askUserToInsertData(param);
        return scanner.nextLine();
    }

    private void askUserToInsertData(String param) {
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

    public void printUpdateMenu(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to exit update\n" +
                "1  - to edit name\n" +
                "2  - to edit description\n" +
                "3  - to edit category\n" +
                "4  - to change date\n" +
                "5  - to edit status.");
        System.out.println("Choose your action: ");
    }
    public void printSortingMenuOptions(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - no sorting\n" +
                "1  - sort by project Ascending\n" +
                "2  - sort by project Descending\n" +
                "3  - sort by date Ascending\n" +
                "4  - sort by date Descending");
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

            System.out.println("\nEnter action: (press 6 to show all available actions)");
            String input = scanner.nextLine();
            int action = 6;

            if (input.matches(regexMenu)) {
                action = Integer.parseInt(input);
            } else {
                System.out.println("That's not a valid action! Please pick an option from the main menu");
            }
            switch (action) {
                case 0:
                    saveToFile(file);
                    System.out.println("\nExit...");
                    quit = true;
                    break;
                case 1:
                    printTaskListOrdered(scanner);
                    //myTask.printTasks();
                    break;
                case 2:
                    addNewTask(scanner);
                    break;
                case 3:
                    updateTask(scanner);
                    break;
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

    private void printTaskListOrdered(Scanner scanner){
        System.out.println("Choose sorting by: ");
        printSortingMenuOptions();
        String input=scanner.nextLine();
        int action=Integer.parseInt(input);
        switch (action) {
            case 0:
                myTask.printTasks();
                break;
            case 1:
                myTask.printTaskListSortedByProjectAsc();
                break;
            case 2:
                myTask.printTaskListSortedByProjectDsc();
                break;
            case 3:
                myTask.printTaskListSortedByDateAsc();
                break;
            case 4:
                myTask.printTaskListSortedByDateDsc();
                break;
            default:
                System.out.println("That's not a valid action! Please pick option from the main menu");
                this.printMenu();
        }

    }

    private void queryTask(Scanner scanner) {
        String name = askAndReadChoice(TASK_NAME,scanner);
        Task existingTaskRecord=myTask.findExistingTask(name);
        //Task existingTaskRecord = myTask.queryTask(name);
        if (existingTaskRecord == null) {
            System.out.println("Task not found.");
            return;
        }
        existingTaskRecord.PrintTask(existingTaskRecord);
    }

    private void removeTask(Scanner scanner){
        String name = askAndReadChoice(TASK_NAME, scanner);
        myTask.removeTaskByName(name);
    }

    private void updateTask(Scanner scanner) throws ParseException {
        System.out.println("Enter existing task name: ");
        String name = scanner.nextLine();
        Task existingTaskRecord = myTask.findExistingTask(name);
        if (existingTaskRecord == null) {
            System.out.println("Task not found.");
            return;
        }
        int subActions=5;
        printUpdateMenu();
        String input = scanner.nextLine();
        if (input.matches(regexMenu)) {
            subActions = Integer.parseInt(input);
        } else {
            System.out.println("That's not a valid action! Please pick an option from the edit menu");
        }
        switch (subActions) {
            case 0:
                printMenu();
                performActions(scanner);
                break;
            case 1:
                String newName=askAndReadChoice(TASK_NAME,scanner);
                myTask.changeTaskName(existingTaskRecord.getName(),newName);
                break;
            case 2:
                String newDescription=askAndReadChoice(SHORT_DESCRIPTION,scanner);
                myTask.changeTaskDescription(existingTaskRecord.getName(),newDescription);
                break;
            case 3:
                String newCategory=askAndReadChoice(CATEGORY,scanner);
                myTask.changeTaskCategory(existingTaskRecord.getName(),newCategory);
                break;
            case 4:
                String newDate=askAndReadChoice(DUE_DATE,scanner);
                myTask.changeTaskDueDate(existingTaskRecord.getName(),newDate);
                break;
            case 5:
                myTask.changeStatus(existingTaskRecord.getName());
                break;
            default:
                System.out.println("That's not a valid action! Please pick option from the main menu");
                this.printUpdateMenu();;
        }
    }
}
