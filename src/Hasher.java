import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.util.Formatter;
import java.util.List;

/**
 * This class takes a text file as an input
 * It calculates the MD5, SHA-3, and RIPEMD-160 hash values of the file
 * It saves the hash values to separate files
 */
public class Hasher {
    String textfilePath;
    String hashMethod;

    List<String> validHashMethods = List.of("MD5", "SHA3-256", "RIPEMD160");

    System.Logger logger = System.getLogger(getClass().getName());

    // These cases will be used when the user doesn't specify a hash method
    byte[] md5Hash;
    byte[] sha3Hash;
    byte[] ripemd160Hash;

    // This case will be used when the user specifies a hash method
    byte[] hash;


    public Hasher(String textfilePath, String hashMethod) {
        this.textfilePath = textfilePath;
        this.hashMethod = hashMethod;

        Security.addProvider(new BouncyCastleProvider());

        hashFile();
        saveFile();
    }

    public Hasher(String textfilePath) {
        this.textfilePath = textfilePath;

        Security.addProvider(new BouncyCastleProvider());

        hashFile();
        saveFile();
    }

    public void hashFile() {
        byte[] data = null;

        try {
            data = Files.readAllBytes(Paths.get(textfilePath));
        } catch (java.io.FileNotFoundException e) {
            logger.log(System.Logger.Level.ERROR, "File not found: " + e.getMessage());
        } catch (Exception e) {
            logger.log(System.Logger.Level.ERROR, "Reading bytes failed: " + e.getMessage());
        }

        if (hashMethod == null) {
            try {
                // Calculate MD5 hash
                md5Hash = calculateHash(data, "MD5");

                // Calculate SHA-3 hash
                sha3Hash = calculateHash(data, "SHA3-256");

                // Calculate RIPEMD-160 hash
                // I had to install the Bouncy Castle library for this
                ripemd160Hash = calculateHash(data, "RIPEMD160");

            } catch (Exception e) {
                logger.log(System.Logger.Level.ERROR, "Hashing data failed: " + e.getMessage());
            }
        } else {
            try {
                if (validHashMethods.contains(hashMethod)) {
                    hash = calculateHash(data, hashMethod);
                } else {
                    logger.log(System.Logger.Level.ERROR, "Invalid hash method: " + hashMethod);
                }
            } catch (Exception e) {
                logger.log(System.Logger.Level.ERROR, "Hashing data failed: " + e.getMessage());
            }
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
        // Get the directory of the text file
        File textFile = new File(textfilePath);
        String directory = textFile.getParent();

        if (hashMethod == null) {
            try {
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
        } else {
            try {
                // Create the file path for the hash
                String hashFilePath = directory + File.separator + hashMethod + "_" + textFile.getName();

                // Write the hash to a file
                try (FileOutputStream outputStream = new FileOutputStream(hashFilePath)) {
                    outputStream.write(bytesToHex(hash).getBytes());
                }

                logger.log(System.Logger.Level.INFO, "Hash file saved successfully.");
            } catch (Exception e) {
                logger.log(System.Logger.Level.ERROR, "Saving file failed: " + e.getMessage());
            }
        }
    }
}
