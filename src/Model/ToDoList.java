package Model;

import Constants.EnvConstant;
import Utils.ScreenPrint;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


import static java.util.stream.Collectors.toList;

public class ToDoList implements Serializable{

    private List<Task> myTasks;
    private ScreenPrint sp = new ScreenPrint();

    public ToDoList() {
        this.myTasks = new ArrayList<>();
    }
    /**
     * Implementing method addNewTask
     * Return boolean to see whether it was successful or not
     * Sending it a task record or object created
     */
    public boolean addNewTask(Task task) {
        if (findTask(task.getName()) >= 0) {
            System.out.println("Task is already on file");
            return false;
        }
        myTasks.add(task);
        return true;
    }

    private int findTask(String taskName) {
        for(int i=0; i<this.myTasks.size(); i++) {
            Task taskList = this.myTasks.get(i);
            if(taskList.getName().equals(taskName)) {
                return i;
            }
        }
        return -1;
    }

    public void printTasks() {
        System.out.println("Task List");
        for(int i=0; i<this.myTasks.size(); i++) {
            System.out.println((i+1) + "." +
                    this.myTasks.get(i).getName() + " - " +
                    this.myTasks.get(i).getDescription()+ " - " +
                    this.myTasks.get(i).getCategory() + " - " +
                    this.myTasks.get(i).getDueDate() + " - " +
                    this.myTasks.get(i).getStatusToString());
        }
    }

    /** add new task **/
    public void addNewTask(Scanner scanner) {
        String name = askAndReadChoice(EnvConstant.TASK_NAME, scanner);
        String description = askAndReadChoice(EnvConstant.SHORT_DESCRIPTION, scanner);
        String category = askAndReadChoice(EnvConstant.CATEGORY, scanner);
        String dueDate = askAndReadChoice(EnvConstant.DUE_DATE, scanner);
        LocalDate date;
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("yyyy M d");
            date = LocalDate.parse(dueDate, formatter);
        } catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", dueDate);
            throw exc;      // Rethrow the exception.
        }
        Task newTask = new Task(name, description, category, date);
        if (this.addNewTask(newTask)) {
            System.out.println("New task added: \nname = " + name + ", \ndescription = " + description + ", \ncategory = " + category + ", \ndate = " + date + ", \nstatus = " + newTask.getStatusToString());
        } else {
            System.out.println("Cannot add, " + name + " already on file");
        }
    }

    /** remove task based on task name**/
    public void removeTask(Scanner scanner) {
        String name = askAndReadChoice(EnvConstant.TASK_NAME, scanner);
        this.removeTaskByName(name);
    }

    /** update task based on task name**/
    public void updateTask(Scanner scanner){
        System.out.println("Enter existing task name: ");
        String name = scanner.nextLine();
        Task existingTaskRecord = this.findExistingTask(name);
        if (existingTaskRecord == null) {
            System.out.println("Task not found.");
            return;
        }
        int subActions = 5;
        sp.printUpdateMenu();
        String input = scanner.nextLine();
        if (input.matches(EnvConstant.REGEX_MENU)) {
            subActions = Integer.parseInt(input);
        } else {
            System.out.println("That's not a valid action! Please pick an option from the edit menu");
        }
        switch (subActions) {    /** all properties can be modified**/
            case 0:
                break;
            case 1:
                String newName = askAndReadChoice(EnvConstant.TASK_NAME, scanner);
                this.changeTaskName(existingTaskRecord.getName(), newName);
                break;
            case 2:
                String newDescription = askAndReadChoice(EnvConstant.SHORT_DESCRIPTION, scanner);
                this.changeTaskDescription(existingTaskRecord.getName(), newDescription);
                break;
            case 3:
                String newCategory = askAndReadChoice(EnvConstant.CATEGORY, scanner);
                this.changeTaskCategory(existingTaskRecord.getName(), newCategory);
                break;
            case 4:
                String newDate = askAndReadChoice(EnvConstant.DUE_DATE, scanner);
                this.changeTaskDueDate(existingTaskRecord.getName(), newDate);
                break;
            case 5:
                this.changeStatus(existingTaskRecord.getName());
                break;
            default:
                System.out.println("That's not a valid action! Please pick option from the main menu");
                sp.printUpdateMenu();
        }
    }

    /** method used for getting existing task**/
    public Task findExistingTask(String name) {
        return this.myTasks.stream().filter(mytask->name.equals(mytask.getName())).findAny().orElse(null);
    }

    /** method used for changing existing task name**/
    public void changeTaskName(String oldName, String newName) {
        this.myTasks.stream().filter(myTasks->oldName.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setName(newName));
    }

    /** method used for changing existing task description**/
    public void changeTaskDescription(String name, String newDescription){
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setDescription(newDescription));
    }

    /** method used for changing existing task category**/
    public void changeTaskCategory(String name, String newCategory) {
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setCategory(newCategory));
    }

    /** method used for changing existing task due date**/
    public void changeTaskDueDate(String name, String newDate){
        LocalDate date;
        try {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern("MMM d yyyy");
            date = LocalDate.parse(newDate, formatter);
        }
        catch (DateTimeParseException exc) {
            System.out.printf("%s is not parsable!%n", newDate);
            throw exc;      // Rethrow the exception.
        }
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setDate(date));
    }

    /** method used for changing existing task status**/
    public void changeStatus(String name) {
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setStatus());
    }

    /** method used for removing existing task name**/
    public void removeTaskByName(String taskName) {
        this.myTasks.removeIf(mytask->taskName.equals(mytask.getName()));
        //todo add handled in case task doesn exist
    }

    /** method used for printing tasks sorted by project Asc**/
    public void printTaskListSortedByProjectAsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getCategory))
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.parseStatusForTask()));
    }

    /** method used for printing tasks sorted by project Dsc**/
    public void printTaskListSortedByProjectDsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getCategory).reversed())
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.parseStatusForTask()));
    }

    /** method used for printing tasks sorted by date Asc**/
    public void printTaskListSortedByDateAsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.parseStatusForTask()));
    }

    /** method used for printing tasks sorted by date Dsc**/
    public void printTaskListSortedByDateDsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.parseStatusForTask()));
    }

    public void queryTask(String param, Scanner scanner) {
        String name = askAndReadChoice(param, scanner);
        Task existingTaskRecord = this.findExistingTask(name);
        if (existingTaskRecord == null) {
            System.out.println("Task not found.");
            return;
        }
        existingTaskRecord.PrintTask(existingTaskRecord);
    }

    private String askAndReadChoice(String param, Scanner scanner) {
        askUserToInsertData(param);
        return scanner.nextLine();
    }

    private void askUserToInsertData(String param) {
        System.out.println("Enter " + param + ": ");
    }

    public void printTaskListOrdered(Scanner scanner) {
        System.out.println("Choose sorting by: ");
        sp.printSortingMenuOptions();
        String input = scanner.nextLine();
        int action = Integer.parseInt(input);
        switch (action) {
            case 0:
                this.printTasks();
                break;
            case 1:
                this.printTaskListSortedByProjectAsc();
                break;
            case 2:
                this.printTaskListSortedByProjectDsc();
                break;
            case 3:
                this.printTaskListSortedByDateAsc();
                break;
            case 4:
                this.printTaskListSortedByDateDsc();
                break;
            default:
                System.out.println("That's not a valid action! Please pick option from the main menu");
                sp.printMenu();
        }
    }
}
