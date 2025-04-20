import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
public class QuoteManager {

    public ArrayList<String[]> quoteList = new ArrayList<String[]>();

    public void downloadQuotes(){
        quoteList.clear();
        String filePath="quotes.csv";
        File file=new File(filePath);
        try {
            Scanner scan = new Scanner(new FileReader(file));
            String headers = scan.nextLine();
            while (scan.hasNextLine()) {
                String row = scan.nextLine();
                String [] data = row.split(";");
                quoteList.add(data);

            }
            scan.close();
        }catch (FileNotFoundException var7){
            var7.printStackTrace();
        }

    }

    //using a method, reads the quotes from a file named quotes.csv and stores them in an array.
    //- enables the user to realize the following actions:
    //− List all the quotes;
    //− Get a random quote;
    //− Create a new quote (only if the user is logged)


}
