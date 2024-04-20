package src;

import java.io.File;

import java.io.IOException;

import java.nio.file.Files;

import java.util.List;

public abstract class FileParser {

protected String[][] data;

// Initialization of constructor method with exception handling for offensive programming

public FileParser(String filename) throws IOException {

List<String> lines = Files.readAllLines(new File(filename).toPath());

initializeData(lines);

}

// abstract method used in other classes to initialize data

protected abstract void initializeData(List<String> lines);

// Get method to access data

public String[][] getData() {

return data;

}

// Set method to modify data

public void setData(String[][] newData) {

this.data = newData;

            }

}