public class NotificationService {
    private static NotificationService notificationService;

    public static synchronized NotificationService getInstance() {
        if (notificationService == null) {
            notificationService = new NotificationService();
        }
        return notificationService;
    }

    public void sendNotification(String message) {
        System.out.println("Notification sent: " + message);
    }
}
