/**
 * This class should take a textfile as an input
 * The textfile should have the hash values of the original textfile
 * It should calculate the hash values based on the given file
 * Then it should check if the calculated hash values match the original hash values
 * The result should be returned to console
 */
public class Detector {
    String textfilePath;

    public Detector(String textfilePath) {
        this.textfilePath = textfilePath;

        detectFile();
    }

    public void detectFile() {
        // Implement detection here
        System.out.println(textfilePath);
    }
}
