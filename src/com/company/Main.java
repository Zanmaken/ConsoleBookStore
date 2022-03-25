package com.company;


import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static ArrayList<String> menuButtons = new ArrayList<>(Arrays.asList("Create account", "Login", "Search for books", "Exit program"));
    static ArrayList<String> menuButtonsLoggedIn = new ArrayList<>(Arrays.asList("Search for Books", "Order History", "Shopping Cart", "Account Settings", "Log out", "Exit program"));
    static ArrayList<String> menuButtonsAdmin = new ArrayList<>(Arrays.asList("Search for Books", "Manage Users", "Manage Books", "Account Settings", "Log out", "Exit program"));
    static ArrayList<String> searchBooksButtons = new ArrayList<>(Arrays.asList("Search by name", "Search by category", "Search by author"));
    static ArrayList<String> manageBooksButtons = new ArrayList<>(Arrays.asList("Add a book", "Remove a book", "Back"));
    static ArrayList<String> manageUsersButtons = new ArrayList<>(Arrays.asList("Add new user", "Remove user", "Back"));
    static ArrayList<Book> booksInStorage = new ArrayList<>();
    public static User currentUser = null;
    public static Scanner uInput = new Scanner(System.in).useDelimiter("\\n");
    static User adminUser = new User("Rafał", "Kryczyk", "Knaga", "12345", true);
    public static void main(String[] args) {
        currentUser = adminUser;
        readBooks();
        mainMenu();
    }

    private static void mainMenu() {
        mainMenuButtonsPrint();
        switch (checkUserLoginStatus()) {
            case 0:
                mainMenuChoiceNotLogged();
                break;
            case 1:
                mainMenuChoiceLoggedIn();
                break;
            case 2:
                mainMenuChoiceAdmin();
                break;
        }
    }

    private static void printButtons(ArrayList<String> buttonList) {  // Prints menu buttons from list to the console when called
        for (int x = 1; x <= buttonList.size(); x++)
            System.out.println(x + "." + buttonList.get(x - 1));
    }

    private static void mainMenuButtonsPrint() {          // Uses correct printButton() method,
        switch (checkUserLoginStatus()) {             // depending on the login status of the current user.
            case 0:
                printButtons(menuButtons);
                break;
            case 1:
                printButtons(menuButtonsLoggedIn);
                break;
            case 2:
                printButtons(menuButtonsAdmin);
                break;
        }
        System.out.println("Choose one of the options(Type the number): ");
    }

    private static void mainMenuChoiceNotLogged() {
        switch (userInputInt(menuButtons.size())) {
            case 1:
                currentUser = addUser();
                currentUser.saveUser();
                User.readUser();
                System.out.println(currentUser.toString());
                mainMenu();
                break;
            case 2:
                System.out.println("--Login method placeholder--");
                break;
            case 3:
                searchForBooksMenu();
                break;
            case 4:
                break;
        }
    }

    private static void mainMenuChoiceLoggedIn() {
        switch (userInputInt(menuButtonsLoggedIn.size())) {
            case 1:
                searchForBooksMenu();
                break;
            case 2:
                System.out.println("--Order history method placeholder--");
                break;
            case 3:
                System.out.println("--Shopping cart method placeholder--");
                break;
            case 4:
                System.out.println("--Account settings method placeholder--");
                break;
            case 5:
                logOut();
                break;
            case 6:
                break;
        }
    }

    private static void mainMenuChoiceAdmin() {
        switch (userInputInt(menuButtonsAdmin.size())) {
            case 1:
                searchForBooksMenu();
                break;
            case 2:
                System.out.println("--Manage users method placeholder--");
                break;
            case 3:
                System.out.println("--Manage books method placeholder--");
                break;
            case 4:
                System.out.println("--Account settings method placeholder--");
                break;
            case 5:
                logOut();
                break;
            case 6:
                break;
        }
    }

    private static void logOut() {
        currentUser = null;
        mainMenu();
    }

    private static int checkUserLoginStatus() {          // Checks if the user is logged in and has admin privileges
        if (currentUser != null && currentUser.isAdmin)  // then returns 0 for not logged in, 1 for logged in
            return 2;                                    // and 2 for logged in with admin privileges.
        else if (currentUser != null)
            return 1;
        else
            return 0;
    }

    private static User addUser() {
        System.out.println("Enter your first name: ");
        String firstName = uInput.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = uInput.nextLine();
        System.out.println("Enter your username: ");
        String username = uInput.nextLine();
        System.out.println("Enter your password: ");
        Console cnsl = System.console();
        String password;
        if (cnsl != null) {
             password = String.valueOf(cnsl.readPassword());

        } else {
             password = uInput.nextLine();
        }
        return new User(firstName, lastName, username, password);
    }

    private static User addUserAsAdmin() {
        System.out.println("Is the user an admin? y/n: ");
        String isAdminAsString = uInput.nextLine();
        boolean isAdmin;
        while (true) {
            if (isAdminAsString.equals("y") || isAdminAsString.equals("Y")){
                isAdmin = true;
                break;
            }
            else if (isAdminAsString.equals("n") || isAdminAsString.equals("N")) {
                isAdmin = false;
                break;
            }
        }
        System.out.println("Enter your first name: ");
        String firstName = uInput.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = uInput.nextLine();
        System.out.println("Enter your username: ");
        String username = uInput.nextLine();
        System.out.println("Enter your password: ");
        Console cnsl = System.console();
        String password;
        if (cnsl != null) {
            password = String.valueOf(cnsl.readPassword());

        } else {
            password = uInput.nextLine();
        }
        return new User(firstName, lastName, username, password);
    }

    private static void searchForBooksMenu() {
        int x = 1;
        printButtons(searchBooksButtons);
        System.out.println("Choose one of the options above(Type in the number): ");
        while (true) {
            int userInput = uInput.nextInt();
            if (userInput > searchBooksButtons.size() || userInput < 1) {
                System.out.println("Number is out of bounds, please try again.");
                continue;
            }
            switch (userInput) {
                case 1: {
                    searchBookByName();
                }
            }
        }
    }

    private static void manageBooksMenu() {
        printButtons(manageBooksButtons);
        System.out.println("Choose one of the options above(Type in the number): ");
        while (true) {
            int userInput = uInput.nextInt();
            if (userInput > manageBooksButtons.size() || userInput < 1) {
                System.out.println("Number is out of bounds, please try again.");
                continue;
            }
            switch (userInput) {
                case 1: {
                    addBook();
                    break;
                }
                case 2: {
                    System.out.println("--Placeholder for remove books method--");
                    break;
                }
                case 3: {
                    break;
                }
            }
        }
    }

    private static void manageUsersMenu(){

    }

    private static void searchBookByName() {
        System.out.print("Type the name of the book: ");
        Scanner uInput = new Scanner(System.in);
        String userInput = uInput.nextLine();
    }

    private static void addBook() {
        System.out.println("Enter the name of the book: ");
        String name = uInput.nextLine();
        System.out.println("Enter the author of the book: ");
        String author = uInput.nextLine();
        System.out.println("Enter the date the book was released: ");
        String releaseDate = uInput.nextLine();
        System.out.println("Enter the categories of the book: ");
        String categoryInput = uInput.nextLine();
        ArrayList<String> categories = new ArrayList<>();
        while (!categoryInput.equals("")) {
            categories.add(categoryInput);
            categoryInput = uInput.nextLine();
        }
        Book tempBook = new Book(name, author, releaseDate, categories);
        tempBook.saveBook();
    }

    private static void readBooks() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("Books.dat"));
            while(true) {
                try {
                    booksInStorage.add((Book) in.readObject());
                } catch(EOFException ex){
                    break;
                } catch(ClassNotFoundException ex){
                    ex.printStackTrace();
                }
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }

    }

    private static int userInputInt(int listLength) {  // Gets the user's input for selecting menu buttons
        do {                                           // and returns the choice as an int.
            try {
                int numInput = uInput.nextInt();
                uInput.nextLine();
                if (numInput < 1 || numInput > listLength) {
                    System.out.println("Number must be from 1 to " + listLength + ". Please try again: ");
                    continue;
                }
                return numInput;
            }
            catch (InputMismatchException ex) {
                System.out.println("Input must be an integer!!! Please try again: ");
                uInput.next();
            }
        } while (true);
    }

    private static String userInputAsString() {
        String userInput = uInput.nextLine();
        uInput.close();
        return userInput;
    }


}
