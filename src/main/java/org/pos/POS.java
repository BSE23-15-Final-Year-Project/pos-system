package org.pos;

import java.util.Scanner;

public class POS {
    public static void main(String[] args) {
        String productName, productType;
        Double productPrice;
        System.out.println("\t\t.................Welcome to Wandegeya Supermarket..............");
        System.out.println("\t\t\t\t................Menu................");
        System.out.println("1. Add Product \n2. Display Product Catalog\n3. Display Specific Product");
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

                // add the product
                catalog.addProduct(productType, productName, productPrice);
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
        }
        scanner.close();
    }
}
