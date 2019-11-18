import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router} from '@angular/router';
import { Book } from 'src/app/models/book';
import { BookService } from 'src/app/services/book.service';
import { AppConst } from 'src/app/constants/app-const';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  public filterQuery = '';
  public rowsOnPage = 5;

  private selectedBook: Book;
  public bookList: Book[];
  public serverPath = AppConst.serverPath;

  constructor(
    private bookService: BookService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params.bookList) {
        console.log('filtered book list');
        this.bookList = JSON.parse(params.bookList);
      } else {
        this.bookService.getBookList().subscribe(
          res => {
            console.log(res);
            this.bookList = res;
          },
          err => {
            console.log(err);
          }
        );
      }
    });
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }

}
