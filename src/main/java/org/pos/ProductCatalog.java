package org.pos;

import java.util.HashMap;
import java.util.Map;


public class ProductCatalog {
    private static ProductCatalog instance;
    private Map<String, Product> products;
    
    private ProductCatalog() {
        products = new HashMap<>();
        // Add some initial products to the catalog
        addProduct(ProductType.ELECTRONICS.toString(), "Smartphone", 500.0);
        addProduct(ProductType.GROCERIES.toString(), "Bread", 2.0);
        addProduct(ProductType.CLOTHING.toString(), "T-shirt", 10.0);
    }
    
    public static synchronized ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }
    
    public void addProduct(String productType, String name, double price) {
        try {
            ProductType.valueOf(productType);
        }catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("Product Type is invalid");
        }
        ProductFactory factory = new ProductFactory();
        Product product = factory.createProduct(productType, name, price);
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
}


