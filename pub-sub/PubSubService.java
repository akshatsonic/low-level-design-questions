import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PubSubService {
    private static PubSubService pubSubServiceInstance;
    private static List<Publisher> publishers;
    private static List<Subscriber> subscribers;
    private static List<Topic> topics;

    private PubSubService() {
        publishers = new ArrayList<>();
        subscribers = new ArrayList<>();
        topics = new ArrayList<>();
    }

    public List<Topic> getTopics(){
        return topics;
    }

    public void publishMessage(String publisherName, Message message) {
        Optional<Publisher> publisherOpt = publishers.stream().filter(publisher -> publisherName.equals(publisher.getName())).findFirst();
        if (publisherOpt.isEmpty()) {
            System.out.println("Publisher with name: " + publisherName + " does not exist");
            return;
        }
        publisherOpt.get().publish(message);
        System.out.println("Publisher: " + publisherName + " published the message : " + message.toString());
    }

    public synchronized void getAllSubscribers() {
        subscribers.forEach(subscriber -> {
            System.out.print(subscriber.name + " ");
        });
    }

    public synchronized void getAllTopics() {
        topics.forEach(
                topic -> {
                    System.out.println(topic.getTopicName() + " ");
                }
        );
    }

    public synchronized void getAllPublishers() {
        publishers.forEach(
                publisher -> {
                    System.out.println(publisher.getName() + " ");
                }
        );
    }

    public synchronized static PubSubService getPubSubServiceInstance() {
        if (pubSubServiceInstance == null)
            return new PubSubService();
        return pubSubServiceInstance;
    }

    public void addPublisher(Publisher publisher) {
        publishers.add(publisher);
    }

    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void addTopic(Topic topic) {
        topics.add(topic);
    }

    public void addSubscriberToTopic(String subscriberName, String topicName) {
        Optional<Topic> topicOpt = topics.stream().filter(topic -> topicName.equals(topic.getTopicName())).findFirst();
        if (topicOpt.isEmpty()) {
            System.out.println("Topic not present with name: " + topicName);
            return;
        }

        Optional<Subscriber> subscriberOpt = subscribers.stream().filter(subscriber -> subscriberName.equals(subscriber.name)).findFirst();
        if (subscriberOpt.isEmpty()) {
            System.out.println("Subscriber not present with name: " + subscriberName);
            return;
        }

        topicOpt.get().addSubscriber(subscriberOpt.get());
        System.out.println("Subscriber: " + subscriberName + " added to topic: " + topicName);
    }

}
