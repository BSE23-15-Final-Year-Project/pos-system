package org.pos;

import org.pos.exceptions.ProductNotFoundException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class ProductCatalog {
    private static ProductCatalog instance;
    private Map<String, Product> products;


    private ProductCatalog() {
        products = new HashMap<>();
        // Add some initial products to the catalog
        addProduct(ProductType.ELECTRONICS.toString(), "Smartphone", 500.0, "brand new Samsung", "1234");
        addProduct(ProductType.GROCERIES.toString(), "Bread", 2.0, "Milk bread for your breakfast","5678");
        addProduct(ProductType.CLOTHING.toString(), "T-shirt", 10.0, "classic shirt","91011");
    }
    
    public static synchronized ProductCatalog getInstance() {
        if (instance == null) {
            instance = new ProductCatalog();
        }
        return instance;
    }
    
    // method for adding products to the Catalog
    public void addProduct(String productType, String name, double price, String description, String barcode) {
        try {
            ProductType.valueOf(productType);
        }catch (IllegalArgumentException exception){
            throw new IllegalArgumentException("Product Type is invalid");
        }
        ProductFactory factory = new ProductFactory();
        Product product = factory.createProduct(productType, name, price, description);
        products.put(barcode, product);
    }


    // method to lookup for a product from the catalog
    public Product lookupProduct(String barcode) throws ProductNotFoundException {
        if (!products.containsKey(barcode)) {
            throw new ProductNotFoundException("Product not found in catalog: " + barcode);
        }
        return products.get(barcode);
    }
    
    // method to return a product by its name
    public Product getProduct(String name) {
        for (Product product : products.values()) {
            if (product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    // method to return the product type
    public ProductType getProductType(String productName) {
        for (Product product: products.values()) {
            if(product.getName().equals(productName)){
                return ProductType.valueOf(product.getClass().getSimpleName().toUpperCase());
            }
        }
        return null;
    }

    // method to return the number of products in the Catalog
    public  int getProductCount(){
        return products.size();
    }

    // method to display all products in the catalog
    public void displayCatalog() {
        System.out.println("Product Catalog:");
        for (Product product : products.values()) {
            product.display();
        }
    }
}


