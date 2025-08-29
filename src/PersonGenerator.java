import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PersonGenerator {
    public static void main(String[] args) {
        ArrayList<String> persons = new ArrayList<>();
        boolean done = false;
        String personData;

        do {
            String id = SafeInput.getNonZeroLenString("Enter ID");
            String firstName = SafeInput.getNonZeroLenString("Enter First Name");
            String lastName = SafeInput.ZeroLenString("Enter Last Name");
            String title = SafeInput.ZeroLenString("Enter Title (Mr., Mrs., Ms., Dr., etc.)");
            int yearOfBirth = SafeInput.getRangedInt("Enter Year of Birth", 1000, 2025);

            personData = String.format("%s, %s, %s, %s, %d", id, firstName, lastName, title, yearOfBirth);
            persons.add(personData);

            done = SafeInput.getYNConfirm("Are you done entering data?");
        } while (!done);

        String fileName = SafeInput.getNonZeroLenString("Enter the filename to save to");
        Path filePath = Paths.get(fileName + ".txt");

        try (BufferedWriter writer = Files.newBufferedWriter(filePath)) {
            for (String person : persons) {
                writer.write(person);
                writer.newLine();
            }
            System.out.println("Data successfully written to " + filePath.toAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}