package org.pos;

import java.util.Scanner;

public class POS {
    public static void main(String[] args) {
        String productName, productType, description;
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

                // add the product
                catalog.addProduct(productType, productName, productPrice, description);
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
            System.out.println("Name: " + product.getName() + ", Price: " + product.getPrice());
        }else if(menuItemSelected.equals("4")){
            // create shopping cart
            ShoppingCart cart = new ShoppingCart(catalog);

            // add observer
            CartObserver observer = new SalesPerson("Hastings Tugume");
            cart.addObserver(observer);

            // add items to cart
            cart.addItem("Smartphone");
            cart.addItem("Bread");
            cart.addItem("T-shirt");

            // add gift wrap to phone
            Product phone = cart.getItems().get(0);
            phone = new GiftWrappingDecorator(phone, 200);
            cart.removeItem(cart.getItems().get(0));
            cart.getItems().add(phone);

            // add express shipping to bread
            Product bread = cart.getItems().get(1);
            bread = new ExpressShippingDecorator(bread, 5000);
            cart.removeItem(cart.getItems().get(1));
            cart.getItems().add(bread);

            // display items in cart
            cart.displayItems();

            // display total price
            System.out.println("Total: " + cart.getTotal() + "/=");
        }


        scanner.close();
    }
}
