/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/MessageDrivenBean.java to edit this template
 */
package ejb;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 *
 * @author Wojtek
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/NewsQueue"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "jakarta.jms.Queue")
})
public class NewsMDB implements MessageListener {

    @PersistenceContext
    private EntityManager em;

    public NewsMDB() {
    }

    @Override
    public void onMessage(Message message) {
        ObjectMessage msg = null;
        TextMessage tMsg = null;
        try {
            if (message instanceof ObjectMessage) {
                msg = (ObjectMessage) message;
                NewsItem e = (NewsItem) msg.getObject();
                em.persist(e);
            }
            if (message instanceof TextMessage){
                tMsg = (TextMessage) message;
                String[] elements = tMsg.getText().split("\\|");
                NewsItem e = new NewsItem();
                e.setHeading(elements[0]);
                e.setBody(elements[1]);
                em.persist(e);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
