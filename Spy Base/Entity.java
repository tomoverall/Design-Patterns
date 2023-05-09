public interface Entity {
    public void sendMessage(String message, Entity recipient);
    public void receiveMessage(String message);
}