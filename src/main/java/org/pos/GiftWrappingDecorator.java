package org.pos;

// Concrete decorator classes
public class GiftWrappingDecorator extends ProductDecorator {
    private double wrappingCost;

    public GiftWrappingDecorator(Product decoratedProduct, double wrappingCost) {
        super(decoratedProduct);
        this.wrappingCost = wrappingCost;
        this.decoratedProduct = decoratedProduct;
    }

    public double getPrice() {
        return decoratedProduct.getPrice() + wrappingCost;
    }

    @Override
    public String description(){
        return decoratedProduct.description() + " (gift wrapped)";
    }

    @Override
    public void display() {
        System.out.println("Name: " + decoratedProduct.getName()+", Description: " +description()+ ", Total Price - " + (wrappingCost + decoratedProduct.getPrice()));
    }

}