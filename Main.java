package src;

import java.io.IOException;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Provide the file paths for the supplier and product files
            String supplierFile = "src/SupplierFile.txt";
            String productFile = "src/ProductFile.txt";

            // Create an instance of Inventory and pass the file paths to its constructor
            Inventory inventory = new Inventory(supplierFile, productFile);

            // Define the file name where the inventory will be written
            String inventoryFileName = "src/Inventory.txt";

            // Write the inventory to the file
            inventory.writeInventoryToFile(inventoryFileName);

            // Inform the user that the process is complete
            System.out.println("Inventory created successfully. Check " + inventoryFileName);

            // Run the SplashScreen
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new SplashScreen();
                }
            });
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}