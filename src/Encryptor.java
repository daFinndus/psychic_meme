import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class should take a textfile and a keyfile as an input
 * Then it should encrypt the textfile using the keyfile and AES
 * The encrypted file should be saved in the same directory as the textfile
 */
public class Encryptor {
    String textfilePath;
    String keyfilePath;

    System.Logger logger = System.getLogger(getClass().getName());

    byte[] encryptedData;

    public Encryptor(String textfilePath, String keyfilePath) {
        this.textfilePath = textfilePath;
        this.keyfilePath = keyfilePath;

        encryptFile();
        saveFile();
    }

    public void encryptFile() {
        try {
            // Read the text file
            byte[] textfileData = Files.readAllBytes(Paths.get(textfilePath));

            // Read the key file
            byte[] keyfileData = Files.readAllBytes(Paths.get(keyfilePath));
            SecretKeySpec secretKey = new SecretKeySpec(keyfileData, "AES");

            // Initialize the cipher for encryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            // Encrypt the data
            encryptedData = cipher.doFinal(textfileData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveFile() {
        try {
            // Get the directory of the text file
            File textFile = new File(textfilePath);
            String directory = textFile.getParent();

            // Create the encrypted file path
            String encryptedFilePath = directory + File.separator + "encrypted_" + textFile.getName();

            // Write the encrypted data to the new file
            try (FileOutputStream outputStream = new FileOutputStream(encryptedFilePath)) {
                outputStream.write(encryptedData);
            }

            logger.log(System.Logger.Level.INFO, "Encrypted file saved at: " + encryptedFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}