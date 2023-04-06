import org.junit.jupiter.api.Test;
import org.pos.*;
import org.pos.exceptions.ProductNotFoundException;
import static org.junit.jupiter.api.Assertions.*;

public class StrategyTest {
    @Test
    void testPayWithCash() throws ProductNotFoundException {
        ProductCatalog catalog = ProductCatalog.getInstance();
        ShoppingCart cart = new ShoppingCart();

        // Add products to the catalog
        catalog.addProduct(ProductType.GROCERIES.toString(),"Tomotoes",3000,"Fresh tomatoes","tmt3");
        catalog.addProduct(ProductType.CLOTHING.toString(), "Trouser", 20000, "Kaki trouser","ktr2");

        // Add the products to the cart
        BarcodeScanner barcodeScanner = new BarcodeScanner();
        barcodeScanner.scan(cart,"tmt3");
        barcodeScanner.scan(cart,"ktr2");

        // Pay with cash
        PaymentStrategy cashPayment = new CashPaymentStrategy();
        PaymentContext paymentContext = new PaymentContext(cashPayment);
        String receipt = paymentContext.processPayment(cart.getTotal());

        assertEquals("Paid shs23000.0 in cash", receipt);
    }

    @Test
    void testPayWithCreditCard() throws ProductNotFoundException {
        ProductCatalog catalog = ProductCatalog.getInstance();
        ShoppingCart cart = new ShoppingCart();

        // Add products to the catalog
        catalog.addProduct(ProductType.ELECTRONICS.toString(),"MTN Mifi",75000,"MTN pocket mifi","5555");
        catalog.addProduct(ProductType.CLOTHING.toString(),"Jacket",30000,"Leather Jacket","6666" );

        // Add the products to the cart
        BarcodeScanner barcodeScanner = new BarcodeScanner();
        barcodeScanner.scan(cart,"5555");
        barcodeScanner.scan(cart,"6666");

        // Pay with credit card
        PaymentStrategy cardPayment = new CreditCardPaymentStrategy("01234567","12/12/2023","1234");
        PaymentContext paymentContext = new PaymentContext(cardPayment);
        String receipt = paymentContext.processPayment(cart.getTotal());

        assertEquals("Paid shs105000.0 with credit card 01234567", receipt);
    }
}
