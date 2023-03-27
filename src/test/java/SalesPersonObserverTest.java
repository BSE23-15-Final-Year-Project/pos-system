import org.junit.jupiter.api.Test;
import org.pos.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesPersonObserverTest {
    @Test
    public void testSalesPersonObserver() {
        // Create a new salesperson observer
        SalesPerson salesperson = new SalesPerson("Roy");

        // Create a new cart and add the salesperson observer to it
        ProductCatalog cart = ProductCatalog.getInstance();
        cart.addObserver(salesperson);

        // Redirect standard output stream to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Add a product to the cart
        cart.addProduct("ELECTRONICS","iPhone",5000000.0, "Uk used iPhone 12");

        // Restore standard output stream
        System.setOut(System.out);

        // Assert that the output contains the expected strings
        String output = outputStream.toString().trim();
        assertEquals("Salesperson Roy notified: iPhone added to cart.", output);


        // Verify that multiple salespersons are notified
        SalesPerson salesPerson2 = new SalesPerson("Tugume");
        cart.addObserver(salesPerson2);

        // Add a product again to the catalog
        cart.addProduct("ELECTRONICS","iPhone",5000000.0, "Uk used iPhone 12");

        // Restore standard output stream
        System.setOut(System.out);

        // Assert that the output contains the expected strings
        output = outputStream.toString().trim();
        assertEquals("Salesperson Roy notified: iPhone added to cart.\nSalesperson Roy notified: iPhone added to cart.\nSalesperson Tugume notified: iPhone added to cart.", output);
    }

}
