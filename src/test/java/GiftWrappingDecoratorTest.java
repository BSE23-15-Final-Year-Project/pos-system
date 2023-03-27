import org.junit.jupiter.api.Test;
import org.pos.ExpressShippingDecorator;
import org.pos.GiftWrappingDecorator;
import org.pos.Product;
import org.pos.ProductFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GiftWrappingDecoratorTest {
    ProductFactory factory = new ProductFactory();
    @Test
    public void testGiftWrappingDecorator() {
        // Create a new electronics product
        Product product = factory.createProduct("ELECTRONICS","MacBook", 40000.00, "Brand new");

        // Wrap it with a gift wrapping decorator
        Product giftWrappedProduct = new GiftWrappingDecorator(product, 100);

        // Verify that the price and description reflect the added feature
        assertEquals(40200, giftWrappedProduct.getPrice(), 200.0);
        assertEquals("Brand new (gift wrapped)", giftWrappedProduct.description());
    }

    @Test
    public void testExpressShippingDecorator() {
        // Create a new clothing product
        Product product = factory.createProduct("CLOTHING","Shirt", 50, "Classic Shirt");

        // Wrap it with an express shipping decorator
        Product expressShippedProduct = new ExpressShippingDecorator(product, 20);

        // Verify that the price and description reflect the added feature
        assertEquals(70, expressShippedProduct.getPrice(), 0.001);
        assertEquals("Classic Shirt (express shipping)", expressShippedProduct.description());
    }

    @Test
    public void testMultipleDecorators() {
        // Create a new groceries product
        Product product = factory.createProduct("GROCERIES","Milk", 1500, "Fresh milk");

        // Wrap it with multiple decorators
        Product decoratedProduct = new GiftWrappingDecorator(new ExpressShippingDecorator(product, 10), 5);

        // Verify that the price and description reflect all added features
        assertEquals(1515, decoratedProduct.getPrice(), 0.001);
        assertEquals("Fresh milk (express shipping) (gift wrapped)", decoratedProduct.description());
    }

}
