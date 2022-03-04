package com.company;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Main {
    static ArrayList<String> menuButtons = new ArrayList<String>(Arrays.asList("Create account", "Login to an existing account", "Search for books"));
    static ArrayList<String> menuButtonsLoggedIn = new ArrayList<String>(Arrays.asList("Create account", "Login to an existing account", "Search for books"));
    static boolean isUserLoggedIn = false;
    static User currentUser = null;

    public static void main(String[] args) {
        // write your code here
        homePage();
    }


    public static void homePage() {
        while (true) {
            if (isUserLoggedIn) {

            }
            else if (isUserLoggedIn && currentUser.isAdmin) {

            }
            else {
                int x = 1;
                for (String button : menuButtons) {
                    System.out.println(x + "." + button);
                    x++;
                }
                Scanner uInput = new Scanner(System.in);
                System.out.print("Choose one of the options(Type the number or name of the option): ");
                String userInput = uInput.nextLine();
                if (userInput.equals("1") || userInput.equals("Create account")) {
                    currentUser = createAccount();
                    currentUser.saveUser();
                    System.out.println(currentUser.readUser().toString());
                    break;
                }
            }
        }
    }

    public static User createAccount() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
        Scanner uInput = new Scanner(System.in);
        System.out.print("Enter your first name: ");
        String fName = uInput.nextLine();
        System.out.print("Enter your last name: ");
        String lName = uInput.nextLine();
        System.out.print("Enter your username: ");
        String uName = uInput.nextLine();
        System.out.print("Enter your password: ");
        Console cnsl = System.console();
        if (cnsl != null) {
            char[] password = cnsl.readPassword();
            return new User(fName, lName, uName, new String(password), false);
            }
        else {
            String password = uInput.nextLine();
            return new User(fName, lName, uName, password,false);
            }
    }

}
