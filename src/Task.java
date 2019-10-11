import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Task {

    static void showList() {
        System.out.println("\nTo-do List\n");
        try {
            Scanner inFile = new Scanner(new FileReader(Main.fileName));
            String line;
            int number = 1;
            while (inFile.hasNextLine()) {
                line = inFile.nextLine();
                System.out.println(number + " " + line);
                //System.out.println(line);
                ++number;
            }
            inFile.close();
        } catch (IOException ioe) {
            System.out.println("Can't access file.");
        }
    }

    static void addItem() {
        System.out.println("\nAdd Item\n");
        try {
            Scanner input = new Scanner(System.in);
            PrintWriter outFile = new PrintWriter(new FileWriter(Main.fileName, true));
            System.out.println("Enter an item: ");
            String item = input.nextLine();
            outFile.println(item);
            outFile.close();
        } catch (IOException ioe) {
            System.out.println("Can't access file.");
        }
    }

    static void removeItem() {
        int choise;
        showList();
        Scanner input = new Scanner(System.in);
        System.out.println("Which item do you want to remove? ");
        choise = input.nextInt();
        ArrayList<String> items = new ArrayList<String>();
        int number = 1;
        try {
            Scanner inFile = new Scanner(new FileReader(Main.fileName));
            String item;
            while (inFile.hasNextLine()) {
                item = inFile.nextLine();
                if (number != choise)
                    items.add(item);
                ++number;
            }
            PrintWriter outFile = new PrintWriter(new FileWriter(Main.fileName));
            for (int i = 0; i < items.size(); ++i)
                outFile.println(items.get(i));
            outFile.close();
        }
        catch(IOException ioe) {
            System.out.println("Can't access file.");
        }
    }
}

