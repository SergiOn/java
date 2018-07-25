import { Component, OnInit } from '@angular/core';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  isLoggedIn = false;

  counter = 0;

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
    setInterval(()=>{
      this.counter ++;
    }, 1000)
  }

  login(username: string, password: string){
    this.isLoggedIn = this.authenticationService.isLoggedIn(username, password);
  }

}
