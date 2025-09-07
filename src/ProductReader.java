import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductReader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Product> products = new ArrayList<>();

        String fileName = SafeInput.getNonZeroLenString("Enter the name of the file to read from");
        Path filePath = Paths.get(fileName);

        if (!Files.exists(filePath)) {
            System.out.println("Error: The file does not exist. Please check the filename and try again.");
            in.close();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Use String.split() to separate the fields by the comma
                String[] fields = line.split(",");
                if (fields.length == 4) {
                    try {
                        String ID = fields[0];
                        String name = fields[1];
                        String description = fields[2];
                        double cost = Double.parseDouble(fields[3]);

                        // Create a Product object and add it to the ArrayList
                        products.add(new Product(ID, name, description, cost));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping malformed record: " + line);
                    }
                } else {
                    System.out.println("Skipping malformed record (incorrect number of fields): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        } finally {
            in.close();
        }

        if (products.isEmpty()) {
            System.out.println("\nNo records found in the file.");
        } else {
            System.out.println("\n--------------------------------------------------------------");
            System.out.printf("%-10s%-25s%-30s%-10s\n", "ID", "Name", "Description", "Cost");
            System.out.println("--------------------------------------------------------------");
            for (Product p : products) {
                System.out.printf("%-10s%-25s%-30s$%.2f\n", p.getID(), p.getName(), p.getDescription(), p.getCost());
            }
            System.out.println("--------------------------------------------------------------");
        }
    }
}
