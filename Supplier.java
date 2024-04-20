package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Class to parse and store supplier file data in a 2D array
public class Supplier extends FileParser {
    // Class to initialize supplier data parsing
    public Supplier(String supplierFile) throws IOException {
        super(supplierFile); // Calling superclass constructor
    }

    // Method to initialize supplier data from lines 
    @Override
    protected void initializeData(List<String> lines) {
        if (lines.isEmpty()) {  // Checking if lines list is empty
            data = new String[0][0]; // If true, set data to empty array
            return;
        }
    
        // Split first line to get number of attributes per line
        String[] list_of_attributes = lines.get(0).split(",");
        int length_of_attributes = list_of_attributes.length;  // Get the number of attributes
    
        // Initialize data array
        data = new String[lines.size()][length_of_attributes];
    
        // Parsing each line and storing in data array
        for (int i = 0; i < lines.size(); i++) {
            String[] individual_supplier = lines.get(i).split(",");
    
            // Apply offensive programming by ensuring supplier ID is four digits and not negative
            if (individual_supplier[0].matches("\\d{4}") && !individual_supplier[0].startsWith("-")) {
                data[i] = individual_supplier;
            } else {
                // Produce an error to the user
                System.err.println("Invalid supplier ID at line " + (i + 1) + ": " + lines.get(i));
            }

        }
    }
}
