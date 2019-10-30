import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { AddNewBookComponent } from './components/add-new-book/add-new-book.component';


const routes: Routes = [{
  path: '',
  redirectTo: '/login',
  pathMatch: 'full'
}, {
  path: 'login',
  component: LoginComponent
}, {
  path: 'addNewBook',
  component: AddNewBookComponent
}];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
