public class Message {
    private final int messageId;
    private final String messageStr;

    public Message(int messageId, String messageStr){
        this.messageId = messageId;
        this.messageStr = messageStr;
    }

    public int getMessageId() {
        return messageId;
    }

    public String getMessageStr(){
        return messageStr;
    }

    public String toString(){
        return String.format("id: %s, message: %s", this.messageId, this.messageStr);
    }
}
