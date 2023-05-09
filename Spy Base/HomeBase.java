import java.util.ArrayList;

public class HomeBase implements Entity, Subject {
    // List of field bases.
    private ArrayList fieldBases;

    // Store current encryption scheme and key.
    private EncryptionBehaviour encryptionScheme;
    private int encryptionKey;

    // Store its current encrypted message and current decrypted message.
    private String encryptedMessage;
    private String decryptedMessage;


    // Unique HomeBase instance.
    private static HomeBase instance;

    private HomeBase(EncryptionBehaviour encryptionScheme, int encryptionKey) {
        this.encryptedMessage = null;
        this.decryptedMessage = null;
        this.encryptionScheme = encryptionScheme;
        this.encryptionKey = encryptionKey;
        this.fieldBases = new ArrayList();
    }

    // Implement getInstance in accordance with Singleton pattern. Only one instance of HomeBase can exist.
    public static HomeBase getInstance(EncryptionBehaviour encryptionScheme, int encryptionKey) {
        if (instance == null) {
            instance = new HomeBase(encryptionScheme, encryptionKey);
        }
        return instance;
    }

    // Register an Observer (FieldBase) to the HomeBase.
    @Override public void registerObserver(Observer fieldBase) {
        fieldBases.add(fieldBase);
        fieldBase.updateEncryption(encryptionScheme, encryptionKey);
    }

    // Unregister an Observer (FieldBase) from HomeBase.
    @Override public void unregisterObserver(Observer fieldBase) {
        fieldBases.remove(fieldBase);
    }

    // Notify its Observers (FieldBases) with an updated encryption scheme and key.
    @Override public void notify(EncryptionBehaviour encryptionScheme, int encryptionKey) {
        for (int i = 0; i < fieldBases.size(); i++) {
            Observer fb = (Observer) fieldBases.get(i);
            fb.updateEncryption(encryptionScheme,encryptionKey);
        }
    }

    // Encrypt a message, store it, then send it to a recipient.
    @Override
    public void sendMessage(String message, Entity recipient) {
        this.encryptedMessage = this.encryptionScheme.encrypt(message, this.encryptionKey);
        recipient.receiveMessage(encryptedMessage);
    }

    // Decrypt a received message and store it.
    @Override public void receiveMessage(String message) {
        this.decryptedMessage = encryptionScheme.decrypt(message, this.encryptionKey);
    }

    // Set the encryption scheme to a given encryption scheme.
    public void setEncryptionScheme(EncryptionBehaviour encryptionScheme) {
        this.encryptionScheme = encryptionScheme;
        this.notify(this.encryptionScheme, this.encryptionKey);
    }

    // Set the encryption key to a given encryption key.
    public void setEncryptionKey(int encryptionKey) {
        this.encryptionKey = encryptionKey;
        this.notify(this.encryptionScheme, this.encryptionKey);
    }
}