package model;


import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;


@Entity
@Table
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "sender")
    private User sender;
    @ManyToOne
    @JoinColumn(name = "receiver")
    private User receiver;
    private String subject;
    @Column(length = 500)
    private String text;
    private Date date;
    private byte[] attachement;
    private MessageStatus status;

    public Message() { }

    public Message(User sender, User receiver, String subject, String text, Date date, byte[] attachement, MessageStatus status) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.text = text;
        this.date = date;
        this.attachement = attachement;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte[] getAttachement() {
        return attachement;
    }

    public void setAttachement(byte[] attachement) {
        this.attachement = attachement;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public MessageStatus getStatus() {
        return status;
    }

    public void setStatus(MessageStatus status) {
        this.status = status;
    }
}
