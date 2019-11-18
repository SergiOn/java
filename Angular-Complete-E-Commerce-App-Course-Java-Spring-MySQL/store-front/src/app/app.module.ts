import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';
import { MatButtonModule, MatProgressSpinnerModule, MatTabsModule } from '@angular/material';
// import { DataTableModule } from 'angular2-datatable';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './components/home/home.component';
import { NavBarComponent } from './components/nav-bar/nav-bar.component';
import { MyAccountComponent } from './components/my-account/my-account.component';
import { UserService } from './services/user.service';
import { LoginService } from './services/login.service';
import { BookService } from './services/book.service';
import { MyProfileComponent } from './components/my-profile/my-profile.component';
import { PaymentService } from './services/payment.service';
import { ShippingService } from './services/shipping.service';
import { OrderService } from './services/order.service';
import { CartService } from './services/cart.service';
import { CheckoutService } from './services/checkout.service';
import { BookDetailComponent } from './components/book-detail/book-detail.component';
import { DataFilterPipe } from './components/book-list/data-filter.pipe';
import { BookListComponent } from './components/book-list/book-list.component';
import { OrderComponent } from 'src/app/components/order/order.component';
import { OrderSummaryComponent } from './components/order-summary/order-summary.component';
import { ShoppingCartComponent } from './components/shopping-cart/shopping-cart.component';

@NgModule({
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTabsModule,
    MatButtonModule,
    MatProgressSpinnerModule,
    // DataTableModule
  ],
  declarations: [
    AppComponent,
    HomeComponent,
    NavBarComponent,
    MyAccountComponent,
    MyProfileComponent,
    BookDetailComponent,
    BookListComponent,
    OrderComponent,
    OrderSummaryComponent,
    ShoppingCartComponent,
    DataFilterPipe
  ],
  providers: [
    LoginService,
    UserService,
    BookService,
    PaymentService,
    ShippingService,
    OrderService,
    CartService,
    CheckoutService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
