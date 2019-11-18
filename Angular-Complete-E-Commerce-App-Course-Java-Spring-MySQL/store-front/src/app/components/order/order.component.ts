import { Component, OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';
import { AppConst } from 'src/app/constants/app-const';
import { Book } from 'src/app/models/book';
import { CartService } from 'src/app/services/cart.service';
import { ShippingService } from 'src/app/services/shipping.service';
import { PaymentService } from 'src/app/services/payment.service';
import { CheckoutService } from 'src/app/services/checkout.service';
import { CartItem } from 'src/app/models/cart-item';
import { ShoppingCart } from 'src/app/models/shopping-cart';
import { ShippingAddress } from 'src/app/models/shipping-address';
import { BillingAddress } from 'src/app/models/billing-address';
import { UserPayment } from 'src/app/models/user-payment';
import { UserBilling } from 'src/app/models/user-billing';
import { UserShipping } from 'src/app/models/user-shipping';
import { Payment } from 'src/app/models/payment';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit {

  public serverPath = AppConst.serverPath;
  private selectedBook: Book;
  public cartItemList: CartItem[] = [];
  public cartItemNumber: number;
  public shoppingCart: ShoppingCart = new ShoppingCart();
  private cartItemUpdated: boolean;
  public shippingAddress: ShippingAddress = new ShippingAddress();
  public billingAddress: BillingAddress = new BillingAddress();
  private userPayment: UserPayment = new UserPayment();
  private userShipping: UserShipping = new UserShipping();
  private userBilling: UserBilling = new UserBilling();
  public userShippingList: UserShipping[] = [];
  public userPaymentList: UserPayment[] = [];
  public payment: Payment = new Payment();
  public selectedTab: number;
  public emptyShippingList = true;
  public emptyPaymentList = true;
  public stateList: string[] = [];
  public shippingMethod: string;
  private order: Order = new Order();

  constructor(
    private router: Router,
    private cartService: CartService,
    private shippingService: ShippingService,
    private paymentService: PaymentService,
    private checkoutService: CheckoutService
  ) { }

  ngOnInit() {
    this.getCartItemList();

    this.cartService.getShoppingCart().subscribe(
      res => {
        console.log(res);
        this.shoppingCart = res;
      },
      error => {
        console.log(error);
      }
    );

    this.shippingService.getUserShippingList().subscribe(
      res => {
        console.log(res);
        this.userShippingList = res;
        if (this.userShippingList.length) {
          this.emptyShippingList = false;

          for (const userShipping of this.userShippingList) {
            if (userShipping.userShippingDefault) {
              this.setShippingAddress(userShipping);
              return;
            }
          }
        }
      },
      error => {
        console.log(error);
      }
    );

    this.paymentService.getUserPaymentList().subscribe(
      res => {
        console.log(res);
        this.userPaymentList = res;
        this.emptyPaymentList = false;

        if (this.userPaymentList.length) {
          this.emptyPaymentList = false;

          for (const userPayment of this.userPaymentList) {
            if (userPayment.defaultPayment) {
              this.setPaymentMethod(userPayment);
              return;
            }
          }
        }
      },
      error => {
        console.log(error);
      }
    );

    for (const s in AppConst.usStates) {
      this.stateList.push(s);
    }

    this.payment.type = '';
    this.payment.expiryMonth = '';
    this.payment.expiryYear = '';
    this.billingAddress.billingAddressState = '';
    this.shippingAddress.shippingAddressState = '';
    this.shippingMethod = 'groundShipping';
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }

  selectedChange(val: number) {
    this.selectedTab = val;
  }

  goToPayment() {
    this.selectedTab = 1;
  }

  goToReview() {
    this.selectedTab = 2;
  }

  getCartItemList() {
    this.cartService.getCartItemList().subscribe(
      res => {
        this.cartItemList = res;
        this.cartItemNumber = this.cartItemList.length;
      },
      error => {
        console.log(error);
      }
      );
  }

  setShippingAddress(userShipping: UserShipping) {
    this.shippingAddress.shippingAddressName = userShipping.userShippingName;
    this.shippingAddress.shippingAddressStreet1 = userShipping.userShippingStreet1;
    this.shippingAddress.shippingAddressStreet2 = userShipping.userShippingStreet2;
    this.shippingAddress.shippingAddressCity = userShipping.userShippingCity;
    this.shippingAddress.shippingAddressState = userShipping.userShippingState;
    this.shippingAddress.shippingAddressCountry = userShipping.userShippingCountry;
    this.shippingAddress.shippingAddressZipcode = userShipping.userShippingZipcode;
  }

  setPaymentMethod(userPayment: UserPayment) {
    this.payment.type = userPayment.type;
    this.payment.cardNumber = userPayment.cardNumber;
    this.payment.expiryMonth = userPayment.expiryMonth;
    this.payment.expiryYear = userPayment.expiryYear;
    this.payment.cvc = userPayment.cvc;
    this.payment.holderName = userPayment.holderName;
    this.payment.defaultPayment = userPayment.defaultPayment;
    this.billingAddress.billingAddressName = userPayment.userBilling.userBillingName;
    this.billingAddress.billingAddressStreet1 = userPayment.userBilling.userBillingStreet1;
    this.billingAddress.billingAddressStreet2 = userPayment.userBilling.userBillingStreet2;
    this.billingAddress.billingAddressCity = userPayment.userBilling.userBillingCity;
    this.billingAddress.billingAddressState = userPayment.userBilling.userBillingState;
    this.billingAddress.billingAddressCountry = userPayment.userBilling.userBillingCountry;
    this.billingAddress.billingAddressZipcode = userPayment.userBilling.userBillingZipcode;
  }

  setBillingAsShipping(checked: boolean) {
    console.log('same as shipping');

    if (checked) {
      this.billingAddress.billingAddressName = this.shippingAddress.shippingAddressName;
      this.billingAddress.billingAddressStreet1 = this.shippingAddress.shippingAddressStreet1;
      this.billingAddress.billingAddressStreet2 = this.shippingAddress.shippingAddressStreet2;
      this.billingAddress.billingAddressCity = this.shippingAddress.shippingAddressCity;
      this.billingAddress.billingAddressState = this.shippingAddress.shippingAddressState;
      this.billingAddress.billingAddressCountry = this.shippingAddress.shippingAddressCountry;
      this.billingAddress.billingAddressZipcode = this.shippingAddress.shippingAddressZipcode;
    } else {
      this.billingAddress.billingAddressName = '';
      this.billingAddress.billingAddressStreet1 = '';
      this.billingAddress.billingAddressStreet2 = '';
      this.billingAddress.billingAddressCity = '';
      this.billingAddress.billingAddressState = '';
      this.billingAddress.billingAddressCountry = '';
      this.billingAddress.billingAddressZipcode = '';
    }
  }

  onSubmit() {
    this.checkoutService.checkout(
      this.shippingAddress,
      this.billingAddress,
      this.payment,
      this.shippingMethod
      ).subscribe(
      res => {
        this.order = res;
        console.log(this.order);

        const navigationExtras: NavigationExtras = {
            queryParams: {
              order: JSON.stringify(this.order)
            }
        };

        this.router.navigate(['/orderSummary'], navigationExtras);
      },
      error => {
        console.log(error.text());
      }
      );
    }

}
