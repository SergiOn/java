import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { GetBookService } from 'src/app/services/get-book.service';

@Component({
  selector: 'app-view-book',
  templateUrl: './view-book.component.html',
  styleUrls: ['./view-book.component.scss']
})
export class ViewBookComponent implements OnInit {

  public book: Book = new Book();
  private bookId: number;

  constructor(
    private getBookService: GetBookService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.route.params.forEach((params: Params) => {
      this.bookId = Number.parseInt(params.id, 10);
    });

    this.getBookService.getBook(this.bookId).subscribe(
      res => {
        this.book = res;
      },
      error => {
        console.log(error);
      }
    );
  }

  onSelect(book: Book) {
    this.router.navigate(['/editBook', this.book.id])
    // .then(s => location.reload())
    ;
  }


}
