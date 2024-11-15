public class PrintSubscriber extends Subscriber {

    public PrintSubscriber(String name){
        super(name);
    }

    @Override
    public void onMessage(Message message){
        System.out.println("Message received on Subscriber: "+ this.name + "\n message: " + message.toString());
    }
}
