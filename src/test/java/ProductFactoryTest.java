import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.pos.*;

public class ProductFactoryTest {
     @Test
     void testCreateProduct() {
         // Test creating an Electronics product
         ProductFactory productFactory = new ProductFactory();
         Product electronics = productFactory.createProduct(ProductType.ELECTRONICS.toString(), "Laptop", 1000.0);
         Assertions.assertEquals("Laptop", electronics.getName());
         Assertions.assertEquals(1000.0, electronics.getPrice());
         Assertions.assertTrue(electronics instanceof Electronics);

         // Test creating a Groceries product
         Product groceries = productFactory.createProduct(ProductType.GROCERIES.toString(), "Banana", 1.0);
         Assertions.assertEquals("Banana", groceries.getName());
         Assertions.assertEquals(1.0, groceries.getPrice());
         Assertions.assertTrue(groceries instanceof Groceries);

         // Test creating a Clothing product
         Product clothing = productFactory.createProduct(ProductType.CLOTHING.toString(), "Jeans", 50.0);
         Assertions.assertEquals("Jeans", clothing.getName());
         Assertions.assertEquals(50.0, clothing.getPrice());
         Assertions.assertTrue(clothing instanceof Clothing);

         // Test creating an invalid product type
         Assertions.assertThrows(IllegalArgumentException.class, () -> {
             productFactory.createProduct("Invalid Type", "Invalid Product", 0.0);
         });

         // Test null product type
         Assertions.assertThrows(NullPointerException.class, () -> {
             productFactory.createProduct(null, "Null Product Type",0.0);
         });
     }
 }
