package org.pos;
// product factory
public class ProductFactory {
    // Factory method to create products
    public Product createProduct(String productType, String productName, double price, String description) {
        switch (productType) {
            case "GROCERIES":
                return new Groceries(productName, price, description);
            case "CLOTHING":
                return new Clothing(productName, price, description);
            case "ELECTRONICS":
                return new Electronics(productName, price, description);
            default:
                throw new IllegalArgumentException("Invalid product type: " + productType);
        }
    }
}
