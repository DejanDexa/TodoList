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
    void changeTaskName() {
        ts=new TestHelper();
        String oldTaskName="Task 1";
        String newTaskName="New Task Name";
        String dateString="2019 10 8";
        LocalDate finalTaskDate=ts.parseStringToLocalDate(dateString);
        List<Task> actualTaskList=new ArrayList<>();
        List<Task> expectedTaskList=new ArrayList<>();
        Task task1=new Task(oldTaskName, "Test Description2", "TestCat 2", finalTaskDate);
        Task task2=new Task(newTaskName, "Test Description2", "TestCat 2", finalTaskDate);
        actualTaskList.add(task1);
        expectedTaskList.add(task2);
        ToDoList todoListExpected =new ToDoList(expectedTaskList);
        ToDoList todoListActualinit=new ToDoList(actualTaskList);
        todoListActualinit.changeTaskName(oldTaskName,newTaskName);
        Assertions.assertEquals(todoListExpected.getMyTasks(),todoListActualinit.getMyTasks());
    }

    @Test
    void changeTaskDescription() {
        ts=new TestHelper();
        String taskName="Task 1";
        String newDescriptionName="Description Test";
        String dateString="2019 10 8";
        LocalDate finalTaskDate=ts.parseStringToLocalDate(dateString);
        List<Task> actualTaskList=new ArrayList<>();
        List<Task> expectedTaskList=new ArrayList<>();
        Task task1=new Task(taskName, "Test Description2", "TestCat 2", finalTaskDate);
        Task task2=new Task(taskName, "Description Test", "TestCat 2", finalTaskDate);
        actualTaskList.add(task1);
        expectedTaskList.add(task2);
        ToDoList todoListExpected =new ToDoList(expectedTaskList);
        ToDoList todoListActualinit=new ToDoList(actualTaskList);
        todoListActualinit.changeTaskDescription(taskName,newDescriptionName);
        Assertions.assertEquals(todoListExpected.getMyTasks(),todoListActualinit.getMyTasks());
    }

    @Test
    void changeTaskCategory() {
        ts=new TestHelper();
        String taskName="Task 1";
        String newCategory="Category Test";
        String dateString="2019 10 8";
        LocalDate finalTaskDate=ts.parseStringToLocalDate(dateString);
        List<Task> actualTaskList=new ArrayList<>();
        List<Task> expectedTaskList=new ArrayList<>();
        Task task1=new Task(taskName, "Description Test", "-bdsas", finalTaskDate);
        Task task2=new Task(taskName, "Description Test", "Category Test", finalTaskDate);
        actualTaskList.add(task1);
        expectedTaskList.add(task2);
        ToDoList todoListExpected =new ToDoList(expectedTaskList);
        ToDoList todoListActualinit=new ToDoList(actualTaskList);
        todoListActualinit.changeTaskCategory(taskName,newCategory);
        Assertions.assertEquals(todoListExpected.getMyTasks(),todoListActualinit.getMyTasks());
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
}