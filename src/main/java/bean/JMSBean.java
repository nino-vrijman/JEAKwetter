package bean;

import service.KweetService;
import service.UserService;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Nino Vrijman on 3-5-2017.
 */
@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/queue/incoming"),
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "incoming"),
                @ActivationConfigProperty(propertyName = "resourceAdapter", propertyValue = "activemq-rar-5.14.5")
        }
)
public class JMSBean implements MessageListener {
    @Inject
    KweetService kweetService;
    @Inject
    UserService userService;

    public void onMessage(Message message) {
        try {
            String messageBody = ((TextMessage)message).getText();
            String content = messageBody.split(";")[0];
            String username = messageBody.split(";")[1];

            kweetService.create(content, userService.getUserByUsername(username));
            System.out.println("Kweet created");
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}