import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProductWriter {
    public static void main(String[] args) {
        ArrayList<String> products = new ArrayList<>();
        boolean done = false;
        String productData;

        do {
            String id = SafeInput.getNonZeroLenString("Enter Product ID");
            String name = SafeInput.getNonZeroLenString("Enter Product Name");
            String description = SafeInput.ZeroLenString("Enter Product Description");
            double cost = SafeInput.getDouble("Enter Product Cost");

            productData = String.format("%s, %s, %s, %.2f", id, name, description, cost);
            products.add(productData);

            done = SafeInput.getYNConfirm("Are you done entering data?");
        } while (!done);

        String fileName = SafeInput.getNonZeroLenString("Enter the filename to save to");
        Path filePath = Paths.get(fileName + ".txt");

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (String product : products) {
                writer.write(product);
                writer.newLine();
            }
            System.out.println("Data successfully written to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
