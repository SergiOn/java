import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class UploadImageService {

  filesToUpload: File[];

  constructor(private http: HttpClient) {
    this.filesToUpload = [];
  }

  upload(bookId: number) {
    console.log(this.filesToUpload);
    this.makeFileRequest(`http://localhost:8181/book/add/image?id=${bookId}`, [], this.filesToUpload).subscribe((result) => {
      console.log(result);
    }, (error) => {
      console.log(error);
    });
  }

  modify(bookId: number) {
    console.log(this.filesToUpload);
    if (this.filesToUpload.length) {
      this.makeFileRequest(`http://localhost:8181/book/update/image?id=${bookId}`, [], this.filesToUpload).subscribe((result) => {
        console.log(result);
      }, (error) => {
        console.log(error);
      });
    }
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = fileInput.target.files;
  }

  makeFileRequest(url: string, params: string[], files: File[]) {
    const formData: FormData = new FormData();

    for (let i = 0; i < files.length; i++) {
      formData.append('uploads[]', files[i], files[i].name);
    }

    const headers = new HttpHeaders({
      'x-auth-token' : localStorage.getItem('xAuthToken')
    });

    return this.http.post(url, formData, { headers, responseType: 'text' });
  }

}
