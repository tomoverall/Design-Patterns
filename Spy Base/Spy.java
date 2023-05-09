public class Spy implements Entity, Observer {
    // Store current encryption scheme and key.
    private EncryptionBehaviour encryptionScheme;
    private int encryptionKey;

    // Store name of Spy and isAlive status.
    private String name;
    private boolean isAlive;

    // Store its current encrypted message and current decrypted message.
    private String encryptedMessage;
    private String decryptedMessage;

    // Store reference to its home base.
    private FieldBase fieldBase;

    public Spy(String name) {
        this.name = name;
        this.isAlive = true;
        this.decryptedMessage = null;
        this.encryptedMessage = null;
    }

    // Encrypt a message, store it, then send it to a recipient.
    @Override public void sendMessage(String message, Entity recipient) {
        this.encryptedMessage = this.encryptionScheme.encrypt(message, this.encryptionKey);
        recipient.receiveMessage(encryptedMessage);
    }

    // Decrypt a received message and store it.
    @Override public void receiveMessage(String message) {
        this.decryptedMessage = encryptionScheme.decrypt(message, this.encryptionKey);
    }

    // Return the alive status of Spy.
    public boolean getAliveStatus() {
        return this.isAlive;
    }

    // Kill the spy by setting its alive status to false, then unregistering it from its FieldBase.
    public void killSpy(Spy spy) {
        this.isAlive = false;
        fieldBase.unregisterObserver(spy);
    }

    // Update encryption scheme and encryption key.
    public void updateEncryption(EncryptionBehaviour encryptionScheme, int encryptionKey) {
        this.encryptionScheme = encryptionScheme;
        this.encryptionKey = encryptionKey;
    }
}