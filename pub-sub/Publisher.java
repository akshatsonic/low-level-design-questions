import java.util.Set;

public class Publisher {
    final String name;
    final Topic topic;

    public Publisher(String name, Topic topic){
        this.name = name;
        this.topic = topic;
    }

    public String getName(){
        return this.name;
    }

    public Topic getTopic(){
        return this.topic;
    }

    void publish(Message message){
        Set<Subscriber> subscribers = this.topic.getSubscribers();
        System.out.println("Publishing messge to topic: "+topic.getTopicName() + " with message: "+message.toString());
        subscribers.forEach(
                subscriber -> subscriber.onMessage(message)
        );
    }
}
