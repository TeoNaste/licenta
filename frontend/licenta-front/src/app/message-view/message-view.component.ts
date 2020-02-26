import { Component, OnInit } from '@angular/core';
import { MessageService } from 'src/api/service/messageService';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-message-view',
  templateUrl: './message-view.component.html',
  styleUrls: ['./message-view.component.css']
})
export class MessageViewComponent implements OnInit {
  message: Message= null;
  id;
  filename = null;
  constructor(private messageService: MessageService,private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params =>{
      this.id = params.get("id");
      this.getMessage();
    });
    
  }

  public getMessage(){
    this.messageService.getMessage(this.id).subscribe(res => {
      console.log(res);
      this.message = JSON.parse(JSON.stringify(res));
      if(this.message.attachement === null){
        this.filename= null;
      }else{
        const parts = this.message.attachement.split("\\");
        this.filename = parts[parts.length-1];
      }
    }, err => {
      console.log(err)
    })
  }



}
