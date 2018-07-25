import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from '@angular/router';
import {AuthenticationService} from '../authentication.service';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  token;

  userInfo;

  constructor(private activatedRoute: ActivatedRoute,
              private auth: AuthenticationService) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.token = params['token'];
      this.auth.userInfo(params['token']).subscribe(
        res => {
          this.userInfo = res;
        },
        error => console.log(error)
      )
    })
  }

  logout(){
    this.auth.logout(this.token).subscribe(
      res => {
        this.userInfo = '';
        this.token = '';
      },
      error => console.log(error)
    )
  }

  ngOnInit() {
  }

}
