import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
public class Author {

    public String name;

    public void createAuthor() throws IOException {

        try {

            Scanner input = new Scanner(System.in);

            System.out.println("What is the name of the author you would like to add?");
            this.name = input.nextLine();

            FileWriter write = new FileWriter("authors.csv", true);
            write.append(name);
            write.append("\n");

            write.flush();
            write.close();

            System.out.println("Added!");

        }
        catch (FileNotFoundException e) {

        }

    }


}