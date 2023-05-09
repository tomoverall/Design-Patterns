public interface Subject {
    public void registerObserver(Observer o);
    public void unregisterObserver(Observer o);
    public void notify(EncryptionBehaviour encryptionScheme, int encryptionKey);
}