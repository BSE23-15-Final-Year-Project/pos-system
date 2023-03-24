package org.pos;


// Clothing product class
public class Clothing extends Product {
    public Clothing(String name, double price) {
        super(name, price);
    }
    
    public void display() {
        System.out.println("Clothing - Name: " + name + ", Price: " + price);
    }
}
