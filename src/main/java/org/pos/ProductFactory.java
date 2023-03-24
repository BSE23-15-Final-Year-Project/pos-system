package org.pos;
// product factory
public class ProductFactory {
    // Factory method to create products
    public Product createProduct(String productType, String productName, double price) {
        switch (productType) {
            case "GROCERIES":
                return new Groceries(productName, price);
            case "CLOTHING":
                return new Clothing(productName, price);
            case "ELECTRONICS":
                return new Electronics(productName, price);
            default:
                throw new IllegalArgumentException("Invalid product type: " + productType);
        }
    }
}
