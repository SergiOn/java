import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatButtonModule, MatProgressSpinnerModule, MatTabsModule } from '@angular/material';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { MyAccountComponent } from './components/my-account/my-account.component';
import { UserService } from './services/user.service';
import { LoginService } from './services/login.service';
import { BookService } from './services/book.service';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { PaymentService } from 'src/app/services/payment.service';
import { ShippingService } from 'src/app/services/shipping.service';
import { OrderService } from 'src/app/services/order.service';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTabsModule,
    MatButtonModule,
    MatProgressSpinnerModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    MyAccountComponent,
    MyProfileComponent
  ],
  providers: [
    LoginService,
    UserService,
    BookService,
    PaymentService,
    ShippingService,
    OrderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
