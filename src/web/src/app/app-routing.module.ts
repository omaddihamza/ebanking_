import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomersComponent} from "./components/customers/customers.component";
import {AccountsComponent} from "./components/accounts/accounts.component";
import {NewCustomerComponent} from "./components/new-customer/new-customer.component";
import {LoginComponent} from "./components/login/login.component";
import {AdminTemplateComponent} from "./components/admin-template/admin-template.component";
import {authenticationGuard} from "./guards/authentication.guard";
import {authorizationGuard} from "./guards/authorization.guard";
import {NotAuthorizedComponent} from "./components/not-authorized/not-authorized.component";

const routes: Routes = [
  {path:"login", component:LoginComponent},
  {path:'', redirectTo:"login", pathMatch:'full'},
  {path:"admin", component:AdminTemplateComponent, canActivate:[authenticationGuard],
    children:[
      {path:"customers", component:CustomersComponent},
      {path:"new-customer", component:NewCustomerComponent, canActivate:[authorizationGuard], data:{role:"ADMIN"}},
      {path:"accounts", component:AccountsComponent, canActivate:[authorizationGuard], data:{role:"ADMIN"}},
      {path:"not-authorized", component:NotAuthorizedComponent}
    ]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
