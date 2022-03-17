package com.company;

import java.io.*;
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

    public User(){
        Scanner uInput = Main.uInput;
        System.out.print("Enter your first name: ");
        firstName = uInput.nextLine();
        System.out.print("Enter your last name: ");
        lastName = uInput.nextLine();
        System.out.print("Enter your username: ");
        username = uInput.nextLine();
        System.out.print("Enter your password: ");
        Console cnsl = System.console();
        if (cnsl != null)
            this.password = String.valueOf(cnsl.readPassword());
        else
            password = uInput.nextLine();
    }

    public void saveUser() {
        try (FileOutputStream fos = new FileOutputStream("src/com/company/Users.dat", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(this);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }
    public static User readUser(){
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

    public String toString(){
        return this.username + " | " + this.firstName + " | " + this.lastName + " | " + this.password;
    }

}





