package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;


class TaskTest {

    private static String date="2019 5 4";
    private TestHelper ts;

    private static String name;
    private static String project;
    private static String category;

    @Test
    void getName() {
        /*Task task = new Task(name, project, category,);

        assertEquals(title, task.getTitle());
        assertEquals("", task.getTitle());*/
    }

    @Test
    void getDescription() {
    }

    @Test
    void getCategory() {
    }

    @Test
    void getDueDate() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void createTask() {
        //Task task = new Task("Task", "Celan", "Home",);
    }

    @Test
    void getStatusToString() {
    }

    @Test
    void printTask() {
    }

    @Test
    void setName() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void setCategory() {
    }

    @Test
    void setDate() {
    }

    @Test
    void setStatus() {
    }

    @Test
    void compareTo() {

    }

    @Test
    void testToString() {
    }

    @Test
    void parseStatusForTaskInProgress() {
        ts=new TestHelper();
        String expected ="Task 1 - Description Task 1 - Cat 1 - 2019-05-04 - In Progress";
        LocalDate dueD=ts.parseStringToLocalDate(date);
        Task isInProgress=new Task("Task 1", "Description Task 1","Cat 1",dueD);
        Assertions.assertEquals(expected,isInProgress.parseStatusForTask());
    }

    @Test
    void parseStatusForTaskDone() {
        ts=new TestHelper();
        String expected ="Task 1 - Description Task 1 - Cat 1 - 2019-05-04 - Done";
        LocalDate dueD=ts.parseStringToLocalDate(date);
        Task isDone=new Task("Task 1", "Description Task 1","Cat 1",dueD);
        isDone.setStatus();
        Assertions.assertEquals(expected,isDone.parseStatusForTask());
    }

}