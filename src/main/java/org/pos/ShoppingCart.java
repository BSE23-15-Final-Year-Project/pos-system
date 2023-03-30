package org.pos;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items;
    private ProductCatalog productCatalog;
    public ShoppingCart(ProductCatalog productCatalog) {
        items = new ArrayList<>();
        this.productCatalog = productCatalog;
    }

    public void addItem(String productName) {
        Product product = productCatalog.getProduct(productName);
        if (product != null) {
            items.add(product);
            productCatalog.notifyObservers(product);
        } else {
            System.out.println("Product not found in catalog.");
        }
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

}
