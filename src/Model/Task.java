package Model;

import com.sun.tools.javac.Main;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Task implements Serializable {
    private String name;
    private String description;
    private String category;
    private Date dueDate;
    //    private String date;
    private boolean status;
    private String details;

    /** Constructor */
    public Task(String name, String description, String category, Date dueDate, String details) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.status = status;
        this.details = details;

        //this.details = details;
    }

    //public Task(String name){
    //    this.name=name;

    //}

    /** Getters */
    public String getName() { return name; }

    public String getDescription() { return description; }

    public String getCategory() { return category; }

    public Date getDueDate() { return dueDate; }
    /////TEST/////
    //public String dateToString() {return dateToString();}
    /////TEST/////

    public boolean getStatus() { return status; }

    public String getDetails() { return details; }

    /**
     * Method Task - Create a new task record
     * instead of having to parse the parameters for the fields, create a task record without creating new separate object
     * Calling the constructor to create new task record, and return new task record
     */
    public static Task  createTask(String name, String description, String category, Date dueDate, String details ) {
        return new Task(name, description, category, dueDate, details);
    }
}

