import { Component, OnInit } from '@angular/core';
import {Params, ActivatedRoute, Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { CartService } from 'src/app/services/cart.service';
import { AppConst } from 'src/app/constants/app-const';

@Component({
  selector: 'app-book-detail',
  templateUrl: './book-detail.component.html',
  styleUrls: ['./book-detail.component.scss']
})
export class BookDetailComponent implements OnInit {

  private bookId: number;
  public book: Book = new Book();
  public serverPath = AppConst.serverPath;
  public numberList: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9];
  public qty: number;

  public addBookSuccess = false;
  public notEnoughStock = false;

  constructor(
    private bookService: BookService,
    private cartService: CartService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      this.bookId = Number.parseInt(params.id, 10);
    });

    this.bookService.getBook(this.bookId).subscribe(
      res => {
        this.book = res;
      },
      error => {
        console.log(error);
      }
    );

    this.qty = 1;
  }

  onAddToCart() {
    this.cartService.addItem(this.bookId, this.qty).subscribe(
      res => {
        console.log(res);
        this.addBookSuccess = true;
      },
      err => {
        console.log(err);
        this.notEnoughStock = true;
      }
    );
  }

}
