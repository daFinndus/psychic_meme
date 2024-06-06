import java.util.logging.Logger;

public class Main {

    Logger logger = Logger.getLogger(getClass().getName());

    static Encryptor encryptor;
    static Decryptor decryptor;
    static Hasher hasher;
    static Detector detector;

    public static void main(String[] args) {
        if (args[0].equalsIgnoreCase("encrypt")) {
            encryptor = new Encryptor(args[1], args[2]);
        } else if (args[0].equalsIgnoreCase("decrypt")) {
            decryptor = new Decryptor(args[1], args[2]);
        } else if (args[0].equalsIgnoreCase("hash")) {
            if (args.length == 2) {
                hasher = new Hasher(args[1]);
            } else {
                hasher = new Hasher(args[1], args[2]);
            }
        } else if (args[0].equalsIgnoreCase("detect")) {
            detector = new Detector(args[1], args[2], args[3]);
        }
    }
}