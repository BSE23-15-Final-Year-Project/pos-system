import org.junit.jupiter.api.Test;
import org.pos.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesPersonObserverTest {
    @Test
    public void testSalesPersonObserver() {
        // get the catalog
        ProductCatalog cart = ProductCatalog.getInstance();

        // Create a new salesperson observer and add the observer to the Catalog
        SalesPerson salesperson = new SalesPerson("Roy");// takes in an instance of the catalog and the name of the of SalesPerson
        cart.addObserver(salesperson);

        // Redirect standard output stream to a byte array
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        cart.addProduct("ELECTRONICS","iPhone",5000000.0, "Uk used iPhone 12");// Add a product to the cart
        System.setOut(System.out);// Restore standard output stream
        // Assert that the output contains the expected notification
        String output = outputStream.toString().trim();
        assertEquals("Salesperson Roy notified: iPhone added to cart.", output);


        // Verify that multiple salespersons are notified
        ByteArrayOutputStream outputStream1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream1));
        SalesPerson salesPerson2 = new SalesPerson("Tugume"); // add another observer
        cart.addObserver(salesPerson2);
        cart.addProduct("ELECTRONICS","iPhone",5000000.0, "Uk used iPhone 12");// Add a product again to the catalog
        String output2 = outputStream1.toString().trim(); // get the console output
        System.setOut(System.out);// Restore standard output stream
        // Assert that the output contains the expected notifications to the observers
        assertEquals("Salesperson Roy notified: iPhone added to cart.\nSalesperson Tugume notified: iPhone added to cart.", output2);
    }

}
