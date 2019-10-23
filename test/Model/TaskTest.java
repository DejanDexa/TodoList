package Model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private static String date="2019 5 4";

    @Test
    void getName() {
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
        LocalDate dueD=parseStringToLocalDate(date);
        Task task = new Task("Task Test", "Test", "compareTo",dueD);
    }

    @Test
    void testToString() {
    }

    @Test
    void parseStatusForTaskInProgress() {
        String expected ="Task 1 - Description Task 1 - Cat 1 - 2019-05-04 - In Progress";
        LocalDate dueD=parseStringToLocalDate(date);
        Task isInProgress=new Task("Task 1", "Description Task 1","Cat 1",dueD);
        Assertions.assertEquals(expected,isInProgress.parseStatusForTask());
    }

    private LocalDate parseStringToLocalDate(String date){
        LocalDate dateP;
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("yyyy M d");
        dateP = LocalDate.parse(date, formatter);
        return dateP;
    }
}