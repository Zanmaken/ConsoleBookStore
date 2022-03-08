package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    static ArrayList<String> menuButtons = new ArrayList<String>(Arrays.asList("Create account", "Login", "Search for books"));
    static ArrayList<String> menuButtonsLoggedIn = new ArrayList<String>(Arrays.asList("Search for Books", "Order History", "Shopping Cart", "Account Settings", "Log out"));
    static ArrayList<String> menuButtonsAdmin = new ArrayList<String>(Arrays.asList("Search for Books", "Manage Users", "Manage Books", "Account Settings", "Log out"));
    static ArrayList<String> searchBooksButtons = new ArrayList<String>(Arrays.asList("Search by name", "Search by category", "Search by author"));
    public static boolean isUserLoggedIn = false;
    public static User currentUser = null;

    public static void homePage() {
        while (true) {
            mainMenuButtons();
            System.out.println("Choose one of the options(Type the number): ");
            Scanner uInput = new Scanner(System.in);
            int userInput = uInput.nextInt();
            mainMenuChoice(userInput);
        }
    }

    public static void mainMenuButtons(){
        if(isUserLoggedIn){
            int x = 1;
            for (String button : menuButtonsLoggedIn) {
                System.out.println(x + "." + button); x++;
            }
        }
        else if(isUserLoggedIn && currentUser.isAdmin){
            int x = 1;
            for (String button : menuButtonsAdmin){
                System.out.println(x + "." + button); x++;
            }
        }
        else {
            int x = 1;
            for (String button : menuButtons) {
                System.out.println(x + "." + button);
                x++;
            }
        }
    }

    public static void mainMenuChoice (int userInput){
        if(isUserLoggedIn && currentUser.isAdmin){
            switch (userInput){
                case 1:{
                    searchForBooksMenu();
                }
            }
        }
        else if(isUserLoggedIn){
            switch (userInput){
                case 1:{
                    searchForBooksMenu();
                }
            }
        }
        else {
            switch(userInput){
                case 1:{
                   currentUser = User.createAccount();
                   currentUser.saveUser();
                   isUserLoggedIn = true;
                }
            }
        }
    }


    public static void searchForBooksMenu(){
        int x = 1;
        for(String button : searchBooksButtons) {
            System.out.println(x + "." + button);
            x++;
        }
        System.out.println("Choose one of the options above(Type in the number): ");
        while(true){
            Scanner uInput = new Scanner(System.in);
            int userInput = uInput.nextInt();
            if(userInput > searchBooksButtons.size() || userInput < 1){
                System.out.println("Number is out of bounds, please try again.");
                continue;
            }
            switch(userInput){
                case 1: {
                    searchBookByName();
                }
            }
        }

    }

    public static void searchBookByName(){
        System.out.print("Type the name of the book: ");
        Scanner uInput = new Scanner(System.in);
        String userInput = uInput.nextLine();

    }

}
