import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { routing } from './app.routing';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { PhotoListComponent } from './components/photo-list/photo-list.component';
import { PhotoService } from './services/photo/photo.service';
import { SidePanelComponent } from './components/side-panel/side-panel.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { RegisterComponent } from './components/register/register.component';
import { RegisterService } from './services/register/register.service';
import { LoginComponent } from './components/login/login.component';
import { LoginService } from './services/login/login.service';
import { MyAlbumComponent } from './components/my-album/my-album.component';
import { UserService } from './services/user/user.service';
import { AddPhotoComponent } from './components/add-photo/add-photo.component';
import { AddPhotoService } from './services/add-photo/add-photo.service';
import { UploadPhotoService } from './services/upload-photo/upload-photo.service';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    PhotoListComponent,
    SidePanelComponent,
    NavBarComponent,
    RegisterComponent,
    LoginComponent,
    MyAlbumComponent,
    AddPhotoComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    routing,
  ],
  providers: [
    PhotoService,
    RegisterService,
    LoginService,
    UserService,
    AddPhotoService,
    UploadPhotoService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
