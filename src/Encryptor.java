/**
 * This class should take a textfile and a keyfile as an input
 * Then it should encrypt the textfile using the keyfile and AES
 * The encrypted file should be saved in the same directory as the textfile
 */
public class Encryptor {
    String textfilePath;
    String keyfilePath;

    public Encryptor(String textfilePath, String keyfilePath) {
        this.textfilePath = textfilePath;
        this.keyfilePath = keyfilePath;
    }

    public void encryptFile() {
        // Implement encryption here
        System.out.println(textfilePath + " + " + keyfilePath);
    }

    public void saveFile() {
        // Implement saving here
    }
}
