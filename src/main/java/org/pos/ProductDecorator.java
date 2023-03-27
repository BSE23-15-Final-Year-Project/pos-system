package org.pos;

// ProductDecorator abstract class
abstract class ProductDecorator extends Product {
    protected Product decoratedProduct;

    public ProductDecorator(Product decoratedProduct) {
        super(decoratedProduct.getName(), decoratedProduct.getPrice());
        this.decoratedProduct = decoratedProduct;
    }

    public String getName() {
        return decoratedProduct.getName();
    }

    public double getPrice() {
        return decoratedProduct.getPrice();
    }

}
