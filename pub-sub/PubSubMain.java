import java.util.Optional;
import java.util.Scanner;

public class PubSubMain {
    static Scanner sc = new Scanner(System.in);
    static PubSubService pubSubService = PubSubService.getPubSubServiceInstance();
    public static void main(String[] args) {


        while(true){
            System.out.println("OPTIONS:- \n" +
                    "1-> add publisher\n" +
                    "2->add topic\n" +
                    "3->add subscriber\n" +
                    "4->add subscriber to topic\n" +
                    "5->get all publishers\n" +
                    "6->get all subscribers\n" +
                    "7->get all topics\n" +
                    "8->publish message via publisher");
            String option = sc.nextLine();
            System.out.println("---------------------------------------------");
            switch (option){
                case "1":
                    pubSubService.addPublisher(getNewPublisher());
                    break;
                case "2":
                    pubSubService.addTopic(getNewTopic());
                    break;
                case "3":
                    pubSubService.addSubscriber(getNewSubscriber());
                    break;
                case "4":
                    System.out.print("Topic Name? ");
                    String topicName = sc.nextLine();
                    System.out.print("Subscriber Name? ");
                    String subscriberName = sc.nextLine();
                    pubSubService.addSubscriberToTopic(subscriberName, topicName);
                    break;
                case "5":
                    pubSubService.getAllPublishers();
                    break;
                case "6":
                    pubSubService.getAllSubscribers();
                    break;
                case "7":
                    pubSubService.getAllTopics();
                    break;
                case "8":
                    System.out.print("Message Id? ");
                    String messageId = sc.nextLine();
                    System.out.print("Message String? ");
                    String messageStr = sc.nextLine();
                    System.out.print("Publisher Name? ");
                    String publisherName = sc.nextLine();
                    pubSubService.publishMessage(publisherName, new Message(Integer.parseInt(messageId), messageStr));
                    break;
                default:
                    System.out.println("\nINVALID OPTION");
            }
            System.out.println("---------------------------------------------");
        }
    }

    static Publisher getNewPublisher(){
        System.out.print("Name of publisher? ");
        String publisherName = sc.nextLine();
        System.out.print("Topic associated with publisher? ");
        String topicName = sc.nextLine();
        Optional<Topic> topicOpt = pubSubService.getTopics().stream().filter(topic -> topicName.equals(topic.getTopicName())).findAny();
        if (topicOpt.isEmpty()){
            System.out.println("Topic with name: "+topicName+" not present, please create one");
            return null;
        }
        return new Publisher(publisherName,topicOpt.get());
    }

    static Subscriber getNewSubscriber(){
        System.out.print("Subscriber Name? ");
        String subscriberName = sc.nextLine();
        return new PrintSubscriber(subscriberName);
    }

    static Topic getNewTopic(){
        System.out.print("Topic Name? ");
        String topicName = sc.nextLine();
        return new Topic(topicName);
    }
}
