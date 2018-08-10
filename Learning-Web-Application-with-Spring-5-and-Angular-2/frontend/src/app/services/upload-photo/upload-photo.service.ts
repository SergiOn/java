import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable()
export class UploadPhotoService {
  filesToUpload: Array<File>;

  constructor(private http: HttpClient) {
    this.filesToUpload = [];
  }

  upload() {
    this.makeFileRequest('http://localhost:8080/rest/photo/upload', [], this.filesToUpload)
      .subscribe(
        (result) => {
          console.log(result);
        },
        (error) => {
          console.error(error);
        }
      );

    // this.makeFileRequest('http://localhost:8080/rest/photo/upload', [], this.filesToUpload).then((result) => {
    //   console.log(result);
    // }, (error) => {
    //   console.error(error);
    // });
  }

  fileChangeEvent(fileInput: any) {
    this.filesToUpload = <Array<File>> fileInput.target.files;
  }

  makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
    const formData: any = new FormData();

    /** In Angular 5, including the header Content-Type can invalidate your request */
    const headers = new HttpHeaders({
      // 'Content-Type': 'multipart/form-data',
      // 'Accept': 'application/json',
      'Authorization': 'Bearer ' + localStorage.getItem('token')
    });

    for (let i = 0; i < files.length; i++) {
      formData.append('uploads[]', files[i], files[i].name);
    }

    return this.http.post(url, formData, { headers: headers });
      // .subscribe(
      //   data => alert('Upload successful!'),
      //   error => console.log(error)
      // );
  }

  // makeFileRequest(url: string, params: Array<string>, files: Array<File>) {
  //   return new Promise((resolve, reject) => {
  //     const formData: any = new FormData();
  //     const xhr = new XMLHttpRequest();
  //
  //     for (let i = 0; i < files.length; i++) {
  //       formData.append('uploads[]', files[i], files[i].name);
  //     }
  //     xhr.onreadystatechange = function () {
  //       if (xhr.readyState === 4) {
  //         if (xhr.status === 200) {
  //           alert('Upload successful!');
  //         } else {
  //           reject(xhr.response);
  //         }
  //       }
  //     };
  //     xhr.open('POST', url, true);
  //     xhr.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
  //     xhr.send(formData);
  //   });
  // }

}
