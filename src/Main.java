import exceptions.AgeException;
import exceptions.MoneyException;
import exceptions.NameException;
import park.Park;
import park.users.UserTypeOfTicket;
import park.users.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Main {
    private static Park park = new Park("Disneyland","123456");
    public static void main(String[] args) throws Exception {
        //TODO create menu methods for each case
        mainMenu();
    }

    public static void mainMenu() throws Exception {
        String[] options = {"User menu", "Admin menu", "Exit"};
        printOptions(options);

        switch (readString()) {
            case "1": // ticket menu
                // TODO call method..
                userMenu();
                break;
            case "2": // Control users menu - use user -> shopping/cinema/funZone
                // TODO call method..
                System.out.print("Enter admin password: ");
                if (park.ckeckPassword(readString())) {
                    adminMenu();
                } else {
                    System.out.println("Username and password doesn't match!");
                }
                break;
            case "3": // exit
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        mainMenu();
    }
    
    public static void userMenu() throws Exception {
        String[] options = {"Buy ticket","Add credits","Go shopping","Watch a movie","Disneyland attractions","Exit"};
        printOptions(options);
        String command = readString();
        switch (command) {
            case "1":
                buyTicketMenu();
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                break;
            case "6":
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        userMenu();
    }

    private static void adminMenu() throws IOException {
        String[] options = {"Stores","Attractions","Cinema","Exit"};
        printOptions(options);
        String command = readString();
        switch (command) {
            case "1":
                storesMenu();
                break;
            case "2":
                attractionsMenu();
                break;
            case "3":
                cinemaMenu();
                break;
            case "4":
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        adminMenu();
    }

    private static void storesMenu() throws IOException {
        String[] options = {"Remove store", "Add new store", "Get store products ", "Add products to store", "Exit"};
        printOptions(options);
        String command = readString();
        switch (command) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        storesMenu();
    }

    private static void attractionsMenu() throws IOException {
        String[] options = {"Remove attraction", "Add new attraction", "Show all attractions ", "Change attraction", "Exit"};
        printOptions(options);
        String command = readString();
        switch (command) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        attractionsMenu();
    }

    private static void cinemaMenu() throws IOException {
        String[] options = {"Add movie", "Remove movie", "Add foods to cinema's store", "Remove foods from cinema store", "Exit"};
        printOptions(options);
        String command = readString();
        switch (command) {
            case "1":
                break;
            case "2":
                break;
            case "3":
                break;
            case "4":
                break;
            case "5":
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        cinemaMenu();
    }

    //TODO check if the person already exist
    public static void buyTicketMenu() throws Exception {
        String[] options = {"SingleTicket", "GroupTicket", "Exit"};
        printOptions(options);
        List<User> users = new ArrayList<>();
        switch (readString()) {
            case "1": // TODO create person and ticket
                users.addAll(createUser(1));
                break;
            case "2":
                //TODO create groups and tickets
                users.addAll(createUser(readSizeOfGroup()));
                break;
            case "3":  //exit
                return;
            default:
                System.out.println("Invalid choice!");
                break;
        }
        park.addUsers(users);
    }

    //TODO  TEST exception when group is less than 2
    public static int readSizeOfGroup() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter number of members: ");
        String input = reader.readLine();
        int numberOfUsers;
        try {
            numberOfUsers = Integer.parseInt(input);
            System.out.println();
        } catch (Exception e) {
            // System.out.println(e);
            System.out.print("Please enter a valid number: ");
            return readSizeOfGroup();
        }

        if(numberOfUsers < 2) {
            System.out.println("The group can not have less than 2 members!");
            return readSizeOfGroup();
        }

        return numberOfUsers;
    }

    //TODO check if there is such a user already
    //TODO create tickets for each user
    //TODO add case when user's age < 14 and it's not in group and when the group doesn't have member older than 14
    public static List<User> createUser(int numberOfUsers) throws Exception {
        List<User> users = new ArrayList<>();
        UserTypeOfTicket userTypeOfTicket = null;
        if (numberOfUsers == 5) {
            userTypeOfTicket = userTypeOfTicket.SMALLGROUP;
        } else if (numberOfUsers > 5) {
            userTypeOfTicket = userTypeOfTicket.BIGGROUP;
        }

        for (int i = 0; i < numberOfUsers; i++) {

            System.out.print("Please enter a name: ");
            String name = readName();
            System.out.print("Please enter age: ");
            int age = readAge();
            System.out.print("What's the budget of " + name + ": ");
            double budget = readMoney();

            if (userTypeOfTicket == null) {
                if (age < 18) {
                    userTypeOfTicket = userTypeOfTicket.UNDER18;
                } else {
                    userTypeOfTicket = readTicketType();
                }
            }
            users.add(new User(name, age, budget, userTypeOfTicket));
        }
        return users;
    }
    //TODO make readName and validateName - INTERFACE
    public static String readName() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String name = reader.readLine();
        try {
            validateName(name);
        } catch (NameException e) {
            System.out.println(e);
            System.out.print("Please enter valid name ");
            return readName();
        }
        return name;
    }

    public static String validateName(String name) throws NameException {
        if (name.length() < 3 || name.length() > 35) {
            throw new NameException("The name has to be between 3 and 35 symbols!");
        } else if (!name.matches("[a-zA-Z]+")) {
            throw new NameException("The name must contain only letters!");
        } else {
            return name;
        }
    }

    private static int readAge() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        int age;
        try {
            age = validateAge(input);
        } catch (AgeException e) {
            System.out.println(e);
            System.out.print("Please enter a valid age: ");
            return readAge();
        }
        System.out.println();
        return age;
    }

    private static int validateAge(String input) throws AgeException {
        int age;
        try {
            age = Integer.parseInt(input);
        } catch (Exception e) {
            throw new AgeException("The entered is not a number!");
        }
        if (age < 1 || age > 114) {
            throw new AgeException("Invalid age! Age has to be between 1 and 114");
        }
        return age;
    }

    //TODO make readMoney and validateMoney -INTERFACE
    private static double readMoney() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();
        double money;
        try {
            money = validateMoney(input);
        } catch (MoneyException e) {
            System.out.println(e);
            System.out.print("Please enter again: ");
            return readMoney();
        }
        return money;
    }

    private static double validateMoney(String input) throws MoneyException {
        double money;
        try {
            money = Double.parseDouble(input);
        } catch (NumberFormatException e) {
            throw new MoneyException("The entered is not a number!");
        }
        if(money < 0) {
            throw new MoneyException("Money can't be less than 0! ");
        }

        return money;
    }

    private static UserTypeOfTicket readTicketType() throws IOException {
        String[] options = {"Adult", "Pensioner", "Disabled"};
        printOptions(options);
        String command = readString();
        switch (command) {
            case "1":
                return UserTypeOfTicket.ADULT;
            case "2":
                return UserTypeOfTicket.PENSIONEER;
            case "3":
                return UserTypeOfTicket.DISABLED;
            default:
                System.out.println("Not a valid choice!");
                System.out.println("Please enter your choice again!");
                return readTicketType();
        }
    }

    //TODO add method readingTicketNumber - throws exception
    private static void addCredits() throws Exception {
        System.out.print("Please enter user name: ");
        String name = readName();
        int ticketNumber = Integer.parseInt(readString());
        User currentUser =  park.findUser(name,ticketNumber);
        if(currentUser == null) {
            System.out.println("There is no such user!");
        } else {
            
        }
    }

    private static String readString() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = reader.readLine();
        return command;
    }

    private static void printOptions(String[] options) {
        IntStream.range(0, options.length)
                .mapToObj(i -> (i + 1) + "." + options[i])
                .forEach(System.out::println);
    }
}