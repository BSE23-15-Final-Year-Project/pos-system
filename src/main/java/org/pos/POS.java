package org.pos;

import org.pos.exceptions.ProductNotFoundException;

import java.util.Scanner;

public class POS {
    public static void main(String[] args) throws ProductNotFoundException {
        String productName, productType, description, barcode;
        Double productPrice;
        System.out.println("\t\t.................Welcome to Wandegeya Supermarket..............");
        System.out.println("\t\t\t\t................Menu................");
        System.out.println("1. Add Product \n2. Display Product Catalog\n3. Display Specific Product\n4. Add product to cart");
        Scanner scanner = new Scanner(System.in);
        String menuItemSelected = scanner.nextLine();

        // Get the singleton instance of the product catalog
        ProductCatalog catalog = ProductCatalog.getInstance();
        
        // Add a new product to the catalog
        if (menuItemSelected.equals("1")) {
            try {
                System.out.println("Product Type : ");
                productType = scanner.nextLine().toUpperCase();
                if(productType.equals("NULL") || productType.isEmpty()){
                    throw new NullPointerException("Product Type is null");
                }

                System.out.println("Product Name : ");
                productName = scanner.nextLine();

                System.out.println("Product Price : ");
                productPrice = Double.parseDouble(scanner.nextLine().toUpperCase());

                System.out.println("Product Description : ");
                description = scanner.nextLine();

                System.out.println("Barcode: ");
                barcode = scanner.nextLine();

                // add the product
                catalog.addProduct(productType, productName, productPrice, description,barcode);
                // Display the updated catalog
                catalog.displayCatalog();
            }catch (Exception exception){
                throw new IllegalArgumentException("Error - "+exception.getMessage());
            }
        } else if (menuItemSelected.equals("2")) {
            // Display the initial catalog
            catalog.displayCatalog();
        } else if(menuItemSelected.equals("3")){
             // Get a product from the catalog
            System.out.println("Which product do you want to view ?");
            Product product = catalog.getProduct(scanner.nextLine().trim());
            if(product == null){
                System.out.println("Product does not exist!");
                return;
            }
            System.out.println("Name: " + product.getName() + ", Price: " + product.getPrice() + ", Type: "+product.getClass().getSimpleName());
        }else if(menuItemSelected.equals("4")){
            // create shopping cart
            ShoppingCart cart = new ShoppingCart();

            // add observer
            CartObserver observer = new SalesPerson("Hastings Tugume");
            cart.addObserver(observer);

            // add items to cart
            BarcodeScanner barcodeScanner = new BarcodeScanner();
            barcodeScanner.scan(cart,"1234"); // add a smartphone
            barcodeScanner.scan(cart,"5678"); // add bread

            // add gift wrap to phone
            Product phone = cart.getItems().get(0);
            phone = new GiftWrappingDecorator(phone, 200);
            cart.removeItem(cart.getItems().get(0));
            cart.getItems().add(phone);

            // add express shipping to bread
            Product bread = cart.getItems().get(0);
            bread = new ExpressShippingDecorator(bread, 5000);
            cart.removeItem(cart.getItems().get(0));
            cart.getItems().add(bread);

            // display items in cart
            cart.displayItems();

            // display total price
            System.out.println("Total: " + cart.getTotal() + "/=");

            System.out.println("1. Process to checkout\t\t2. Continue Shopping");

            // payment
            String option = scanner.nextLine();
            if(option.equals("1")){
                PaymentStrategy paymentStrategy = null;
                System.out.println("Please choose payment mode");
                System.out.println("1. Cash\t\t2.Credit card");
                option = scanner.nextLine();
                if(option.equals("1")){
                    paymentStrategy =  new CashPaymentStrategy(); // paying cash
                } else if (option.equals("2")) {
                    System.out.println("Enter the card number");
                    String cardNo = scanner.nextLine();
                    System.out.println("Card expiry date");
                    String expiryDate = scanner.nextLine();
                    System.out.println("CVV");
                    String cvv = scanner.nextLine();
                    paymentStrategy = new CreditCardPaymentStrategy(cardNo, expiryDate, cvv); // paying with credit card
                } else {
                    System.out.println("invalid Payment option");
                }
                PaymentContext paymentContext = new PaymentContext(paymentStrategy);
                System.out.println(paymentContext.processPayment(cart.getTotal()));
            }
        }


        scanner.close();
    }
}
