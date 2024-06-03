import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class takes an encrypted textfile and a keyfile as an input
 * It decrypts the textfile using the keyfile and AES
 * The decrypted file is saved in the same directory as the textfile
 */
public class Decryptor {
    String textfilePath;
    String keyfilePath;

    System.Logger logger = System.getLogger(getClass().getName());

    byte[] decryptedData;

    public Decryptor(String textfilePath, String keyfilePath) {
        this.textfilePath = textfilePath;
        this.keyfilePath = keyfilePath;

        decryptFile();
        saveFile();
    }

    public void decryptFile() {
        try {
            // Read the encrypted file
            byte[] encryptedFileData = Files.readAllBytes(Paths.get(textfilePath));

            // Read the key file
            byte[] keyfileData = Files.readAllBytes(Paths.get(keyfilePath));
            SecretKeySpec secretKey = new SecretKeySpec(keyfileData, "AES");

            // Initialize the cipher for decryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decrypt the data
            decryptedData = cipher.doFinal(encryptedFileData);

        } catch (java.io.FileNotFoundException e) {
            logger.log(System.Logger.Level.ERROR, "File not found: " + e.getMessage());
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Decrypting data failed: " + e.getMessage());
        }
    }

    public void saveFile() {
        try {
            // Get the directory of the encrypted file
            File encryptedFile = new File(textfilePath);
            String directory = encryptedFile.getParent();

            // Create the decrypted file path
            String decryptedFilePath = directory + File.separator + "decrypted_" + encryptedFile.getName();

            // Clear the file if it already exists
            try (FileOutputStream outputStream = new FileOutputStream(decryptedFilePath)) {
                outputStream.write(new byte[0]);
            }

            // Write the decrypted data to the new file
            try (FileOutputStream outputStream = new FileOutputStream(decryptedFilePath)) {
                outputStream.write(decryptedData);
            }

            logger.log(System.Logger.Level.INFO, "Decrypted file saved at: " + decryptedFilePath);
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Saving file failed: " + e.getMessage());
        }
    }
}
