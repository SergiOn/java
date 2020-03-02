import { Component, OnInit } from '@angular/core';
import { AppConst } from '../../constants/app-const';
import { Router } from '@angular/router';
import { Book } from '../../models/book';
import { CartService } from '../../services/cart.service';
import { ShoppingCart } from '../../models/shopping-cart';
import { CartItem } from '../../models/cart-item';

@Component({
  selector: 'app-shopping-cart',
  templateUrl: './shopping-cart.component.html',
  styleUrls: ['./shopping-cart.component.scss']
})
export class ShoppingCartComponent implements OnInit {

  public serverPath = AppConst.serverPath;
  private selectedBook: Book;
  public cartItemList: CartItem[] = [];
  public cartItemNumber: number;
  public shoppingCart: ShoppingCart = new ShoppingCart();
  public cartItemUpdated: boolean;
  public emptyCart: boolean;
  public notEnoughStock: boolean;

  constructor(
    private router: Router,
    private cartService: CartService
  ) { }

  ngOnInit() {
    this.getCartItemList();
    this.getShoppingCart();
  }

  onSelect(book: Book) {
    this.selectedBook = book;
    this.router.navigate(['/bookDetail', this.selectedBook.id]);
  }

  onRemoveCartItem(cartItem: CartItem) {
    this.cartService.removeCartItem(cartItem.id).subscribe(
      res => {
        console.log(res);
        this.getCartItemList();
        this.getShoppingCart();
      },
        error => {
        console.log(error);
      }
    );
  }

  onUpdateCartItem(cartItem: CartItem) {
    this.cartService.updateCartItem(cartItem.id, cartItem.qty).subscribe(
      res => {
        console.log(res);
        this.cartItemUpdated = true;
        this.getShoppingCart();
      },
        error => {
        console.log(error);
      }
    );
  }

  getCartItemList() {
    this.cartService.getCartItemList().subscribe(
      res => {
        this.cartItemList = res;
        this.cartItemNumber = this.cartItemList.length;
      },
        error => {
        console.log(error.text());
      }
    );
  }

  getShoppingCart() {
    this.cartService.getShoppingCart().subscribe(
      res => {
        console.log(res);
        this.shoppingCart = res;
      },
        error => {
        console.log(error.text());
      }
    );
  }

  onCheckout() {
    if (this.cartItemNumber === 0) {
      this.emptyCart = true;
    } else {
      for (const item of this.cartItemList) {
        if (item.qty > item.book.inStockNumber) {
          console.log('not enough stock on some item');
          this.notEnoughStock = true;
          return;
        }
      }

      // this.router.navigate('[/order]');
    }
  }

}
