import { Component, OnInit } from '@angular/core';
import { Book } from 'src/app/models/book';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material';
import { GetBookListService } from 'src/app/services/get-book-list.service';
import { RemoveBookService } from 'src/app/services/remove-book.service';

@Component({
  selector: 'app-book-list',
  templateUrl: './book-list.component.html',
  styleUrls: ['./book-list.component.scss']
})
export class BookListComponent implements OnInit {

  public bookList: Book[];
  public allChecked: boolean;

  private selectedBook: Book;
  private checked: boolean;
  private removeBookList: Book[] = [];

  constructor(
    private getBookListService: GetBookListService,
    private removeBookService: RemoveBookService,
    private router: Router,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.getBookList();
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/viewBook', this.selectedBook.id]);
  }

  openDialog(book: Book) {
    const dialogRef = this.dialog.open(DialogResultExampleDialogComponent);
    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
        if (result === 'yes') {
          this.removeBookService.sendBook(book.id).subscribe(
            res => {
              console.log(res);
              this.getBookList();
            },
            err => {
              console.log(err);
            }
          );
        }
      }
    );
  }

  updateRemoveBookList(checked: boolean, book: Book) {
    if (checked) {
      this.removeBookList.push(book);
    } else {
      this.removeBookList.splice(this.removeBookList.indexOf(book), 1);
    }
  }

  updateSelected(checked: boolean) {
    if (checked) {
      this.allChecked = true;
      this.removeBookList = this.bookList.slice();
    } else {
      this.allChecked = false;
      this.removeBookList = [];
    }
  }

  removeSelectedBooks() {
    const dialogRef = this.dialog.open(DialogResultExampleDialogComponent);
    dialogRef.afterClosed().subscribe(
      result => {
        console.log(result);
        if (result === 'yes') {
          for (const book of this.removeBookList) {
            this.removeBookService.sendBook(book.id).subscribe(
              res => {

              },
              err => {
              }
            );
          }
          location.reload();
        }
      }
    );
  }

  getBookList() {
    this.getBookListService.getBookList().subscribe(
      res => {
        // console.log(res);
        this.bookList = res;
      },
      error => {
        console.log(error);
      }
    );
  }

}

@Component({
  selector: 'app-dialog-result-example-dialog',
  templateUrl: './dialog-result-example-dialog.html'
})
export class DialogResultExampleDialogComponent {
  constructor(public dialogRef: MatDialogRef<DialogResultExampleDialogComponent>) {}
}
