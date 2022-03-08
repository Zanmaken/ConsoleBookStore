package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable {
    String username;
    String firstName;
    String lastName;
    String password;
    boolean isAdmin = false;
    double balance;
    ArrayList<Order> orderHistory;

    public User(String fName, String lName, String uName,String uPassword, boolean adminPrivilege){
        firstName = fName;
        lastName = lName;
        username = uName;
        isAdmin = adminPrivilege;
        password = uPassword;
    }

    public void saveUser() {
        try (FileOutputStream fos = new FileOutputStream("src/com/company/Users.dat", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(this);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public User readUser(){
        try{
            FileInputStream fileIn = new FileInputStream("src/com/company/Users.dat");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            User cUser = (User) objectIn.readObject();
            objectIn.close();
            return cUser;
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
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

    public String toString(){
        return this.username + " | " + this.firstName + " | " + this.lastName + " | " + this.password;
    }

}





