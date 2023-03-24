package org.pos;


// Electronics product class
public class Electronics extends Product {
    public Electronics(String name, double price) {
        super(name, price);
    }
    
    public void display() {
        System.out.println("Electronics - Name: " + name + ", Price: " + price);
    }
}
