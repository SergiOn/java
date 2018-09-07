import { Component, OnInit } from '@angular/core';
import { Router } from  '@angular/router';
import {AuthenticationService} from '../authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  token;

  constructor(private auth: AuthenticationService,
  private router: Router) { }

  ngOnInit() {
  }

  login(username: string, password: string){
    this.auth.login(username, password).subscribe(
      res =>{
        this.token = res.access_token;
        if(res.access_token){
          this.router.navigate(['/user/' + this.token]);
        }
      },
      error => console.log(error)
    )
  }
}
