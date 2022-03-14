package com.company;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main{

    static ArrayList<String> menuButtons = new ArrayList<>(Arrays.asList("Create account", "Login", "Search for books"));
    static ArrayList<String> menuButtonsLoggedIn = new ArrayList<>(Arrays.asList("Search for Books", "Order History", "Shopping Cart", "Account Settings", "Log out"));
    static ArrayList<String> menuButtonsAdmin = new ArrayList<>(Arrays.asList("Search for Books", "Manage Users", "Manage Books", "Account Settings", "Log out"));
    static ArrayList<String> searchBooksButtons = new ArrayList<>(Arrays.asList("Search by name", "Search by category", "Search by author"));
    public static boolean isUserLoggedIn = false;
    public static User currentUser = null;

    public static void main(String[] args){
        // write your code here
        System.out.println("weed");
        System.out.println("\f");
        System.out.flush();
        homePage();

    }

    private static void homePage(){
        while(true){
            mainMenuButtons();
            System.out.println("Choose one of the options(Type the number): ");
            Scanner uInput = new Scanner(System.in);
            int userInput = uInput.nextInt();
            mainMenuChoice(userInput);
        }
    }

    private static void mainMenuButtons(){
        if(isUserLoggedIn){
            int x = 1;
            for(String button : menuButtonsLoggedIn){
                System.out.println(x + "." + button);
                x++;
            }
        } else if(isUserLoggedIn && currentUser.isAdmin){
            int x = 1;
            for(String button : menuButtonsAdmin){
                System.out.println(x + "." + button);
                x++;
            }
        } else{
            int x = 1;
            for(String button : menuButtons){
                System.out.println(x + "." + button);
                x++;
            }
        }
    }

    private static void mainMenuChoice(int userInput){
        if(isUserLoggedIn && currentUser.isAdmin){
            switch(userInput){
                case 1:{
                    searchForBooksMenu();
                }
            }
        } else if(isUserLoggedIn){
            switch(userInput){
                case 1:{
                    searchForBooksMenu();
                }
            }
        } else{
            switch(userInput){
                case 1:
                    currentUser = new User();
                    currentUser.saveUser();
                    isUserLoggedIn = true;
                    break;
            }
        }
    }


    private static void searchForBooksMenu(){
        int x = 1;
        for(String button : searchBooksButtons){
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
                case 1:{
                    searchBookByName();
                }
            }
        }
    }

    private static void searchBookByName(){
        System.out.print("Type the name of the book: ");
        Scanner uInput = new Scanner(System.in);
        String userInput = uInput.nextLine();
    }

    private static int userInputInt(int listLength){
       Scanner uInput = new Scanner(System.in);
       int userInput = uInput.nextInt();
       while(userInput < 1 || userInput > listLength){
           System.out.print("Number is out of bounds, please enter a number from 1 to " + listLength + ": ");
           userInput = uInput.nextInt();
       }
       return userInput;
    }
}
