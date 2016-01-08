package com.startup.enginizer.model.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Dragos on 10/7/2015.
 */
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private int messageId;

    @Column(name = "content" , nullable = true)
    private String content;

    @Column(name = "timestamp" , nullable = false)
    private Timestamp timestamp;

    @Column(name = "sender" , nullable = false)
    private long sender;

    @Column(name = "fileName" , nullable = true)
    private String fileName;

    @Column(name = "location" , nullable = true)
    private String location;

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getSender() {
        return sender;
    }

    public void setSender(long sender) {
        this.sender = sender;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
