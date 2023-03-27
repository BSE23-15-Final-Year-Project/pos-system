package org.pos;


// Electronics product class
public class Electronics extends Product {
    String description;
    public Electronics(String name, double price, String description) {
        super(name, price);
        this.description = description;
    }
    
    public void display() {
        System.out.println("Electronics - Name: " + name + ", Price: " + price+ ", Description: "+description);
    }

    @Override
    public String description() {
        return description;
    }
}
