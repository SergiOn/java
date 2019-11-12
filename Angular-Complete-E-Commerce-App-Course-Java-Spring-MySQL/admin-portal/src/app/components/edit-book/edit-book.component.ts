import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Book } from 'src/app/models/book';
import { UploadImageService } from 'src/app/services/upload-image.service';
import { GetBookService } from 'src/app/services/get-book.service';
import { EditBookService } from 'src/app/services/edit-book.service';

@Component({
  selector: 'app-edit-book',
  templateUrl: './edit-book.component.html',
  styleUrls: ['./edit-book.component.scss']
})
export class EditBookComponent implements OnInit {

  public book: Book = new Book();
  public bookUpdated: boolean;
  private bookId: number;

  constructor(
    public uploadImageService: UploadImageService,
    private editBookService: EditBookService,
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
      error => console.log(error)
    );
  }

  onSubmit() {
    this.editBookService.sendBook(this.book).subscribe(
      data => {
        this.uploadImageService.modify(data.id);
        this.bookUpdated = true;
      },
      error => console.log(error)
    );
  }

}
