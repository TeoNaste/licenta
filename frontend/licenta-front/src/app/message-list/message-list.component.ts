import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { MessageService } from 'src/api/service/messageService';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-message-list',
  templateUrl: './message-list.component.html',
  styleUrls: ['./message-list.component.css']
})
export class MessageListComponent implements OnInit {
  messages: Message[];
  received: String;
  idUser = 3;
  @Output() messageClicked: EventEmitter<any> =   new EventEmitter();
  constructor(
    private messageService: MessageService,
    private route: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params =>{
      this.received = params.get("received");
      if(this.received === '1'){
        this.getMessagesReceived();
      }else{
        this.getMessagesSent();
      }
    });
  }

  public getMessagesReceived(){
    this.messageService.getMessagesReceivedForUser(this.idUser)
    .subscribe(res => {
      this.messages = JSON.parse(JSON.stringify(res));
      this.messages = this.messages.sort(function(a, b){
        if(a.date < b.date) { return 1; }
        if(a.date > b.date) { return -1; }
        return 0;})
    }, err => {
      console.log(err)
    }
  );
  }

  public getMessagesSent(){
    this.messageService.getMessagesSentByUser(this.idUser)
    .subscribe(res => {
      this.messages = JSON.parse(JSON.stringify(res));
      this.messages = this.messages.sort(function(a, b){
        if(a.date < b.date) { return 1; }
        if(a.date > b.date) { return -1; }
        return 0;})
    }, err => {
      console.log(err)
    }
  );
  }

  messageClickedHandler(message: Message){
    const event = {message: message,received: this.received}
    this.messageClicked.emit(event);
  }

}
