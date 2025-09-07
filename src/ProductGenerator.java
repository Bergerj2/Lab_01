import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();
        boolean done = false;

        do {
            String ID = SafeInput.getNonZeroLenString("Enter the Product ID");
            String name = SafeInput.getNonZeroLenString("Enter the Product Name");
            String description = SafeInput.getNonZeroLenString("Enter the Product Description");
            double cost = SafeInput.getRangedDouble(in, "Enter the Product Cost", 0.0, 9999.99);

            try {
                // Instantiate a Product object and add it to the ArrayList
                products.add(new Product(ID, name, description, cost));
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

            done = SafeInput.getYNConfirm("Are you done entering product data?");
        } while (!done);

        String fileName = SafeInput.getNonZeroLenString("Enter the filename to save to (e.g., products.txt)");
        Path filePath = Paths.get(fileName);

        // Write the data to the file
        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Product product : products) {
                // Use the toCSV() method to get the formatted string
                writer.write(product.toCSV());
                writer.newLine();
            }
            System.out.println("\nProduct data successfully written to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while writing the file: " + e.getMessage());
        } finally {
            in.close();
        }
    }
}