package Utils;

public class ScreenPrint {

    public void printStartApplication(){
        System.out.println("###################################################");
        System.out.println("##                                       \\O      ##");
        System.out.println("##              Welcome to ToDoLy!        |\\     ##");
        System.out.println("##                                       / \\     ##");
        System.out.println("###################################################");
        System.out.println("\n>> You have X tasks todo and Y tasks are done! <<");
        System.out.println("\n-> Please pick an option from the Main Menu:");
    }

    public void printMenu(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to quit\n" +
                "1  - to show a list of existing tasks\n" +
                "2  - to add a new task\n" +
                "3  - to update an existing task\n" +
                "4  - to remove an existing task\n" +
                "5  - query if an existing task exists\n" +
                "6  - to print a list of available actions.");
        System.out.println("Choose your action: ");
    }

    public void printUpdateMenu(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - to exit update\n" +
                "1  - to edit name\n" +
                "2  - to edit description\n" +
                "3  - to edit category\n" +
                "4  - to change date\n" +
                "5  - to edit status.");
        System.out.println("Choose your action: ");
    }

    public void printSortingMenuOptions(){
        System.out.println("\nAvailable actions:\npress");
        System.out.println("0  - no sorting\n" +
                "1  - sort by project Ascending\n" +
                "2  - sort by project Descending\n" +
                "3  - sort by date Ascending\n" +
                "4  - sort by date Descending");
        System.out.println("Choose your action: ");
    }
}
