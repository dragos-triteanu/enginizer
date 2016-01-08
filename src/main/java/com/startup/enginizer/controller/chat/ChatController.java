package com.startup.enginizer.controller.chat;

import com.startup.enginizer.model.dto.ChatMessage;
import com.startup.enginizer.services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/chat")
    public void sendMessage(ChatMessage message) {
        messageService.addMessage(message);
        template.convertAndSend("/topic/message/" + message.getTopicId(),message);
    }
}