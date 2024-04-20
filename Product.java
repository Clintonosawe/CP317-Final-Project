package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Class to parse and store product file data in a 2D array
public class Product extends FileParser {
    // Class to initialize product data parsing
    public Product(String productFile) throws IOException {
        super(productFile); // Calling super class constructor
    }
    // Method to initialize product data fron lines 
    @Override
    protected void initializeData(List<String> lines) {
        if (lines.isEmpty()) {  // Check if lines list is empty
            data = new String[0][0]; // Set data to empty array 
            return;
        }
    
        // Initialize data array
        int numAttributes = lines.get(0).split(",").length;
        data = new String[lines.size()][numAttributes];
    
        // Parsing each line and storing in data array
        for (int i = 0; i < lines.size(); i++) {
            String[] individual_product = lines.get(i).split(",");
    
            // Apply Offensive programming by ensuring product ID is not negative and is four digits
            if (individual_product[0].matches("\\d{4}") && !individual_product[0].startsWith("-")) {
                data[i] = individual_product;
            } else {
                // Produce an error to the user
                System.err.println("Invalid product ID at line " + (i + 1) + ": " + lines.get(i));
            }
        }
    }
}