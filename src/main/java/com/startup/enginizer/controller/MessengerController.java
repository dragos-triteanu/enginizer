package com.startup.enginizer.controller;

import com.startup.enginizer.model.dto.ChatMessage;
import com.startup.enginizer.model.entities.User;
import com.startup.enginizer.services.MessageService;
import com.startup.enginizer.utils.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


/**
 * Created by Dragos on 10/6/2015.
 */
@Controller
@RequestMapping("/messenger")
public class MessengerController {

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.GET)
    public String getOrderDetails(ModelMap modelMap) {
        SessionUtils.populateModelWithAuthenticatedRole(modelMap);

        User currentUser = SessionUtils.GetCurrentUser();
        modelMap.put("currentUser",currentUser);

        List<ChatMessage> messages = messageService.getChatMessages();
        modelMap.addAttribute("messages",messages);

        return "messengerPage";
    }

    @RequestMapping(value = "/allMessages" , method = RequestMethod.GET)
    public ResponseEntity<Object> getChatMessages(ModelMap modelMap){
        ResponseEntity<Object> entity = new ResponseEntity<Object>(messageService.getChatMessages(),HttpStatus.OK);
        return entity;
    }
}
