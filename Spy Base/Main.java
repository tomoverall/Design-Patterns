public class Main {
    public static void main(String[] args) {
        EncryptionBehaviour testingScheme = new CaesarCipher();
        HomeBase testingHomeBase = HomeBase.getInstance(testingScheme, 2);
        FieldBase testingFieldBase = new FieldBase();
        Spy testingSpy = new Spy("John");
        testingHomeBase.registerObserver(testingFieldBase);
        testingFieldBase.registerObserver(testingSpy);
        testingHomeBase.notify(testingScheme, 2);
        testingHomeBase.sendMessage("testing", testingFieldBase);
    }
}