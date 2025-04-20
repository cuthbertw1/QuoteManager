import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
public class AuthorManager {

    public ArrayList<String> authorList = new ArrayList<String>();

    public void downloadAuthors(){
        authorList.clear();
        String filePath="authors.csv";
        File file=new File(filePath);
        try {
            Scanner scan = new Scanner(new FileReader(file));
            String headers = scan.nextLine();
            while (scan.hasNextLine()) {
                String row = scan.nextLine();
                authorList.add(row);

            }
            scan.close();
        }catch (FileNotFoundException var7){
            var7.printStackTrace();
        }

    }

}