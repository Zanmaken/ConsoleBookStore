package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class User implements Serializable {
    int userID;
    String username;
    String firstName;
    String lastName;
    String password;
    boolean isAdmin = false;
    double balance;
    ArrayList<Order> orderHistory;

    public User(String firstName, String lastName, String username, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String password, boolean isAdmin){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.isAdmin = isAdmin;
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





