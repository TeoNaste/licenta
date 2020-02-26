import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-message-card',
  templateUrl: './message-card.component.html',
  styleUrls: ['./message-card.component.css']
})
export class MessageCardComponent implements OnInit {
  @Input() message;
  hasAttachment: boolean = false;
  read : boolean = false;

  @Output() messageClicked: EventEmitter<number> =   new EventEmitter();

  constructor() { }

  ngOnInit() {
  }

  onClick(){
    this.read = true;
    this.messageClicked.emit(this.message)
  }

}
