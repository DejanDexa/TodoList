package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ToDoListTest {

    private TestHelper ts;
    private String dateTask="2019 10 6";

    @Test
    void addNewTask() {
        ts = new TestHelper();
        ToDoList toDoList=new ToDoList();
        LocalDate taskDate=ts.parseStringToLocalDate(dateTask);
        Task expected=new Task("TestTask 1","Test Description1","TestCat 1",taskDate);
        toDoList.addNewTask(expected);
        Assertions.assertEquals(toDoList.getSize(),1);
    }

    @Test
    void printTasks() {
    }

    @Test
    void testAddNewTask() {


    }

    @Test
    void removeExistingTaskTest() {
        ts=new TestHelper();
        String taskName="TestTask 1";
        ToDoList toDoList=ts.addTwoTasksWithDifferentNameAndDate();
        Assertions.assertEquals(toDoList.removeTaskByName(taskName).getSize(),1);
    }

    @Test
    void removeNonExistingTaskTest() {
        ts=new TestHelper();
        String taskName="TestTask 3";
        ToDoList toDoList=ts.addTwoTasksWithDifferentNameAndDate();
        Assertions.assertEquals(toDoList.removeTaskByName(taskName).getSize(),2);
    }

    @Test
    void updateTask() {
    }

    @Test
    void findExistingTask() {
    }

    @Test
    void changeTaskName() {
    }

    @Test
    void changeTaskDescription() {
    }

    @Test
    void changeTaskCategory() {
    }

    @Test
    void changeValidTaskDueDateTest() {
        ts=new TestHelper();
        String finalTaskDateString="2019 8 6";
        LocalDate finalTaskDate=ts.parseStringToLocalDate("2019 10 8");
        String modifiedTaskName="TestTask 1";
        ToDoList actualToDoList=ts.addTwoTasksWithDifferentNameAndDate();//create ToDoList which will be used for testing the changeDueDate
        Scanner scanner=new Scanner(finalTaskDateString);
        ToDoList actual=actualToDoList.changeTaskDueDate(modifiedTaskName,scanner);

        /**create the tasks with expected date after change**/
        LocalDate expectedTaskDate=ts.parseStringToLocalDate(finalTaskDateString);
        List<Task> expectedTaskList=new ArrayList<>();
        Task task1=new Task("TestTask 1", "Test Description1", "TestCat 1", expectedTaskDate);
        Task task2=new Task("TestTask 2", "Test Description2", "TestCat 2", finalTaskDate);
        expectedTaskList.add(task1);
        expectedTaskList.add(task2);
        ToDoList todoListExpected =new ToDoList(expectedTaskList);

        Assertions.assertEquals(todoListExpected.getMyTasks(),actual.getMyTasks());
    }

    @Test
    void changeStatus() {
    }

    @Test
    void removeTaskByName() {
    }

    @Test
    void printTaskListSortedByProjectAsc() {
    }

    @Test
    void printTaskListSortedByProjectDsc() {
    }

    @Test
    void printTaskListSortedByDateAsc() {
    }

    @Test
    void printTaskListSortedByDateDsc() {
    }

    @Test
    void queryTask() {
    }

    @Test
    void printTaskListOrdered() {
    }

    @Test
    void getNumberOfToDos() {
    }

    @Test
    void getSize() {
    }
}