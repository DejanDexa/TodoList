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

    static String fileName = "\\Users\\dejan\\Desktop\\IntelliJProjects\\TodoList\\Data\\todo.md";
    public static ToDoList myTask = new ToDoList();
    //toDo change this with relative path
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException, InterruptedException, ParseException {

            //toDo put this methods in Initialize method in separate place
            boolean quit = false;
            startTodoList();
            showMenu();

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

//            scanner.nextLine();
//            if(scanner.hasNextInt()) {
//                action = scanner.nextLine();
//                scanner.nextLine();
//            } else {
//                System.out.println("Error");
//            }

                switch (action) {
                    case 0:
                        //save to file....?
                        saveToFile();
                        System.out.println("\nExit...");
                        quit = true;
                        break;

                    case 1:
                        myTask.printTasks();
                        break;

                    case 2:
                        addNewTask();
                        break;

                    case 3:
                        updateTask();
                        break;

                    case 4:
                        removeTask();
                        break;

                    case 5:
                        queryTask();
                        break;

                    case 6:
                        showMenu();
                        break;

                    default:
                        System.out.println("That's not a valid action! Please pick option from the main menu");
                        showMenu();
                }

            }
        }

        public static void saveToFile () {
            Path destination = Paths.get(fileName).toAbsolutePath();
            try {
                ObjectOutputStream outputSaveToFile = new ObjectOutputStream(new FileOutputStream(destination.toString()));
                outputSaveToFile.writeObject(myTask);
                outputSaveToFile.close();
            } catch (IOException exc) {
                System.err.println("Error saving to file");
                exc.printStackTrace();
            }
        }

        public static void readFromFile () {
            Path destination = Paths.get(fileName).toAbsolutePath();
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

        private static void addNewTask () throws ParseException {
            askUserToInsertData("new task name");
            String name = scanner.nextLine();
            askUserToInsertData("short description");
            String description = scanner.nextLine();
            askUserToInsertData("category");
            String category = scanner.nextLine();
            askUserToInsertData("due date");
            //Date dueDate = scanner.nextLine();
            String sDate = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy MM dd").parse(sDate); // TODO localdate

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

        private static void askUserToInsertData (String param){
            System.out.println("Enter " + param + ": ");
        }


        private static void updateTask () throws ParseException {
            System.out.println("Enter existing task name: ");
            String name = scanner.nextLine();
            Task existingTaskRecord = myTask.queryTask(name);
            if (existingTaskRecord == null) {
                System.out.println("Task not found.");
                return;
            }

            System.out.print("Enter new task name: ");
            String newName = scanner.nextLine();
            System.out.print("Enter new task description: ");
            String newDescription = scanner.nextLine();
            System.out.print("Enter new task category: ");
            String newCategory = scanner.nextLine();
            System.out.println("Enter due date");
            String newDate = scanner.nextLine();
            Date date = new SimpleDateFormat("yyyy MM dd").parse(newDate);


            boolean newStatus = false;
            String newDetails = "";
            if (newStatus == false) {
                System.out.println("In progres");
            } else {
                System.out.println("Done");
            }

            Task newTask = Task.createTask(newName, newDescription, newCategory, date, newDetails);
            if (myTask.updateTask(existingTaskRecord, newTask)) {
                System.out.println("Successfully updated record");
            } else {
                System.out.println("Error updating record.");
            }
        }

        private static void removeTask () {
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

        private static void queryTask () {
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

        private static void startTodoList () {
            readFromFile();
            System.out.println("###################################################");
            System.out.println("##                                       \\O      ##");
            System.out.println("##              Welcome to ToDoLy!        |\\     ##");
            System.out.println("##                                       / \\     ##");
            System.out.println("###################################################");
            System.out.println("\n>> You have X tasks todo and Y tasks are done! <<");
            System.out.println("\n-> Please pick an option from the Main Menu:");
        }

        private static void showMenu () {
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
    }

