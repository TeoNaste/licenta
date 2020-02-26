import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MessageService } from 'src/api/service/messageService';
import { PredictionService } from 'src/api/service/predictionService';

@Component({
  selector: 'app-message-add',
  templateUrl: './message-add.component.html',
  styleUrls: ['./message-add.component.css']
})
export class MessageAddComponent implements OnInit {
  newMessageForm: FormGroup;
  prefix1:String = "";
  prefix2:String = "";
  text: String ="";
  options: State[];
  predictions: State[];
  attachment = null;
  router: Router;
  receiver = "";
  subject = "";
  rightPredictions: number = 0;
  confirmation = "";
  constructor(router: Router,private messageService: MessageService,private predictionService: PredictionService) {
    this.router = router;
   }

  ngOnInit() {
    this.newMessageForm = new FormGroup({
      receiver: new FormControl('',[Validators.required]),
      subject: new FormControl(''),
      text: new FormControl(''),
      attachment: new FormControl(''),
    })
  }

  public onFileSelected(event){
    console.log(event);
    const parts = this.attachment.split("\\");
    this.attachment = parts[parts.length-1];
  }

  public getPredictions(){
    const prefix : Prefix = {
      prefix1: this.prefix1,
      prefix2: this.prefix2,
      states: []
    }
    this.predictionService.getPredictions(prefix).subscribe(res =>{
      this.predictions = JSON.parse(JSON.stringify(res)).states;
      this.predictions.sort((a,b)=> b.probability - a.probability);
      this.options = this.predictions.slice(0,5);
    }, err => {
      console.log(err)
    })
  }

  public onChangeHandler(){
    const words = this.text.split(" ");
    const lastWord = words[words.length-1];
    console.log(lastWord);
    if(lastWord === ""){
      if(words.length > 1){
        this.prefix1 = this.prefix2;
        this.prefix2 = words[words.length-2];
      }
      if(lastWord[lastWord.length-1]!='.')
        this.getPredictions();
    }
  }

  public getValue(option){
    return `${this.text} ${option.value}`;
  }

  public onSelectionClicked(){
    this.rightPredictions++;
  }

  public hasError = (controlName: string, errorName: string) =>{
    return this.newMessageForm.controls[controlName].hasError(errorName);
  }

  public onSubmit = () =>{
    const receiver : String = this.newMessageForm.get("receiver").value;
    const subject : String = this.newMessageForm.get("subject").value  === undefined ? "[NO SUBJECT]" : this.newMessageForm.get("subject").value;
    const text : String =this.newMessageForm.get("text").value;
    const sender : User= JSON.parse(localStorage.getItem("currentUser"));
    const date : Date = null;
    const attachment : String = this.newMessageForm.get("attachment").value;
    console.log(attachment);
    const message = {
      id:0,
      receiver: receiver,
      sender: sender,
      subject: subject,
      text: text,
      date: date,
      attachement: attachment};
    this.messageService.saveMessage(message).subscribe(res =>{
      // console.log(res);
      this.confirmation="Message sent";
      this.onUpdateModel(this.text);
    }, err => {
      console.log(err)
    });

  }

  public onUpdateModel = (text: String) => {
    this.predictionService.updateModel(text).subscribe(res =>{
      // console.log(res);
      this.onUpdateAccuracy();
    }, err => {
      console.log(err)
    })
  }

  public onUpdateAccuracy = () =>{
    const accuracy : Accuracy = {
      accuracy: 0,
      wordsCount: this.text.split(" ").length,
      rightPredictions: this.rightPredictions,
    }
    this.predictionService.updateAccuracy(accuracy).subscribe(res =>{
      console.log(res);
      this.rightPredictions = 0;
    })
  }

  public onCancel = () =>{
    this.router.navigateByUrl("");
  }

}
