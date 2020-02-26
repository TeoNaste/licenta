import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  router: Router;
  nothingSelected : boolean = true;
  constructor(router: Router) { 
    this.router = router;
  }

  ngOnInit() {
    const currentUser = { id: 3,username: "tahani",password: "tahani"};
    localStorage.setItem("currentUser",JSON.stringify(currentUser));
    this.nothingSelected = true;
  }

  messageClickedHandler(event){
    // console.log(message);
    this.nothingSelected=false;
    this.router.navigateByUrl(`/messages/${event.received}/view/${event.message.id}`);
  }

}
