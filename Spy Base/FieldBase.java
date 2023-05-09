import java.util.ArrayList;

public class FieldBase implements Entity, Subject, Observer {
    // Lists for alive spies and dead spies.
    private ArrayList aliveSpies;
    private ArrayList deadSpies;

    // Store current encryption scheme and key.
    private EncryptionBehaviour encryptionScheme;
    private int encryptionKey;

    // Store its current encrypted message and current decrypted message.
    private String encryptedMessage;
    private String decryptedMessage;

    // Store reference to its home base.
    private HomeBase homeBase;

    public FieldBase() {
        this.encryptedMessage = null;
        this.decryptedMessage = null;
        this.aliveSpies = new ArrayList();
        this.deadSpies = new ArrayList();
    }

    // Register an Observer (Spy) to the FieldBase.
    @Override public void registerObserver(Observer spy) {
        // Only register a spy if it is not dead.
        if (!deadSpies.contains(spy)) {
            aliveSpies.add(spy);
        }
    }

    // Unregister an Observer (Spy) from FieldBase.
    @Override public void unregisterObserver(Observer spy) {
        aliveSpies.remove(spy);
        deadSpies.add(spy);
    }

    // Notify its Observers (Spies) with an updated encryption scheme and key.
    @Override public void notify(EncryptionBehaviour encryptionScheme, int encryptionKey) {
        for (int i = 0; i < aliveSpies.size(); i++) {
            Observer s = (Observer) aliveSpies.get(i);
            s.updateEncryption(encryptionScheme, encryptionKey);
        }
    }

    // Encrypt a message, store it, then send it to a recipient.
    @Override public void sendMessage(String message, Entity recipient) {
        this.encryptedMessage = this.encryptionScheme.encrypt(message, this.encryptionKey);
        for (int i = 0; i < aliveSpies.size(); i++) {
            Observer s = (Observer) aliveSpies.get(i);
        }
    }

    // Decrypt a received message and store it.
    @Override public void receiveMessage(String message) {
        this.decryptedMessage = encryptionScheme.decrypt(message, this.encryptionKey);
    }

    // Update encryption scheme and encryption key of FieldBase, then notify all of its Observers (spies) of the update.
    public void updateEncryption(EncryptionBehaviour encryptionScheme, int encryptionKey){
        this.encryptionScheme = encryptionScheme;
        this.encryptionKey = encryptionKey;
        this.notify(this.encryptionScheme, this.encryptionKey);
    }

    // Once a FieldBase goes dark, it will unregister from its HomeBase.
    public void goDark() {
        this.homeBase.unregisterObserver(this);
    }
}