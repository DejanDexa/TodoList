package Utils;

import java.io.Serializable;

public class ScreenPrint implements Serializable {

    public void printStartApplication(int totalNumberOfTasks, int toDoTask){
        System.out.println("###################################################");
        System.out.println("##    _______                                    ##");
        System.out.println("##   | To-Do |                          \\O       ##");
        System.out.println("##   |>_____ |    Welcome to ToDoLy!     |\\      ##");
        System.out.println("##   |>_____ |                          / \\      ##");
        System.out.println("##   |_______|                                   ##");
        System.out.println("##                                               ##");
        System.out.println("###################################################");
        System.out.println("\n>> You have "+toDoTask+" tasks todo and "+totalNumberOfTasks+" tasks are done! <<");
        System.out.println("«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»«»");
        System.out.println("\nPlease pick an option from the Main Menu\n");
        System.out.println("                ⥥⥥⥥                    ");
    }

    public void printMenu(){
        System.out.println("\nAvailable actions press:");
        System.out.println("0  - to save and quit\n" +
                "1  - to show a list of existing tasks\n" +
                "2  - to add a new task\n" +
                "3  - to update an existing task\n" +
                "4  - to remove an existing task\n" +
                "5  - query if an existing task exists\n" +
                "6  - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }

    public void printUpdateMenu(){
        System.out.println("\nAvailable actions press:");
        System.out.println("0  - to exit update\n" +
                "1  - to edit name\n" +
                "2  - to edit description\n" +
                "3  - to edit project\n" +
                "4  - to change date\n" +
                "5  - to edit status.");
        System.out.println("Choose your action: ");
    }

    public void printSortingMenuOptions(){
        System.out.println("\nAvailable actions press:");
        System.out.println("0  - no sorting\n" +
                "1  - sort by project Ascending\n" +
                "2  - sort by project Descending\n" +
                "3  - sort by date Ascending\n" +
                "4  - sort by date Descending");
        System.out.println("Choose your action: ");
    }
}
