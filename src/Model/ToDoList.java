package Model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoList implements Serializable {
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

    public boolean removeTask(Task task) {
        int foundPosition = findTask(task);
        if(foundPosition <0) {
            System.out.println(task.getName() +", was not found.");
            return false;
        }
        this.myTasks.remove(foundPosition);
        System.out.println(task.getName() + ", was deleted.");
        return true;
    }

    private int findTask(Task task) {
        return this.myTasks.indexOf(task);
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

    public Task queryTask(String name) {
        int position = findTask(name);
        if(position >=0) {
            return this.myTasks.get(position);
        }
        return null;
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
                .collect(Collectors.toList()).forEach(mytask->mytask.setName(newName));
    }

    public void changeTaskDescription(String name, String newDescription){
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(Collectors.toList()).forEach(mytask->mytask.setDescription(newDescription));
    }

    public void changeTaskCategory(String name, String newCategory) {
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(Collectors.toList()).forEach(mytask->mytask.setCategory(newCategory));
    }

    public void changeTaskDueDate(String name, String newDate) throws ParseException {
        Date date = new SimpleDateFormat("yyyy MM dd").parse(newDate);
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(Collectors.toList()).forEach(mytask->mytask.setDate(date));
    }

    public void changeStatus(String name) {
        this.myTasks.stream().filter(myTasks->name.equals(myTasks.getName()))
                .collect(Collectors.toList()).forEach(mytask->mytask.setStatus());
    }
}
