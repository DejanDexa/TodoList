package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class ToDoList implements Serializable{
    /** ArrayList of Task store tasks records */
    //private String myList;
    /** ArrayList of Task store tasks records */
    private List<Task> myTasks;

    /**
     * Constractor
     * Initialize empty ArrayList
     */
    public ToDoList() {
        //this.myList = myList;
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

    public Task findExistingTask(String name) {
        return this.myTasks.stream().filter(mytask->name.equals(mytask.getName())).findAny().orElse(null);
    }

    public void changeTaskName(String oldName, String newName) {
        this.myTasks.stream().filter(myTasks->oldName.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setName(newName));
    }

    public void changeTaskDescription(String name, String newDescription){
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setDescription(newDescription));
    }

    public void changeTaskCategory(String name, String newCategory) {
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setCategory(newCategory));
    }

    public void changeTaskDueDate(String name, String newDate) throws ParseException {
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

    public void changeStatus(String name) {
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(toList()).forEach(mytask->mytask.setStatus());
    }

    public void removeTaskByName(String taskName) {
        this.myTasks.removeIf(mytask->taskName.equals(mytask.getName()));
        //todo add handled in case task doesn exist
    }


    public void printTaskListSortedByProjectAsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getCategory))
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.toString()));
    }

    public void printTaskListSortedByProjectDsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getCategory).reversed())
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.toString()));
    }

    public void printTaskListSortedByDateAsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate))
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.toString()));
    }

    public void printTaskListSortedByDateDsc() {
        List<Task> sortedList = this.myTasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate).reversed())
                .collect(Collectors.toList());
        sortedList.stream().forEach(task -> System.out.println(task.parseStatusForTask()));
    }


}
