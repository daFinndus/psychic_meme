import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * This class takes a text file as an input, a hash file as an input and a hash method
 * It detects if the text file has been tampered with
 * It compares the hash value of the text file with the hash value in the hash file
 * It returns true for the given hash method if the hash values match
 */

    /*
    Detect for RIPEMD160 can lead to an error
     */

public class Detector {
    String textfilePath;
    String hashfilePath;
    String hashMethod;


    System.Logger logger = System.getLogger(getClass().getName());


    public Detector(String textfilePath, String hashfilePath, String hashMethod) {
        this.textfilePath = textfilePath;
        this.hashfilePath = hashfilePath;
        this.hashMethod = hashMethod;

        logger.log(System.Logger.Level.INFO, "File was hashed with " + this.hashMethod + ": " + detectFile());
    }

    public boolean detectFile() {
        try {
            // Read the content of the text file
            byte[] textFileBytes = Files.readAllBytes(Paths.get(textfilePath));

            // Calculate hash of the text file based on the specified hash method
            MessageDigest digest = MessageDigest.getInstance(hashMethod);
            byte[] hashBytes = digest.digest(textFileBytes);

            // Read the hash from the hash file
            String storedHash = new String(Files.readAllBytes(Paths.get(hashfilePath)));

            // Convert the calculated hash bytes to a string
            StringBuilder hashBuilder = new StringBuilder();
            for (byte b : hashBytes) {
                hashBuilder.append(String.format("%02x", b));
            }
            String calculatedHash = hashBuilder.toString();

            // Compare the calculated hash with the stored hash
            return calculatedHash.equals(storedHash);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
