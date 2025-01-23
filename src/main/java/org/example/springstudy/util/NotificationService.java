package org.example.springstudy.util;
//Utility classes like this are reusable and can be injected whenever needed using Dependency Injection.
import org.springframework.stereotype.Component;
//Now this is initialized as a bean for spring-managed container.
@Component
public class NotificationService {
    public void sendNotification(String message) {
        System.out.println("Notification Sent: " + message);
    }
}
