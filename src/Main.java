import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.ChronoUnit.DAYS;
public class Main {
    public static void main(String[] args) throws IOException {

        boolean hold = true;
        User user = new User();
        QuoteManager manage = new QuoteManager();
        AuthorManager aManage = new AuthorManager();
        UserManager uManage = new UserManager();
        while (hold) {

            manage.downloadQuotes();
            aManage.downloadAuthors();
            uManage.downloadUsers();
            Scanner input = new Scanner(System.in);
            System.out.println("Welcome User, what do you want to do?");
            System.out.println("1. List all of the quotes");
            System.out.println("2. List all of the authors");
            System.out.println("3. Add a quote");
            System.out.println("4. Add an author");
            System.out.println("5. Search for an author");
            System.out.println("6. Get a random quote");
            System.out.println("7. Create an account");
            System.out.println("8. Log into your account");
            System.out.println("9. Log out of your account");
            System.out.println("10. Admin Menu");
            System.out.println("11. Quit");


            int userChoice = input.nextInt();

            switch (userChoice) {
                case 1:

                    int count = 1;


                    if (manage.quoteList.size() <= 5) {
                        int pageCount = 1;
                        System.out.println("Page " + pageCount + " out of 1");
                        for (int i = 0; i < manage.quoteList.size(); i++) {

                            System.out.print(count + ". ");
                            Quote quote = new Quote();
                            quote.showQuote(manage, i);
                            lineSpacer();
                            count++;
                        }
                    } else {
                        double num = (double) manage.quoteList.size() / 5;
                        int num1 = (int) Math.ceil(num);
                        int pageCount = 1;
                        int g = 0;
                        for (int i = 0; i < num1; i++) {
                            System.out.println("Page " + pageCount + " out of " + num1);
                            for (int j = 0; j < 5; j++) {
                                try {
                                    System.out.print(count + ". ");
                                    Quote quote = new Quote();
                                    quote.showQuote(manage, g);
                                    lineSpacer();
                                    count++;
                                    g++;
                                } catch (IndexOutOfBoundsException e) {
                                    break;
                                }
                            }
                            System.out.println("WHAT DO YOU WANT TO DO?");
                            System.out.println("M. Show more");
                            System.out.println("E. Edit quote");
                            System.out.println("D. Delete quote");
                            System.out.println("A. Add Quote");
                            System.out.println("B. Back to previous menu");
                            String userString = input.next();
                            if (userString.equalsIgnoreCase("M")) {
                                pageCount++;
                                continue;
                            } else if (userString.equalsIgnoreCase("E")) {
                                if (user.logged) {
                                    System.out.println("What quote would you like to edit? (Please enter number)");
                                    int quoteNum = input.nextInt();
                                    if (manage.quoteList.get(quoteNum - 1)[3].equalsIgnoreCase(user.userName) || user.isAdmin) {
                                        System.out.println("What would you like to change the text to?");
                                        input.nextLine();
                                        String edit = input.nextLine();
                                        manage.quoteList.get(quoteNum - 1)[1] = edit;
                                        uploadQuotes(manage);
                                        System.out.println("edited");
                                        break;

                                    } else {
                                        System.out.println("You cannot edit that quote as you did not enter it.");
                                        break;
                                    }
                                } else {
                                    System.out.println("You cannot create a edit without being logged in!");
                                    break;
                                }
                            } else if (userString.equalsIgnoreCase("D")) {
                                if (user.logged) {
                                    System.out.println("What quote would you like to delete? (Please enter number)");
                                    int quoteNum = input.nextInt();
                                    if (manage.quoteList.get(quoteNum - 1)[3].equalsIgnoreCase(user.userName) || user.isAdmin) {

                                        manage.quoteList.remove(quoteNum - 1);
                                        uploadQuotes(manage);
                                        System.out.println("Removed");
                                        break;

                                    } else {
                                        System.out.println("You cannot Delete that quote as you did not enter it.");
                                        break;
                                    }
                                } else {
                                    System.out.println("You cannot delete a quote without being logged in!");
                                    break;
                                }
                            } else if (userString.equalsIgnoreCase("A")) {
                                if (user.logged) {
                                    Quote quote = new Quote();
                                    quote.createQuote(user);
                                    System.out.println("Your quote has been Created!");
                                    break;
                                } else {
                                    System.out.println("You cannot create a quote without being logged in!");
                                    break;
                                }
                            } else {
                                break;
                            }


                        }

                    }

                    break;
                case 2:

                    System.out.println("Here is the author list!");
                    lineSpacer();
                    int authCount = 1;
                    if (aManage.authorList.size() <= 5) {
                        int pageCount = 1;
                        System.out.println("Page " + pageCount + " out of 1");
                        for (int i = 0; i < aManage.authorList.size(); i++) {

                            System.out.println(authCount + ". " + aManage.authorList.get(i));

                            authCount++;
                        }
                    } else {
                        double num = (double) aManage.authorList.size() / 5;

                        int num1 = (int) Math.ceil(num);

                        int pageCount = 1;
                        int g = 0;
                        for (int i = 0; i < num1; i++) {
                            System.out.println("Page " + pageCount + " out of " + num1);
                            for (int j = 0; j < 5; j++) {
                                try {
                                    System.out.println(authCount + ". '" + aManage.authorList.get(g));

                                    authCount++;
                                    g++;
                                } catch (IndexOutOfBoundsException e) {

                                    break;
                                }
                            }
                            System.out.println("WHAT DO YOU WANT TO DO?");
                            System.out.println("M. Show more");
                            System.out.println("E. Edit Author");
                            System.out.println("A. Add Author");
                            System.out.println("B. Back to previous menu");
                            String userString = input.next();
                            if (userString.equalsIgnoreCase("M")) {
                                pageCount++;
                                continue;
                            } else if (userString.equalsIgnoreCase("E")) {
                                if (user.logged) {
                                    System.out.println("Which Author would you like to edit? (Please enter number)");
                                    int authNum = input.nextInt();
                                    System.out.println("What would you like to change the author Name to?");
                                    input.nextLine();
                                    String edit = input.nextLine();
                                    aManage.authorList.set(authNum - 1, edit);

                                    uploadAuthors(aManage);
                                    System.out.println("edited");
                                    break;


                                } else {
                                    System.out.println("You cannot create a edit without being logged in!");
                                    break;
                                }
                            } else if (userString.equalsIgnoreCase("A")) {
                                if (user.logged) {
                                    Author author = new Author();
                                    author.createAuthor();
                                    break;
                                } else {
                                    System.out.println("You cannot create a Author without being logged in!");
                                    break;
                                }
                            } else {
                                break;
                            }


                        }

                    }

                    lineSpacer();

                    break;
                case 3:
                    if (user.logged) {
                        Quote quote = new Quote();
                        quote.createQuote(user);
                        System.out.println("Your quote has been Created!");
                    } else {
                        System.out.println("You cannot create a quote without being logged in!");
                    }
                    break;
                case 4:
                    if (user.logged) {
                        Author author = new Author();
                        author.createAuthor();
                    } else {
                        System.out.println("You cannot add an author without being logged in!");
                    }
                    break;
                case 5:
                    boolean holder1 = true;
                    int breaker = 0;

                    while (holder1) {
                        if (breaker == 10) {
                            break;
                        }
                        System.out.println("What author would you like to search for? (Please enter name)");
                        input.nextLine();
                        String authName = input.nextLine();

                        File file = new File("authors.csv");
                        Scanner dawg = new Scanner(file);
                        int count3 = 0;
                        int lineCount = 1;
                        while (dawg.hasNextLine()) {
                            String name = dawg.nextLine();
                            if (name.equalsIgnoreCase(authName)) {
                                int keep = lineCount;
                                count3 = 1;
                                System.out.println(authName + " Found!");
                                System.out.println("Here are their Quotes!");
                                for (int i = 0; i <= manage.quoteList.size() - 1; i++) {
                                    if (manage.quoteList.get(i)[0].equalsIgnoreCase(String.valueOf(lineCount))) {
                                        Quote quote = new Quote();
                                        quote.showQuote(manage, i);
                                    }
                                }
                                System.out.println("What would you like to do?");
                                System.out.println("1. Search for another author");
                                System.out.println("2. Continue back to main menu");
                                String userEnter = input.next();
                                if (userEnter.equalsIgnoreCase("2")) {
                                    breaker = 10;
                                    break;
                                }

                            }
                            lineCount++;
                        }
                        if (count3 == 0) {
                            System.out.println("Sorry that author is not in our database");
                            System.out.println("What would you like to do?");
                            System.out.println("1. Search for another author");
                            System.out.println("2. Continue back to main menu");
                            String userEnter = input.next();
                            if (userEnter.equalsIgnoreCase("2")) {
                                breaker = 10;
                                break;
                            }
                        }
                        dawg.close();

                    }


                    break;
                case 6:
                    boolean hold6 = true;
                    while (hold6) {
                        System.out.println("Here is your Random Quote!");
                        int rand = (int) (Math.random() * manage.quoteList.size());
                        Quote quote = new Quote();
                        quote.showQuote(manage, rand);
                        lineSpacer();
                        System.out.println("WHAT DO YOU WANT TO DO?");
                        System.out.println("M. Show another random quote");
                        System.out.println("E. Edit quote");
                        System.out.println("D. Delete quote");
                        System.out.println("A. Add Quote");
                        System.out.println("B. Back to previous menu");
                        String userString = input.next();
                        if (userString.equalsIgnoreCase("M")) {
                            continue;
                        } else if (userString.equalsIgnoreCase("E")) {
                            if (user.logged) {
                                if (manage.quoteList.get(rand)[3].equalsIgnoreCase(user.userName) || user.isAdmin) {
                                    System.out.println("What would you like to change the text to?");
                                    input.nextLine();
                                    String edit = input.nextLine();
                                    manage.quoteList.get(rand)[1] = edit;
                                    uploadQuotes(manage);
                                    System.out.println("edited");

                                } else {
                                    System.out.println("You cannot edit that quote as you did not enter it.");
                                }
                            } else {
                                System.out.println("You cannot create a edit without being logged in!");
                            }
                            hold6 = false;


                        } else if (userString.equalsIgnoreCase("D")) {
                            if (user.logged) {
                                if (manage.quoteList.get(rand)[3].equalsIgnoreCase(user.userName) || user.isAdmin) {

                                    manage.quoteList.remove(rand);
                                    uploadQuotes(manage);
                                    System.out.println("Removed");

                                } else {
                                    System.out.println("You cannot Delete that quote as you did not enter it.");
                                }
                            } else {
                                System.out.println("You cannot delete a quote without being logged in!");
                            }
                            hold6 = false;

                        } else if (userString.equalsIgnoreCase("A")) {
                            if (user.logged) {
                                quote = new Quote();
                                quote.createQuote(user);
                                System.out.println("Your quote has been Created!");
                            } else {
                                System.out.println("You cannot create a quote without being logged in!");
                            }
                            hold6 = false;
                        } else {
                            hold6 = false;
                        }

                    }
                    break;
                case 7:
                    user.createAccount();
                    break;
                case 8:
                    user.logIn();

                    break;
                case 9:
                    user.logOut();
                    break;
                case 11:
                    System.out.println("Have a nice Day!");
                    hold = false;
                    break;
                case 10:
                    if (user.isAdmin) {
                        System.out.println("Welcome Admin, what do you want to do?");
                        System.out.println("1. List all of the Quotes");
                        System.out.println("2. List all of the Authors");
                        System.out.println("3. List all of the Users");
                        System.out.println("4. Quit");
                        int adminChoice = input.nextInt();

                        switch (adminChoice) {
                            case 1:

                                break;
                            case 2:

                                break;
                            case 3:
                                user.showUser(uManage);
                                lineSpacer();
                                System.out.println("What do you want to do next?");
                                System.out.println("1. Delete a User");
                                System.out.println("2. Go Back to Main menu");
                                int choice = input.nextInt();
                                if (choice == 1) {
                                    System.out.println("What user do you want to Delete? (Please enter User Number)");
                                    int deleteChoice = input.nextInt();
                                    String[] setter = {" ", " ", " ", " "};
                                    uManage.userList.set(deleteChoice - 1, setter);
                                    uploadUsers(uManage);
                                    File file = new File("quotes.csv");
                                    Scanner scan = new Scanner(file);
                                    for (int i = 0; i <= manage.quoteList.size()-1 ; i++) {
                                        if (manage.quoteList.get(i)[3].equals(String.valueOf(deleteChoice + 1))) {
                                            System.out.println(String.valueOf(deleteChoice + 1));
                                            manage.quoteList.remove(i);
                                            uploadQuotes(manage);
                                        }
                                        i--;
                                    }
                                } else {
                                    System.out.println("See Ya Admin!");
                                }

                                break;
                            case 4:
                                System.out.println("Enjoy the Main Menu");
                                break;
                        }
                    } else {
                        System.out.println("Sorry you are not an admin!");
                    }


                    break;
            }


        }

    }

    public static void lineSpacer() {
        System.out.println("---------------------------------------------------------------");
    }

    public static void uploadQuotes(QuoteManager manage) throws IOException {


        PrintWriter write = new PrintWriter("quotes.csv");
        write.print("");
        write.print("Author;text;dateAdded;Username");
        write.print("\n");
        int j = 0;
        for (int i = 0; i <= manage.quoteList.size() - 1; i++) {

            write.append(manage.quoteList.get(i)[j] + ";" + manage.quoteList.get(i)[j + 1] + ";" + manage.quoteList.get(i)[j + 2] + ";" + manage.quoteList.get(i)[j + 3]);
            write.append("\n");
        }
        write.flush();
        write.close();
    }


    public static void uploadAuthors(AuthorManager aManage) throws IOException {
        PrintWriter write = new PrintWriter("authors.csv");
        write.print("");
        write.print("AuthorName");
        write.print("\n");
        int j = 0;
        for (int i = 0; i <= aManage.authorList.size() - 1; i++) {

            write.append(aManage.authorList.get(i));
            write.append("\n");
        }
        write.flush();
        write.close();

    }

    public static void uploadUsers(UserManager uManage) throws IOException {

        PrintWriter write = new PrintWriter("users.csv");
        write.print("");
        write.print("UserName;Password;Email;AdminStatus");
        write.print("\n");
        int j = 0;
        for (int i = 0; i <= uManage.userList.size() - 1; i++) {

            write.append(uManage.userList.get(i)[j] + ";" + uManage.userList.get(i)[j + 1] + ";" + uManage.userList.get(i)[j + 2] + ";" + uManage.userList.get(i)[j + 3]);
            write.append("\n");
        }
        write.flush();
        write.close();

    }
}