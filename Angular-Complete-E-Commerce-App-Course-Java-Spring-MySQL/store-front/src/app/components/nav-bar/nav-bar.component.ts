import { Component, OnInit } from '@angular/core';
import { NavigationExtras, Router } from '@angular/router';
import { Book } from 'src/app/models/book';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent implements OnInit {

  public loggedIn = false;
  public keyword: string;
  public bookList: Book[] = [];

  constructor(
    private router: Router,
    // private loginService: LoginService,
    // private bookService: BookService
  ) { }

  ngOnInit() {
    // this.loginService.checkSession().subscribe(
    //   res => {
    //     this.loggedIn = true;
    //   },
    //   err => {
    //     this.loggedIn =false;
    //   }
    // );
  }

  logout() {
    // this.loginService.logout().subscribe(
    //   res => {
    //     location.reload();
    //   },
    //   err => {
    //     console.log(err);
    //   }
    // );
  }

  onSearchByTitle() {
    // this.bookService.searchBook(this.keyword).subscribe(
    //   res => {
    //     this.bookList = res.json();
    //     console.log(this.bookList);
    //     const navigationExtras: NavigationExtras = {
    //       queryParams: {
    //         'bookList' : JSON.stringify(this.bookList)
    //       }
    //     };
    //
    //     this.router.navigate(['/bookList'], navigationExtras);
    //   },
    //   error => {
    //     console.log(error);
    //   }
    // );
  }

}
