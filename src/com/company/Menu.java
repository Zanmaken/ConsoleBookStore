package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Menu {
    static ArrayList<String> menuButtons = new ArrayList<String>(Arrays.asList("Create account", "Login", "Search for books"));
    static ArrayList<String> menuButtonsLoggedIn = new ArrayList<String>(Arrays.asList("Search for Books", "Order History", "Shopping Cart", "Account Settings", "Log out"));
    static ArrayList<String> menuButtonsAdmin = new ArrayList<String>(Arrays.asList("Search for Books", "Manage Users", "Manage Books", "Account Settings", "Log out"));
    static ArrayList<String> searchBooksButtons = new ArrayList<String>(Arrays.asList("Search by name", "Search by category", "Search by author", "Account Settings", "Log out"));


    public static void homePage(boolean isUserLoggedIn, User currentUser) {
        while (true) {
            mainMenuButtons(isUserLoggedIn, currentUser);
            System.out.println("Choose one of the options(Type the number): ");
            Scanner uInput = new Scanner(System.in);
            int userInput = uInput.nextInt();
            mainMenuChoice(userInput, isUserLoggedIn, currentUser);
        }
    }

    public static void mainMenuButtons(boolean isUserLoggedIn, User currentUser){
        if(isUserLoggedIn){
            int x = 1;
            for (String button : menuButtonsLoggedIn) System.out.println(x + "." + button); x++;
        }
        else if(isUserLoggedIn && currentUser.isAdmin){
            int x = 1;
            for (String button : menuButtonsAdmin) System.out.println(x + "." + button); x++;
        }
        else {
            int x = 1;
            for (String button : menuButtons) System.out.println(x + "." + button); x++;
        }
    }

    public static void mainMenuChoice (int userInput, boolean isUserLoggedIn, User currentUser){
        if(isUserLoggedIn && currentUser.isAdmin){
            switch (userInput){
                case 1:{
                    searchForBooksMenu();
                }

            }
        }
    }


    public static void searchForBooksMenu(){
        int x = 1;
        for(String button : searchBooksButtons) System.out.println(x + "." + button); x++;
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

                }
            }
        }

    }

}
