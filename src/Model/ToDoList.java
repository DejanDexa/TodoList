package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoList implements Serializable {
    /** ArrayList of Task store tasks records */
    //private String myList;
    /** ArrayList of Task store tasks records */
    private ArrayList<Task> myTasks;

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

    public boolean updateTask(Task oldTask, Task newTask) {
        int foundPosition = findTask(oldTask);
        if(foundPosition <0) {
            System.out.println(oldTask.getName() +", was not found.");
            return false;
        }

        this.myTasks.set(foundPosition, newTask);
        System.out.println(oldTask.getName() + ", was replaced with " + newTask.getName());
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

//    public String queryTask(Task task) {
//        if(findTask(task) >=0) {
//            return task.getName();
//        }
//        return null;
//    }

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
                    this.myTasks.get(i).getDetails());
        }
    }
}
