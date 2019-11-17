import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { MyAccountComponent } from 'src/app/components/my-account/my-account.component';


const routes: Routes = [
  {
    path: 'home',
    component: HomeComponent
  }, {
    path: 'myAccount',
    component: MyAccountComponent
  }, {
    path: '',
    redirectTo: '/home',
    pathMatch: 'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
