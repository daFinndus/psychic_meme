/**
 * This class should take a textfile as an input
 * It should calculate the hash of the textfile using MD5, SHA-3 and RIPEMD-160
 * The hash values for each algorithm should be saved in a textfile in the same directory as the textfile
 */
public class Hasher {
    String textfilePath;

    public Hasher(String textfilePath) {
        this.textfilePath = textfilePath;
    }

    public void hashFile() {
        // Implement hashing here
        System.out.println(textfilePath);
    }

    public void saveFile() {
        // Implement saving here
    }
}
