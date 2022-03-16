package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    static ArrayList<String> menuButtons = new ArrayList<>(Arrays.asList("Create account", "Login", "Search for books", "Exit program"));
    static ArrayList<String> menuButtonsLoggedIn = new ArrayList<>(Arrays.asList("Search for Books", "Order History", "Shopping Cart", "Account Settings", "Log out", "Exit program"));
    static ArrayList<String> menuButtonsAdmin = new ArrayList<>(Arrays.asList("Search for Books", "Manage Users", "Manage Books", "Account Settings", "Log out", "Exit program"));
    static ArrayList<String> searchBooksButtons = new ArrayList<>(Arrays.asList("Search by name", "Search by category", "Search by author"));
    public static User currentUser = null;

    public static void main(String[] args) {
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
                currentUser = new User();
                currentUser.saveUser();
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
            return 2;                                   // and 2 for logged in with admin privileges.
        else if (currentUser != null)
            return 1;
        else
            return 0;
    }


    private static void searchForBooksMenu() {
        int x = 1;
        for (String button : searchBooksButtons) {
            System.out.println(x + "." + button);
            x++;
        }
        System.out.println("Choose one of the options above(Type in the number): ");
        while (true) {
            Scanner uInput = new Scanner(System.in);
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

    private static void searchBookByName() {
        System.out.print("Type the name of the book: ");
        Scanner uInput = new Scanner(System.in);
        String userInput = uInput.nextLine();
    }

    private static int userInputInt(int listLength) {  // Gets the user's input for selecting menu buttons
        int userInput;                                 // and returns the choice as an int.
        Scanner uInput = new Scanner(System.in);
        try {
            userInput = uInput.nextInt();
            while (userInput < 1 || userInput > listLength) {
                System.out.print("Number is out of bounds, please enter a number from 1 to " + listLength + ": ");
                userInput = userInputInt(listLength);
            }
        } catch (InputMismatchException ex){
                System.out.println("Input must be an integer!!! Please try again: ");
                userInput = userInputInt(listLength);
        }
        return userInput;
    }



    private static String userInputAsString() {
        Scanner uInput = new Scanner(System.in);
        return uInput.nextLine();
    }
}
