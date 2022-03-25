package com.company;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class Book implements Serializable {
    String name;
    String author;
    String releaseDate;
    ArrayList<String> categories;

    public Book(String name, String author, String releaseDate, ArrayList<String> categories) {
        this.name = name;
        this.author = author;
        this.releaseDate = releaseDate;
        this.categories = categories;
    }

    public void saveBook() {
        try (FileOutputStream fos = new FileOutputStream("Books.dat", true);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}


