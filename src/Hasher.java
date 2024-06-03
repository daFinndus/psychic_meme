import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Formatter;

/**
 * This class takes a text file as an input
 * It calculates the MD5, SHA-3, and RIPEMD-160 hash values of the file
 * It saves the hash values to separate files
 */
public class Hasher {
    String textfilePath;

    System.Logger logger = System.getLogger(getClass().getName());

    byte[] md5Hash;
    byte[] sha3Hash;
    byte[] ripemd160Hash;


    public Hasher(String textfilePath) {
        this.textfilePath = textfilePath;

        Security.addProvider(new BouncyCastleProvider());

        hashFile();
        saveFile();
    }

    public void hashFile() {
        try {
            // Read the text file
            byte[] fileData = Files.readAllBytes(Paths.get(textfilePath));

            // Calculate MD5 hash
            md5Hash = calculateHash(fileData, "MD5");

            // Calculate SHA-3 hash
            sha3Hash = calculateHash(fileData, "SHA3-256");

            // Calculate RIPEMD-160 hash
            // I had to install the Bouncy Castle library for this
            ripemd160Hash = calculateHash(fileData, "RIPEMD160");


        } catch (java.io.FileNotFoundException e) {
            logger.log(System.Logger.Level.ERROR, "File not found: " + e.getMessage());
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Hashing data failed: " + e.getMessage());
        }
    }

    private byte[] calculateHash(byte[] data, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        return digest.digest(data);
    }

    private String bytesToHex(byte[] bytes) {
        Formatter formatter = new Formatter();
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        String hexString = formatter.toString();
        formatter.close();
        return hexString;
    }

    public void saveFile() {
        try {
            // Get the directory of the text file
            File textFile = new File(textfilePath);
            String directory = textFile.getParent();

            // Create file paths for each hash
            String md5FilePath = directory + File.separator + "md5_" + textFile.getName();
            String sha3FilePath = directory + File.separator + "sha3_" + textFile.getName();
            String ripemd160FilePath = directory + File.separator + "ripemd160_" + textFile.getName();

            // Write the MD5 hash to a file
            try (FileOutputStream outputStream = new FileOutputStream(md5FilePath)) {
                outputStream.write(bytesToHex(md5Hash).getBytes());
            }

            // Write the SHA-3 hash to a file
            try (FileOutputStream outputStream = new FileOutputStream(sha3FilePath)) {
                outputStream.write(bytesToHex(sha3Hash).getBytes());
            }

            // Write the RIPEMD-160 hash to a file
            try (FileOutputStream outputStream = new FileOutputStream(ripemd160FilePath)) {
                outputStream.write(bytesToHex(ripemd160Hash).getBytes());
            }

            logger.log(System.Logger.Level.INFO, "Hash files saved successfully.");
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Saving file failed: " + e.getMessage());
        }
    }
}
