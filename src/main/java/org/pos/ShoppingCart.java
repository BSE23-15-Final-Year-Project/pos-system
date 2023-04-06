package org.pos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ShoppingCart {
    private List<Product> items;
    private Set<CartObserver> observers = new HashSet<>();
    private ProductCatalog productCatalog;
    public ShoppingCart() {
        items = new ArrayList<>();
    }

    public void addItem(String productName) {
        Product product = ProductCatalog.getInstance().getProduct(productName);
        items.add(product);
        notifyObservers(product);
    }

    public void removeItem(Product product) {
        items.remove(product);
    }

    public List<Product> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0.0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public void displayItems() {
        if (items.isEmpty()) {
            System.out.println("Shopping cart is empty.");
        } else {
            System.out.println("\n");
            System.out.println("......Shopping Cart........");
            for (Product item : items) {
                item.display();
            }
        }
    }


    public void addObserver(CartObserver observer) {
        this.observers.add(observer);
    }

    public void removeObserver(CartObserver observer) {
        this.observers.remove(observer);
    }

    public void notifyObservers(Product product) {
        for (CartObserver observer : observers) {
            observer.update(product);
        }
    }

}
