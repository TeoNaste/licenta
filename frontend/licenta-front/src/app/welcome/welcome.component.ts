import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-welcome',
  templateUrl: './welcome.component.html',
  styleUrls: ['./welcome.component.css']
})
export class WelcomeComponent implements OnInit {

  images = ["../../assets/images/slideshow1.jpg","../../assets/images/slideshow2.jpeg","../../assets/images/slideshow3.jpg"];

  items : Array<any> = [];
  constructor() { 

  }

  ngOnInit() {
  }

}
