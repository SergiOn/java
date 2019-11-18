import { Component, OnInit } from '@angular/core';
import { Params, ActivatedRoute, Router } from '@angular/router';
import { AppConst } from 'src/app/constants/app-const';
import { CheckoutService } from 'src/app/services/checkout.service';
import { CartService } from 'src/app/services/cart.service';
import { Order } from 'src/app/models/order';
import { CartItem } from 'src/app/models/cart-item';
import { Book } from 'src/app/models/book';


@Component({
  selector: 'app-order-summary',
  templateUrl: './order-summary.component.html',
  styleUrls: ['./order-summary.component.scss']
})
export class OrderSummaryComponent implements OnInit {

  public serverPath = AppConst.serverPath;
  private order: Order = new Order();
  public estimatedDeliveryDate: string;
  public cartItemList: CartItem[] = [];
  private selectedBook: Book;

  constructor(
    private checkoutService: CheckoutService,
    private cartService: CartService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      this.order = JSON.parse(params.order);

      const deliveryDate = new Date();

      if (this.order.shippingMethod === 'groundShipping') {
        deliveryDate.setDate(deliveryDate.getDate() + 5);
      } else {
        deliveryDate.setDate(deliveryDate.getDate() + 3);
      }

      const days = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
      this.estimatedDeliveryDate =
        days[deliveryDate.getDay()] + ', ' + deliveryDate.getFullYear() + '/' + deliveryDate.getMonth() + '/' + deliveryDate.getDate();

      this.cartItemList = this.order.cartItemList;
    });
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }

  onRemoveCartItem(cartItem: CartItem) {
    this.cartService.removeCartItem(cartItem.id).subscribe(
      res => {
        console.log(res);
      },
      error => {
        console.log(error.text());
      }
    );
  }

}
