import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;

public class Quote {

    public String author;

    public String text;

    public String editor;

    public LocalDate addDate;

    public void createQuote(User person)throws IOException {

        try {

            Scanner input = new Scanner(System.in);

            System.out.println("What is the name of person who said the quote?");
            this.author = input.nextLine();
            System.out.println("What is the Quote?");
            this.text= input.nextLine();
            LocalDate current = LocalDate.now();
            this.addDate = LocalDate.now();
            this.editor = person.userName;

            File file = new File("authors.csv");
            Scanner scan = new Scanner(file);
            int lineCount = 1;
            int keep = 0;
            while(scan.hasNextLine()){
                String name = scan.nextLine();
                if(author.equalsIgnoreCase(name)) {
                    keep = lineCount;
                }
                lineCount++;
            }

            scan.close();
            if(keep == 0){
                keep = lineCount;
                File file2 = new File("authors.csv");
                FileWriter write = new FileWriter(file2,true);
                write.append("\n");
                write.append(author);
                write.flush();
                write.close();
            }


            FileWriter write = new FileWriter("quotes.csv", true);
            write.append(keep+";"+text+";"+current+";"+person.userName);
            write.append("\n");

            write.flush();
            write.close();




        }
        catch (FileNotFoundException e) {

        }

    }

    public void showQuote(QuoteManager manage, int rand)throws IOException{

        File file = new File("authors.csv");
        Scanner scan = new Scanner(file);
        int lineCount = 1;
        String nameHold = "";
        while(scan.hasNextLine()){
            String name = scan.nextLine();
            if(String.valueOf(lineCount).equalsIgnoreCase(manage.quoteList.get(rand)[0])){
                nameHold = name;
            }
            lineCount++;
        }


        System.out.println();
        System.out.println("'" + manage.quoteList.get(rand)[1] + ".'");
        System.out.println("- " + nameHold);
        System.out.println("Added by " + manage.quoteList.get(rand)[3]);
        System.out.println("Date Added: "+manage.quoteList.get(rand)[2]);



    }



    //Show(): displays the quote, showing the author, the text, the date in which it was added to
    //the system, and the name of the user who added the quote.

}


