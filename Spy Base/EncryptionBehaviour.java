public interface EncryptionBehaviour {
    public String encrypt(String message, int encryptionKey);
    public String decrypt(String message, int encryptionKey);
}