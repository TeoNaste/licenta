package model;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table
public class Message implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "senderId")
    private User sender;
    @OneToMany
    @JoinColumn(name = "receiverId")
    private User receiver;
    @Column(length = 500)
    private String subject;
    private String text;
    private Date date;
    private byte[] attachement;
    private MessageStatus messageStatus;


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
}
