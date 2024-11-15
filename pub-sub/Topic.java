import java.util.*;

public class Topic {
    private final String topicName;
    private Set<Subscriber> subscribers;

    public Topic(String topicName){
        this.topicName = topicName;
        this.subscribers = new HashSet<>();
    }

    public String getTopicName(){
        return this.topicName;
    }

    public Set<Subscriber> getSubscribers(){
        return this.subscribers;
    }

    public void addSubscriber(Subscriber subscriber){
        subscribers.add(subscriber);
        System.out.println("Subscriber: " + subscriber.name + " added successfully");
    }

    public void removeSubscriber(String subscriberName){
        Optional<Subscriber> subscriber = subscribers.stream().findAny();
        if(subscriber.isEmpty()) {
            System.out.println("Subscriber does not exist with name: " + subscriberName);
            return;
        }
        subscribers.remove(subscriber.get());
        System.out.println("Subscriber removed successfully");
    }
}
