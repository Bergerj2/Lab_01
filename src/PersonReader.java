import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class PersonReader {
    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String line;
        ArrayList<String> persons = new ArrayList<>();

        try {
            File workingDirectory = new File(System.getProperty("user.dir"));
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                BufferedReader reader = Files.newBufferedReader(file);

                System.out.println(String.format("%-10s %-15s %-15s %-10s %s", "ID#", "Firstname", "Lastname", "Title", "YOB"));
                System.out.println("===============================================================");

                while ((line = reader.readLine()) != null) {
                    persons.add(line);
                    String[] data = line.split(",\\s*");
                    if (data.length == 5) {
                        System.out.println(String.format("%-10s %-15s %-15s %-10s %s", data[0], data[1], data[2], data[3], data[4]));
                    }
                }
                reader.close();

            } else {
                System.out.println("No file selected. Exiting.");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
