package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {
    private String name;
    private String description;
    private String category;
    private LocalDate dueDate;
    private boolean status;

    /** Constructor */
    public Task(String name, String description, String category, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.status = false;
    }

    /** Getters */
    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getCategory() { return category; }

    public LocalDate getDueDate() { return dueDate; }

    public boolean getStatus() { return status; }

    /**
     * Method Task - Create a new task record
     * instead of having to parse the parameters for the fields, create a task record without creating new separate object
     * Calling the constructor to create new task record, and return new task record
     */
    public Task createTask(String name, String description, String category, LocalDate dueDate) {
        return new Task(name, description, category, dueDate);
    }

    public String getStatusToString(){
        if (this.status==false)return "In Progress";
        else return "Done";
    }


    public void PrintTask(Task existingTaskRecord) {
        System.out.println("Name: " + existingTaskRecord.getName() +
                " Description: " + existingTaskRecord.getDescription() +
                " Category: " + existingTaskRecord.getCategory() +
                " Due date: " + existingTaskRecord.getDueDate() +
                " Status: " + existingTaskRecord.getStatusToString());
    }

    public Task setName(String newName) {
        this.name=newName;
        return this;
    }

    public Task setDescription(String newDescription) {
        this.description=newDescription;
        return this;
    }

    public Task setCategory(String newCategory) {
        this.category=newCategory;
        return this;
    }

    public Task setDate(LocalDate newDate) {
        this.dueDate=newDate;
        return this;
    }

    public Task setStatus() {
        if(this.status==true)
            this.status=false;
        else this.status=true;
        return this;
    }

    @Override
    public int compareTo(Task o) {
        if (!this.name.equalsIgnoreCase(o.name))
            return this.name.compareTo(o.name);
        if (!this.dueDate.equals(o.dueDate))
            return this.dueDate.compareTo(o.dueDate);
        return 0;
    }

    @Override
    public String toString(){
            System.out.println("Task List");
                return this.name +" - " +
                        this.description+ " - " +
                        this.category + " - " +
                        this.dueDate + " - " +
                        this.status;

    }

    public String parseStatusForTask(){
        if (this.getStatus()==false)
        return this.toString().replace("false", "In Progress");
        else return this.toString().replace("true", "Done");
    }
}

