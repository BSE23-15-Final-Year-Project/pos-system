package org.pos;

public class ExpressShippingDecorator extends ProductDecorator {
    private double shippingCost;

    public ExpressShippingDecorator(Product decoratedProduct, double shippingCost) {
        super(decoratedProduct);
        this.shippingCost = shippingCost;
        this.decoratedProduct = decoratedProduct;
    }

    public double getPrice() {
        return decoratedProduct.getPrice() + shippingCost;
    }

    @Override
    public void display() {
        System.out.println("Name: "+decoratedProduct.getName() + ", Description: " + description()+ ", TotalPrice - " + (shippingCost + decoratedProduct.getPrice()));
    }

    @Override
    public String description() {
        return decoratedProduct.description() + " (express shipping)";
    }

}
