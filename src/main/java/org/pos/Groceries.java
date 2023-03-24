package org.pos;

// Groceries product class
public class Groceries extends Product {
    public Groceries(String name, double price) {
        super(name, price);
    }
    
    public void display() {
        System.out.println("Groceries - Name: " + name + ", Price: " + price);
    }
}



