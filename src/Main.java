import java.util.logging.Logger;

public class Main {

    Logger logger = Logger.getLogger(getClass().getName());

    static Encryptor encryptor;
    static Decryptor decryptor;
    static Hasher hasher;
    static Detector detector;

    public static void main(String[] args) {
        if (args[0].toLowerCase().equals("encrypt")) {
            encryptor = new Encryptor(args[1], args[2]);
            encryptor.encryptFile();
        } else if (args[0].toLowerCase().equals("decrypt")) {
            decryptor = new Decryptor(args[1], args[2]);
            decryptor.decryptFile();
        } else if (args[0].toLowerCase().equals("hash")) {
            hasher = new Hasher(args[1]);
            hasher.hashFile();
        } else if (args[0].toLowerCase().equals("detect")) {
            detector = new Detector(args[1]);
            detector.detectFile();
        }
    }
}