package com.startup.enginizer.services;

import com.startup.enginizer.model.dto.ChatMessage;
import com.startup.enginizer.model.entities.Message;
import com.startup.enginizer.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Dragos on 10/7/2015.
 */
@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<ChatMessage> getChatMessages() {

        List<Message> messages = messageRepository.getMessages();
        List<ChatMessage> chatMessages = new ArrayList<>();
        for(Message message : messages){
            ChatMessage chatMessage = toChatMessage(message);
            chatMessages.add(chatMessage);
        }

        return chatMessages;
    }

    private ChatMessage toChatMessage(Message message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setContent(message.getContent());
        chatMessage.setFrom(message.getSender());
        chatMessage.setTimestamp(new Date(message.getTimestamp().getTime()));
        chatMessage.setLocation(message.getLocation());
        chatMessage.setFileName(message.getFileName());
        return chatMessage;
    }


    public void addMessage(final ChatMessage message){
        messageRepository.convertAndAddChatMessageAsMessage(message);
    }
}
