package com.startup.enginizer.model.dto;

/**
 * Created by dragos.triteanu on 11/15/15.
 */
public class MailMessage {

    private String sender;

    private String receiver;

    private String subject;

    private String content;


    public String getSender() {
        return sender;
    }

    public MailMessage withSender(String sender) {
        this.sender = sender;
        return this;
    }

    public MailMessage withReceiver(String receiver) {
        this.receiver = receiver;
        return this;
    }

    public MailMessage withContent(String content){
        this.content = content;
        return this;
    }

    public MailMessage withSubject(String subject){
        this.subject = subject;
        return this;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
