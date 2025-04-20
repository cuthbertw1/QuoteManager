import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
public class User {

    public String userName;

    public String email;

    public String password;

    public boolean logged;

    public boolean isAdmin;

    ArrayList<Quote> quotesAdded = new ArrayList<Quote>();

    ArrayList<Author> authorsAdded = new ArrayList<Author>();



    public void createAccount(){

        String filepath="users.csv";
        File file=new File(filepath);

        Scanner scan =new Scanner(System.in);
        System.out.println("Hello new user, please enter your name (FirstLast)");
        this.userName=scan.next();
        System.out.println("Enter your email: ");
        this.email=scan.next();
        System.out.println("Enter a password: ");
        this.password=scan.next();

        try {
            int count = 0;
            Scanner input = new Scanner(file);
            String headers = input.nextLine();
            while (input.hasNextLine()) {

                String row = input.nextLine();
                String[] data = row.split(",");

                if (userName.equals(data[0]) && password.equals(data[1])) {
                    count++;
                    System.out.println("This account already exists, please try logging in.");
                }
            }

            if (count ==0){
                FileWriter writer=new FileWriter(filepath, true);

                writer.append(userName+","+password+","+email+","+"0");
                writer.append("\n");
                writer.flush();
                System.out.println("Your account has been Created!");
                writer.close();
            }

        }catch (IOException var7){
            var7.printStackTrace();
        }
    }

    public  String getName(){
        return this.userName;
    }

    public void logIn(){

        Scanner scan=new Scanner(System.in);
        System.out.println("Enter your Username");
        String userName=scan.next();
        System.out.println("Enter password");
        String password=scan.next();



        String filePath="users.csv";
        File file=new File(filePath);
        try {
            Scanner input1 = new Scanner(new FileReader(file));
            String headers = input1.nextLine();
            int count = 0;
            while (input1.hasNextLine()) {
                String row = input1.nextLine();
                String[] data = row.split(";");
                // System.out.println(row);

                if(userName.equals(data[0]) && password.equals(data[1]) && data[3].equals("1")){
                    count++;
                    System.out.println("You are Logged In!");
                    System.out.println("Welcome Admin!");
                    this.logged=true;
                    this.userName=userName;
                    this.isAdmin = true;
                }
                else if (userName.equals(data[0]) && password.equals(data[1])) {
                    count++;
                    System.out.println("You are Logged In!");
                    this.logged=true;
                    this.userName=userName;
                }
            }
            if(count == 0){
                System.out.println("incorrect, please check credentials and try again");
            }
            input1.close();
        }catch (FileNotFoundException var7){
            var7.printStackTrace();
        }

    }

    public boolean isLogged(){

        return this.logged;
    }


    public void logOut(){

        if (!this.logged){
            System.out.println("You are already logged out!");
        }
        else{
            System.out.println("Logging you out!");
            this.logged = false;
            this.userName = null;
            this.isAdmin = false;
        }

    }

    public void showUser(UserManager manage)throws IOException{

        int count = 1;
        try{
            for(int i = 0; i <= manage.userList.size()-1; i++){
                System.out.println();
                System.out.println("USER #"+count);
                System.out.println("NAME: "+manage.userList.get(i)[0]);
                System.out.println("PASSWORD: "+manage.userList.get(i)[1]);
                System.out.println("EMAIL: " + manage.userList.get(i)[2]);
                count++;

            }
        }catch(IndexOutOfBoundsException e){

        }




    }



}
