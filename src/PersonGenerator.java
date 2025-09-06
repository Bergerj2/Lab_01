import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();
        boolean continueInput = false;

        do {
            try {
                String ID = SafeInput.getNonZeroLenString("Enter the person's ID (6 digits)");
                String firstName = SafeInput.getNonZeroLenString("Enter the person's first name");
                String lastName = SafeInput.getNonZeroLenString("Enter the person's last name");
                String title = SafeInput.getNonZeroLenString("Enter the person's title (e.g., Mr., Ms., Dr.)");
                int YOB = SafeInput.getRangedInt("Enter the person's year of birth", 1940, 2010);

                people.add(new Person(ID, firstName, lastName, title, YOB));

            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Please try again with a valid year of birth.");
            }
            continueInput = SafeInput.getYNConfirm("Do you want to enter another person?");
        } while (continueInput);

        String fileName = SafeInput.getNonZeroLenString("Enter the name of the file to save to");
        Path filePath = Paths.get(fileName + ".txt");

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (Person p : people) {
                writer.write(p.toCSV());
                writer.newLine();
            }
            System.out.println("Data successfully written to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}