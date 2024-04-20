package src;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SplashScreen extends JFrame {
    public SplashScreen() {
        setTitle("Splash Screen");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create a panel to hold the splash screen content
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.GREEN); 
        // Add an image or text to the splash screen
        JLabel label = new JLabel("Welcome to File Convertor System");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);
        

        // Create a button to display merged inventory file
        JButton displayButton = new JButton("Inventory.txt");
        displayButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Call a method to display merged inventory file
                    displayMergedInventory();
                    dispose(); 
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(SplashScreen.this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(displayButton, BorderLayout.SOUTH);

        // Add the panel to the frame
        add(panel);

        setVisible(true); // Make the frame visible
    }

    private void displayMergedInventory() throws IOException {
        // Create an instance of Inventory and call writeInventoryToFile method
        String supplierFile = "src/SupplierFile.txt";
        String productFile = "src/ProductFile.txt";
        Inventory inventory = new Inventory(supplierFile, productFile);
        String inventoryFileName = "Inventory.txt";
        inventory.writeInventoryToFile(inventoryFileName);

        // Display merged inventory file in a new window or dialog
        JTextArea textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        JFrame displayFrame = new JFrame("Inventory.txt");
        displayFrame.getContentPane().add(scrollPane);
        textArea.read(Files.newBufferedReader(Path.of(inventoryFileName)), null);
        displayFrame.getContentPane().setBackground(Color.BLUE);
        displayFrame.pack();
        displayFrame.setLocationRelativeTo(null); // Center the frame on the screen
        displayFrame.setVisible(true);

    }

}