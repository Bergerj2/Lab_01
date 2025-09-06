import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonReader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Person> people = new ArrayList<>();

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
                // Use String.split() to separate the fields by the comma and space
                String[] fields = line.split(", ");
                if (fields.length == 5) {
                    try {
                        String ID = fields[0];
                        String firstName = fields[1];
                        String lastName = fields[2];
                        String title = fields[3];
                        int YOB = Integer.parseInt(fields[4]);

                        // Create a Person object and add it to the ArrayList
                        people.add(new Person(ID, firstName, lastName, title, YOB));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Skipping malformed record: " + line);
                    }
                } else {
                    System.out.println("Skipping malformed record (incorrect number of fields): " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }

        in.close();

        // Display the data in a table from the ArrayList
        if (people.isEmpty()) {
            System.out.println("\nNo records found in the file.");
        } else {
            System.out.println("\n--------------------------------------------------------------");
            System.out.printf("%-10s%-15s%-15s%-10s%-5s\n", "ID", "First Name", "Last Name", "Title", "YOB");
            System.out.println("--------------------------------------------------------------");
            for (Person p : people) {
                System.out.printf("%-10s%-15s%-15s%-10s%-5d\n", p.getID(), p.getFirstName(), p.getLastName(), p.getTitle(), p.getYOB());
            }
            System.out.println("--------------------------------------------------------------");
        }
    }
}
