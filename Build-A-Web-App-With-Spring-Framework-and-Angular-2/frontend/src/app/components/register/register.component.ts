import { Component } from '@angular/core';
import { RegisterService } from '../../services/register/register.service';
import { User } from '../../models/user';

@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {

  newUser: User = new User();
  registered: boolean = false;

  constructor (private registerService: RegisterService) {}

  onSubmit() {
    console.log('submit test');

    this.registerService.sendUser(this.newUser)
      .subscribe(
        (data) => {
          this.registered = true;
          this.newUser = new User();
        },
        (error) => console.log(error)
      );
  }

}
