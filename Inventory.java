package src;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Inventory {
    private Product product; // Instance variable to hold product data
    private Supplier supplier; // Instance variable to hold supplier data
    private String[][] inventory; // Instance variable to hold merged inventory data

    // Constructor to initialize Inventory with supplier and product files
    public Inventory(String supplierFile, String productFile) throws IOException {
        supplier = new Supplier(supplierFile); // Initialize Supplier object
        product = new Product(productFile); // Initialize Product object
        mergeData(); // Merge supplier and product data
    }

    // Method to merge supplier and product data into inventory
    private void mergeData() {
        String[][] supplierData = supplier.getData(); // Get supplier data
        String[][] productData = product.getData(); // Get product data

        // Initialize inventory array with size of supplier data and 14 columns
        inventory = new String[supplierData.length][14];

        // Fill inventory array with null values
        for (int k = 0; k < inventory.length; k++) {
            Arrays.fill(inventory[k], null);
        }

        // Merge product and supplier data based on matching IDs
        for (int j = 0; j < productData.length - 1; j++) {
            for (int m = 0; m < supplierData.length - 1; m++) {
                // Check if product ID matches supplier ID
                if (productData[j][6].trim().equals(supplierData[m][0].trim())) {
                    // Create merged array with product and supplier data
                    String[] merged = new String[12];
                    System.arraycopy(productData[j], 0, merged, 0, 7);
                    System.arraycopy(supplierData[m], 0, merged, 7, 5);
                    inventory[j] = merged; // Assign merged array to inventory
                    break;
                }
            }
        }

        // Sort inventory array based on supplier name (6th element)
        Comparator<String[]> comparator = Comparator.comparing(arr -> arr[0] != null ? arr[0].trim() : "");
        Arrays.sort(inventory, comparator);
    }

    // Method to write inventory data to file
    public void writeInventoryToFile(String fileName) {
        try {
            FileWriter myWriter = new FileWriter(fileName); // Create FileWriter object
            myWriter.write("Product ID, Product Name, Quantity, Price, Status, Supplier Name\n"); // Write header

            // Write each item in inventory to file
            for (int index = 1; index < inventory.length; index++) {
                String[] item = inventory[index]; // Get current item
                if (item != null) { // Check if item is not null
                    // Write item data to file
                    myWriter.write(item[0] + "," + item[1] + "," + item[4] + "," + item[3] + "," + item[5] + "," + item[8] + "\n");
                }
            }
            myWriter.close(); // Close FileWriter
        } catch (IOException e) {
            // Handle IOException
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}