import java.io.IOException;
import java.util.Scanner;

public class Main {

    static String fileName = "\\Users\\dejan\\Desktop\\IntelliJProjects\\TodoList\\Data\\todo.md";

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("**************************************************");
        System.out.println("|              Welcome to ToDoLy                 |");
        System.out.println("**************************************************");
        System.out.println("\n>> You have X tasks todo and Y tasks are done! <<");
        System.out.println("\n-> Please pick an option from Main Menu:");
        choise();
    }

    public static void choise() {
        int menuItem = -1;
        while (menuItem != 0) {
            menuItem = menu();
            switch (menuItem) {
                case 1:
                    Task.showList();
                    break;
                case 2:
                    Task.addItem();
                    break;
                case 3:
                    Task.removeItem();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Unknown command");
            }
        }
    }
    static int menu() {
        int choice;
        Scanner input = new Scanner(System.in);
        System.out.println("\n>>>  Main Menu  <<<");
        System.out.println("1. Display to-do list");
        System.out.println("2. Add item to to-do list");
        System.out.println("3. Remove item from to-do list");
        System.out.println("0. Exit the program");
        System.out.println();
        System.out.println("Enter a choice: ");
        choice = input.nextInt();
        return choice;
    }


}


