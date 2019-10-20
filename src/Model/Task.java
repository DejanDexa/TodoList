package Model;

import com.sun.tools.javac.Main;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Task {

    private String name;
    private String description;
    private String category;
    private Date dueDate;
    private boolean status;
    private String details;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public String getDetails() {
        return details;
    }

    public boolean getStatus() {
        return status;
    }
    /** Constructor */
    public Task(String name, String description, String category, Date dueDate, String details) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.dueDate = dueDate;
        this.status = status;
        this.details = details;
    }

    public static Task  createTask(String name, String description, String category, Date dueDate, String details ) {
        return new Task(name, description, category, dueDate, details);
    }
}

