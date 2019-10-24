package Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Task implements Comparable<Task>, Serializable {
    private String name;
    private String description;
    private String project;
    private LocalDate dueDate;
    private boolean status;


    public Task(String name, String description, String project, LocalDate dueDate) {
        this.name = name;
        this.description = description;
        this.project = project;
        this.dueDate = dueDate;
        this.status = false;
    }


    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProject() {
        return project;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public boolean getStatus() {
        return status;
    }


    public String getStatusToString() {
        if (this.status == false) return "In Progress";
        else return "Done";
    }


    public void PrintTask(Task existingTaskRecord) {
        System.out.println("Name: " + existingTaskRecord.getName() +
                " Description: " + existingTaskRecord.getDescription() +
                " Category: " + existingTaskRecord.getProject() +
                " Due date: " + existingTaskRecord.getDueDate() +
                " Status: " + existingTaskRecord.getStatusToString());
    }

    public Task setName(String newName) {
        this.name = newName;
        return this;
    }

    public Task setDescription(String newDescription) {
        this.description = newDescription;
        return this;
    }

    public Task setProject(String newCategory) {
        this.project = newCategory;
        return this;
    }

    public Task setDate(LocalDate newDate) {
        this.dueDate = newDate;
        return this;
    }

    public Task setStatus() {
        if (this.status == true)
            this.status = false;
        else this.status = true;
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
    public String toString() {
        return this.name + " - " +
                this.description + " - " +
                this.project + " - " +
                this.dueDate + " - " +
                this.status;

    }

    public String parseStatusForTask() {
        if (this.getStatus() == false)
            return this.toString().replace("false", "In Progress");
        else return this.toString().replace("true", "Done");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return getStatus() == task.getStatus() &&
                getName().equals(task.getName()) &&
                getDescription().equals(task.getDescription()) &&
                getProject().equals(task.getProject()) &&
                getDueDate().equals(task.getDueDate());
    }

    @Override
    public int hashCode() {
        return 0;
    }
}

