//productCatalog unit test
 import org.junit.jupiter.api.Assertions;
 import org.junit.jupiter.api.Test;
 import org.pos.Product;
 import org.pos.ProductCatalog;
 import org.pos.ProductType;

 public class ProductCatalogTest {
     @Test
     void testAddProduct() {
         // Test adding a new product to the catalog
         ProductCatalog catalog = ProductCatalog.getInstance();
         catalog.addProduct(ProductType.ELECTRONICS.toString(), "Smartwatch", 200.0);
         Assertions.assertEquals(4, catalog.getProductCount());
     }

     @Test
     void testGetProduct() {
         // Test getting a product from the catalog
         ProductCatalog catalog = ProductCatalog.getInstance();
         Product smartphone = catalog.getProduct("Smartphone");
         Assertions.assertEquals("Smartphone", smartphone.getName());
         Assertions.assertEquals(500.0, smartphone.getPrice());

         // Test getting a non-existent product from the catalog
         Assertions.assertNull(catalog.getProduct("Invalid Product"));
     }

     @Test
     void testProductType() {
         // Test getting a product from the catalog
         ProductCatalog catalog = ProductCatalog.getInstance();
         catalog.addProduct(ProductType.CLOTHING.toString(), "Jeans", 50.0);
         ProductType type = catalog.getProductType("Jeans");
         Assertions.assertEquals(ProductType.CLOTHING, type);
     }
 }
