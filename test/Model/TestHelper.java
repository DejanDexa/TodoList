package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TestHelper {

    private String dateTask1="2019 10 6";
    private String dateTask2="2019 10 8";

    public LocalDate parseStringToLocalDate(String date){
        LocalDate dateP;
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy M d");
        dateP = LocalDate.parse(date, formatter);
        return dateP;
    }

    public ToDoList addTwoTasksWithDifferentNameAndDate(){
        LocalDate taskDate1=this.parseStringToLocalDate(dateTask1);
        LocalDate taskDate2=this.parseStringToLocalDate(dateTask2);
        ToDoList toDoList;
        List<Task> taskList=new ArrayList<>();
        Task task1=new Task("TestTask 1", "Test Description1", "TestCat 1", taskDate1);
        Task task2=new Task("TestTask 2", "Test Description2", "TestCat 2", taskDate2);
        taskList.add(task1);
        taskList.add(task2);
        toDoList=new ToDoList(taskList);
        return toDoList;
    }


}
