/**
 * This class should take a textfile and a keyfile as an input
 * It should decrypt the textfile using the keyfile and AES
 * The decrypted file should be saved in the same directory as the textfile
 */
public class Decryptor {
    String textfilePath;
    String keyfilePath;

    public Decryptor(String textfilePath, String keyfilePath) {
        this.textfilePath = textfilePath;
        this.keyfilePath = keyfilePath;
    }

    public void decryptFile() {
        // Implement decryption here
        System.out.println(textfilePath + " + " + keyfilePath);
    }

    public void saveFile() {
        // Implement saving here
    }
}
