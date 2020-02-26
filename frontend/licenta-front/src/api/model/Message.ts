

class Message{
    id: number;
    receiver: String;
    sender: User;
    subject: String;
    text: String;
    date: Date;
    attachement: String;

    constructor(id,receiver,sender,subject, text,date,attachemet){
        this.id = id;
        this.receiver = receiver;
        this.sender = sender;
        this.subject = subject;
        this.text = text;
        this.date = date;
        this.attachement = attachemet;
    }

}