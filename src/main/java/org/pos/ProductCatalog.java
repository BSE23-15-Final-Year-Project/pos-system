package org.pos;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ProductCatalog {
    private static ProductCatalog instance;
    private Map<String, Product> products;
    private Set<CartObserver> observers = new HashSet<>();


    private ProductCatalog() {
        products = new HashMap<>();
        // Add some initial products to the catalog
        addProduct(ProductType.ELECTRONICS.toString(), "Smartphone", 500.0, "brand new Samsung");
        addProduct(ProductType.GROCERIES.toString(), "Bread", 2.0, "Milk bread for your breakfast");
        addProduct(ProductType.CLOTHING.toString(), "T-shirt", 10.0, "classic shirt");
    }
    
    public static synchronized ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }
    
    public void addProduct(String productType, String name, double price, String description) {
        try {
            ProductType.valueOf(productType);
        }catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("Product Type is invalid");
        }
        ProductFactory factory = new ProductFactory();
        Product product = factory.createProduct(productType, name, price, description);
        products.put(product.getName(), product);
    }
    
    public Product getProduct(String name) {
        return products.get(name);
    }

    public ProductType getProductType(String productName) {
        return ProductType.valueOf(products.get(productName).getClass().getSimpleName().toUpperCase());
    }

    public  int getProductCount(){
        return products.size();
    }
    
    public void displayCatalog() {
        System.out.println("Product Catalog:");
        for (Product product : products.values()) {
            product.display();
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


