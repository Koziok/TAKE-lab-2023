/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package web;

import ejb.NewsItem;
import ejb.NewsItemFacadeLocal;
import jakarta.annotation.Resource;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;
import java.util.List;

/**
 *
 * @author Wojtek
 */
@Named(value = "newsBean")
@RequestScoped
public class NewsBean {
    
    @Inject
    private NewsItemFacadeLocal facade;
    @Inject
    private JMSContext context;
    @Resource(lookup = "jms/NewsQueue")
    private jakarta.jms.Queue queue;
    
    private String headingText, bodyText;

    /**
     * Creates a new instance of NewsBean
     */
    public NewsBean() {
    }
    
    public List<NewsItem> getNewsItems() {
        return facade.getAllNewsItems();
    }
    
    public String submitNews() {
        this.sendNewsItem(headingText, bodyText);
        return null;
    }
    
    void sendNewsItem(String heading, String body) {
        try {
//            ObjectMessage message = context.createObjectMessage();
//            NewsItem e = new NewsItem();
//            e.setHeading(heading);
//            e.setBody(body);
//            message.setObject(e);
//            context.createProducer().send(queue, message);
            TextMessage message = context.createTextMessage();
            message.setText(heading + "|" + body);
            context.createProducer().send(queue, message);
            
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * @return the headingText
     */
    public String getHeadingText() {
        return headingText;
    }

    /**
     * @param headingText the headingText to set
     */
    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    /**
     * @return the bodyText
     */
    public String getBodyText() {
        return bodyText;
    }

    /**
     * @param bodyText the bodyText to set
     */
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
    
}
/*
<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:f="http://xmlns.jcp.org/jsf/core">
    <h:head>
        <title>Facelet Title</title>
    </h:head>
    <h:body>
        <h:form>
            <h:outputText value="Heading"/>
            <h:inputText id="headingInputText" value="#{newsBean.headingText}"/>

            <h:outputText value="Body" />
            <h:inputText id="bodyInputText" value="#{newsBean.bodyText}"/>

            <h:commandButton id="submitButton" action="sendNewsItem"/>

            <h:dataTable value="#{newsBean.newsItems}" var="item"
                         border="1">
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Id"/>
                    </f:facet>
                    <h:outputText value="#{item.id}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Heading"/>
                    </f:facet>
                    <h:outputText value="#{item.heading}"/>
                </h:column>
                <h:column>
                    <f:facet name="header">
                        <h:outputText value="Body"/>
                    </f:facet>
                    <h:outputText value="#{item.body}"/>
                </h:column>
            </h:dataTable>

        </h:form>
    </h:body>
</html>


*/
