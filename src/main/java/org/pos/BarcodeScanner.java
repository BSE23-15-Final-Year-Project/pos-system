package org.pos;

import org.pos.exceptions.ProductNotFoundException;

public class BarcodeScanner {
    public void scan(ShoppingCart cart, String barcode) throws ProductNotFoundException {
        Product product = ProductCatalog.getInstance().lookupProduct(barcode);
        if (product != null) {
            cart.addItem(product.getName());
        } else {
            throw new ProductNotFoundException("Product not found.");
        }
    }
}