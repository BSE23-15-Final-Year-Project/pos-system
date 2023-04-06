import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pos.BarcodeScanner;
import org.pos.CartObserver;
import org.pos.SalesPerson;
import org.pos.ShoppingCart;
import org.pos.exceptions.ProductNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class BarcodeScannerTest {
    @Test
    void testProductScan() throws ProductNotFoundException {
        // add cart observer
        ShoppingCart cart = new ShoppingCart();
        CartObserver salesperson = new SalesPerson("Hastings");
        cart.addObserver(salesperson);

        // Redirect standard output stream to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Add the products to the cart
        BarcodeScanner barcodeScanner = new BarcodeScanner();
        barcodeScanner.scan(cart,"1234");

        String output = outputStream.toString().trim();

        System.setOut(System.out);// Restore standard output stream
        Assertions.assertEquals("Salesperson Hastings notified: Smartphone added to cart.",output);

        // scan a wrong product that is not available in the catalog
        Assertions.assertThrows(ProductNotFoundException.class, () -> {
            barcodeScanner.scan(cart,"tmt3");
        });
    }
}
