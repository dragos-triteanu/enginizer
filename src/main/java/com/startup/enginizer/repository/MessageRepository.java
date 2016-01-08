package com.startup.enginizer.repository;

import com.startup.enginizer.model.dto.ChatMessage;
import com.startup.enginizer.model.entities.Message;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Dragos on 10/7/2015.
 */
@Repository
@Transactional(readOnly = false)
public class MessageRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<Message> getMessages() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class);
        criteria.addOrder(org.hibernate.criterion.Order.asc("timestamp"));
        List<Message> messages = criteria.list();
        return messages;
    }

    public void convertAndAddChatMessageAsMessage(final ChatMessage message){
        Message persistedMessage = fromChatMessage(message);
        sessionFactory.getCurrentSession().persist(persistedMessage);
    }

    private Message fromChatMessage(final ChatMessage chatMessage){
        Message message = new Message();
        message.setTimestamp(new Timestamp(chatMessage.getTimestamp().getTime()));
        message.setContent(chatMessage.getContent());
        message.setSender(chatMessage.getFrom());
        message.setFileName(chatMessage.getFileName());
        message.setLocation(chatMessage.getLocation());
        return message;
    }


}
