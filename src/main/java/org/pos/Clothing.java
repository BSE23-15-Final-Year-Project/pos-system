package org.pos;


// Clothing product class
public class Clothing extends Product {
    String description;
    public Clothing(String name, double price, String description) {
        super(name, price);
        this.description = description;
    }
    
    public void display() {
        System.out.println("Clothing - Name: " + name + ", Price: " + price + ", Description: "+description);
    }

    @Override
    public String description() {
        return description;
    }
}
