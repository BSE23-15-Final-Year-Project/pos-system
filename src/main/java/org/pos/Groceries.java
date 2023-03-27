package org.pos;

// Groceries product class
public class Groceries extends Product {
    String description;
    public Groceries(String name, double price, String description) {
        super(name, price);
        this.description = description;
    }
    
    public void display() {
        System.out.println("Groceries - Name: " + name + ", Price: " + price+ ", Description: "+description);
    }

    @Override
    public String description() {
        return description;
    }
}



