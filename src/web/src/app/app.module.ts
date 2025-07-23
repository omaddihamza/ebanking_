import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { CustomersComponent } from './components/customers/customers.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { NewCustomerComponent } from './components/new-customer/new-customer.component';
import { DebitComponent } from './components/debit/debit.component';
import { CreditComponent } from './components/credit/credit.component';
import { TransferComponent } from './components/transfer/transfer.component';
import { SavingComponent } from './components/saving/saving.component';
import { CurrentComponent } from './components/current/current.component';
import { LoginComponent } from './components/login/login.component';
import { AdminTemplateComponent } from './components/admin-template/admin-template.component';
import {AppHttpInterceptor} from "./interceptors/app-http.interceptor";
import { NotAuthorizedComponent } from './components/not-authorized/not-authorized.component';
import { UpdateCustomerComponent } from './components/update-customer/update-customer.component';
import { ListAccountsComponent } from './components/list-accounts/list-accounts.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ChangePasswordComponent } from './components/change-password/change-password.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    CustomersComponent,
    AccountsComponent,
    NewCustomerComponent,
    DebitComponent,
    CreditComponent,
    TransferComponent,
    SavingComponent,
    CurrentComponent,
    LoginComponent,
    AdminTemplateComponent,
    NotAuthorizedComponent,
    UpdateCustomerComponent,
    ListAccountsComponent,
    ProfileComponent,
    ChangePasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule
  ],
  providers: [
    {provide:HTTP_INTERCEPTORS, useClass: AppHttpInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
