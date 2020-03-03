import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ComponentsModule } from './components/components.module';
import { AddBookService } from './services/add-book.service';
import { LoginService } from './services/login.service';
import { UploadImageService } from './services/upload-image.service';
import { GetBookListService } from './services/get-book-list.service';
import { GetBookService } from 'src/app/services/get-book.service';
import { EditBookService } from 'src/app/services/edit-book.service';

@NgModule({
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    ComponentsModule
  ],
  declarations: [
    AppComponent,
  ],
  providers: [
    LoginService,
    AddBookService,
    UploadImageService,
    GetBookListService,
    GetBookService,
    EditBookService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }