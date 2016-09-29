package com.epam.mylinkedhashmap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        MyLHM<String, Integer> myLHM = new MyLHM<>();
        myLHM.put("first", 1);
        myLHM.put("second", 2);
        myLHM.put("third", 3);
        System.out.println(myLHM);
        System.out.println(myLHM.containsKey("first"));
        System.out.println(myLHM.get("first"));
        String fileName = "./src/main/resources/myLHM.ser";
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream out = new ObjectOutputStream(fos)) {
            out.writeObject(myLHM);
            out.close();
            System.out.println("Serialization is done!");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream in = new ObjectInputStream(fis)) {
            MyLHM myLHMdeser = (MyLHM) in.readObject();
            in.close();
            System.out.println("Deserialization is done!");
            System.out.println(myLHMdeser.containsKey("second"));
            System.out.println(myLHMdeser.get("second"));
            System.out.println(myLHMdeser.containsKey("second"));
            System.out.println(myLHMdeser);
        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}
