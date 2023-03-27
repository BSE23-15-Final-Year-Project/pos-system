package org.pos;

// Concrete observer class
public class SalesPerson implements CartObserver {
    private String name;

    public SalesPerson(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    @Override
    public void update(Product product) {
        System.out.println("Salesperson " + name + " notified: " + product.getName() + " added to cart.");
    }
}
