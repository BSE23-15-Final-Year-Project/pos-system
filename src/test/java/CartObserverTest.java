import javafx.scene.chart.BarChart;
import org.junit.jupiter.api.Test;
import org.pos.*;
import org.pos.exceptions.ProductNotFoundException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartObserverTest {
    @Test
    public void testSalesPersonObserver() throws ProductNotFoundException {
        // get Shopping Cart
        ShoppingCart shoppingCart = new ShoppingCart();

        // Create a new salesperson observer and add the observer to the Catalog
        SalesPerson salesperson = new SalesPerson("Roy");// takes in an instance of the catalog and the name of the of SalesPerson
        shoppingCart.addObserver(salesperson);

        // Redirect standard output stream to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        BarcodeScanner barcodeScanner = new BarcodeScanner();
        barcodeScanner.scan(shoppingCart,"1234"); // Add a product to the cart

        System.setOut(System.out);// Restore standard output stream

        // Assert that the output contains the expected notification
        String output = outputStream.toString().trim();
        assertEquals("Salesperson Roy notified: Smartphone added to cart.", output);


        // Verify that multiple salespersons are notified
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream1));
        SalesPerson salesPerson2 = new SalesPerson("Tugume"); // add another observer
        shoppingCart.addObserver(salesPerson2);
        barcodeScanner.scan(shoppingCart,"91011"); // Add a product again to the cart
        String output2 = outputStream1.toString().trim(); // get the console output
        System.setOut(System.out);// Restore standard output stream

        // Assert that the output contains the expected notifications to the observers
        assertEquals("Salesperson Roy notified: T-shirt added to cart.\nSalesperson Tugume notified: T-shirt added to cart.", output2);
    }

}
