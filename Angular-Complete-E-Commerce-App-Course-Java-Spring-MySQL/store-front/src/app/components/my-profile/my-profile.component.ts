import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { UserService } from 'src/app/services/user.service';
import { PaymentService } from 'src/app/services/payment.service';
import { ShippingService } from 'src/app/services/shipping.service';
import { OrderService } from 'src/app/services/order.service';
import { AppConst } from 'src/app/constants/app-const';
import { User } from 'src/app/models/user';
import { UserPayment } from 'src/app/models/user-payment';
import { UserBilling } from 'src/app/models/user-billing';
import { UserShipping } from 'src/app/models/user-shipping';
import { Order } from 'src/app/models/order';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.scss']
})
export class MyProfileComponent implements OnInit {

  private serverPath = AppConst.serverPath;
  public dataFetched = false;
  private loginError: boolean;
  private loggedIn: boolean;
  private credential = { username: '', password: '' };

  private user: User = new User();
  public updateSuccess: boolean;
  private newPassword: string;
  public incorrectPassword: boolean;
  private currentPassword: string;

  public selectedProfileTab = 0;
  private selectedBillingTab = 0;
  private selectedShippingTab = 0;

  private userPayment: UserPayment = new UserPayment();
  private userBilling: UserBilling = new UserBilling();
  private userPaymentList: UserPayment[] = [];
  private defaultPaymentSet: boolean;
  private defaultUserPaymentId: number;
  private stateList: string[] = [];

  private userShipping: UserShipping = new UserShipping();
  public userShippingList: UserShipping[] = [];

  private defaultUserShippingId: number;
  private defaultShippingSet: boolean;

  private orderList: Order[] = [];
  public order: Order = new Order();
  private displayOrderDetail: boolean;

  constructor(
    private router: Router,
    private loginService: LoginService,
    private userService: UserService,
    private paymentService: PaymentService,
    private shippingService: ShippingService,
    private orderService: OrderService
  ) { }

  ngOnInit() {
    this.loginService.checkSession().subscribe(
      res => {
        this.loggedIn = true;
      },
      error => {
        this.loggedIn = false;
        console.log('inactive session');
        this.router.navigate(['/myAccount']);
      }
    );

    this.getCurrentUser();

    this.orderService.getOrderList().subscribe(
      res => {
        this.orderList = res;
      },
      error => {
        console.log(error);
      }
    );

    for (const s in AppConst.usStates) {
      this.stateList.push(s);
    }

    this.userBilling.userBillingState = '';
    this.userPayment.type = '';
    this.userPayment.expiryMonth = '';
    this.userPayment.expiryYear = '';
    this.userPayment.userBilling = this.userBilling;
    this.defaultPaymentSet = false;

    this.userShipping.userShippingState = '';
    this.defaultShippingSet = false;
  }

  selectedShippingChange(val: number) {
    this.selectedShippingTab = val;
  }

  selectedBillingChange(val: number) {
    this.selectedBillingTab = val;
  }

  onUpdateUserInfo() {
    this.userService.updateUserInfo(this.user, this.newPassword, this.currentPassword).subscribe(
      res => {
        console.log(res);
        this.updateSuccess = true;
      },
      error => {
        console.log(error);
        if (error === 'Incorrect current password!') {
          this.incorrectPassword = true;
        }
      }
    );
  }

  onNewPayment() {
    this.paymentService.newPayment(this.userPayment).subscribe(
      res => {
        this.getCurrentUser();
        this.selectedBillingTab = 0;
        this.userPayment = new UserPayment();
      },
      error => {
        console.log(error);
      }
    );
  }

  onUpdatePayment(payment: UserPayment) {
    this.userPayment = payment;
    this.userBilling = payment.userBilling;
    this.selectedBillingTab = 1;
  }

  onRemovePayment(id: number) {
    this.paymentService.removePayment(id).subscribe(
      res => {
        this.getCurrentUser();
      },
      error => {
        console.log(error);
      }
    );
  }

  setDefaultPayment() {
    this.defaultPaymentSet = false;
    this.paymentService.setDefaultPayment(this.defaultUserPaymentId).subscribe(
      res => {
        this.getCurrentUser();
        this.defaultPaymentSet = true;
      },
      error => {
        console.log(error);
      }
    );
  }

  onNewShipping() {
    this.shippingService.newShipping(this.userShipping).subscribe(
      res => {
        this.getCurrentUser();
        this.selectedShippingTab = 0;
        this.userShipping = new UserShipping();
      },
      error => {
        console.log(error);
      }
    );
  }

  onUpdateShipping(shipping: UserShipping) {
    this.userShipping = shipping;
    this.selectedShippingTab = 1;
  }

  onRemoveShipping(id: number) {
    this.shippingService.removeShipping(id).subscribe(
      res => {
        this.getCurrentUser();
      },
      error => {
        console.log(error);
      }
    );
  }

  setDefaultShipping() {
    this.defaultShippingSet = false;
    this.shippingService.setDefaultShipping(this.defaultUserShippingId).subscribe(
      res => {
        this.getCurrentUser();
        this.defaultShippingSet = true;
      },
      error => {
        console.log(error);
      }
    );
  }

  getCurrentUser() {
    this.userService.getCurrentUser().subscribe(
      res => {
        this.user = res;
        this.userPaymentList = this.user.userPaymentList;
        this.userShippingList = this.user.userShippingList;

        for (const index in this.userPaymentList) {
          if (this.userPaymentList[index].defaultPayment) {
            this.defaultUserPaymentId = this.userPaymentList[index].id;
            break;
          }
        }

        for (const index in this.userShippingList) {
          if (this.userShippingList[index].userShippingDefault) {
            this.defaultUserShippingId = this.userShippingList[index].id;
            break;
          }
        }

        this.dataFetched = true;
      },
      err => {
        console.log(err);
      }
    );
  }

  onDisplayOrder(order: Order) {
    console.log(order);
    this.order = order;
    this.displayOrderDetail = true;
  }

}
