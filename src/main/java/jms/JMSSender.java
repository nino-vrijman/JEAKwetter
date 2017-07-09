package jms;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * Created by Nino Vrijman on 3-5-2017.
 */
public class JMSSender {
    private static final String JNDI_CONNECTION_FACTORY = "jms/ConnectionFactory";
    private static final String QUEUE = "incoming";

    private Connection connection;
    private Session session;
    private Destination destination;

    public JMSSender(){
        init();
    }

    private void init(){
        try {
            Context initialContext = new InitialContext();
            ConnectionFactory connectionFactory = (ConnectionFactory) initialContext.lookup(JNDI_CONNECTION_FACTORY);
            connection = connectionFactory.createConnection();
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            destination = session.createQueue(QUEUE);
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message) {
        MessageProducer messageProducer;
        TextMessage textMessage;
        try {
            messageProducer = session.createProducer(destination);
            textMessage = session.createTextMessage();
            textMessage.setText(message);

            System.out.println("Sending the following message: " + textMessage.getText());

            messageProducer.send(textMessage);
            messageProducer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
