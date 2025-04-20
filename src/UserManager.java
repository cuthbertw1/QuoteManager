import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {

    public ArrayList<String[]> userList = new ArrayList<String[]>();

    public void downloadUsers(){
        userList.clear();
        String filePath="users.csv";
        File file=new File(filePath);
        try {
            Scanner scan = new Scanner(new FileReader(file));
            String headers = scan.nextLine();
            while (scan.hasNextLine()) {
                String row = scan.nextLine();
                String [] data = row.split(";");
                userList.add(data);

            }
            scan.close();
        }catch (FileNotFoundException var7){
            var7.printStackTrace();
        }

    }


}
