public class RandomSymmetricCypher implements EncryptionBehaviour {
    @Override
    public String encrypt(String message, int encryptionKey) {
        return message;
    }

    @Override
    public String decrypt(String message, int encryptionKey) {
        return message;
    }
}
