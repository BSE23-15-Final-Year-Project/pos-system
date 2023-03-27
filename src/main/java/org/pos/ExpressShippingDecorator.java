package org.pos;

public class ExpressShippingDecorator extends ProductDecorator {
    private double shippingCost;

    public ExpressShippingDecorator(Product decoratedProduct, double shippingCost) {
        super(decoratedProduct);
        this.shippingCost = shippingCost;
    }

    public double getPrice() {
        return decoratedProduct.getPrice() + shippingCost;
    }

    @Override
    public void display() {
        System.out.println("Express Shipping - " + shippingCost);
    }

    @Override
    public String description() {
        return decoratedProduct.description() + " (express shipping)";
    }

}
