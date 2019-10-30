import { Component } from '@angular/core';
import { Observable, Subject } from 'rxjs';
import { scan, startWith } from 'rxjs/operators';

@Component({
  selector: 'app-nav-bar',
  templateUrl: './nav-bar.component.html',
  styleUrls: ['./nav-bar.component.scss']
})
export class NavBarComponent {

  private loggedInSync: Subject<void> = new Subject<void>();
  public loggedIn$: Observable<boolean> = this.loggedInSync.asObservable().pipe(
    startWith(false),
    scan((accumulator: boolean, current: undefined) => !accumulator)
  );

  constructor() {
  }

  toggleDisplay(): void {
    this.loggedInSync.next();
  }

}
