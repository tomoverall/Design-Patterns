public class EncryptionDecorator implements EncryptionBehaviour {
    // Take in two encryption schemes.
    private EncryptionBehaviour behaviour;
    private EncryptionBehaviour otherBehaviour;

    public EncryptionDecorator(EncryptionBehaviour behaviour, EncryptionBehaviour otherBehaviour) {
        this.behaviour = behaviour;
        this.otherBehaviour = otherBehaviour;
    }

    // Encrypt a message by composing the encryption methods of both schemes.
    @Override
    public String encrypt(String message, int encryptionKey) {
        String result = otherBehaviour.encrypt(behaviour.encrypt(message, encryptionKey), encryptionKey);
        return result;
    }

    // Decrypt a message by composing the decryption methods of both schemes.
    @Override
    public String decrypt(String message, int encryptionKey) {
        String result = otherBehaviour.decrypt(behaviour.decrypt(message, encryptionKey), encryptionKey);
        return result;
    }
}