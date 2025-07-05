import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../environments/environment.development";
import {Observable} from "rxjs";
import {Account} from "../modeles/account.model";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  url= environment.path
  constructor(private http :HttpClient) { }

   searchAccount(account:string, page:number, size:number):Observable<Account>{
      return this.http.get<Account>(`${this.url}/operation/${account}/pageOperation?page=${page}&size=${size}`)
   }

  createCurrentAccount(initialBalance: number, overDraft: number, customerId: number): Observable<any> {
    const params = new HttpParams()
      .set('initialBalance', initialBalance.toString())
      .set('overDraft', overDraft.toString())
      .set('customerId', customerId.toString());

    return this.http.post(`${this.url}/current`, {}, { params });
  }

  createSavingAccount(initialBalance: number, interestRate: number, customerId: number): Observable<any> {
    const params = new HttpParams()
      .set('initialBalance', initialBalance.toString())
      .set('interestRate', interestRate.toString())
      .set('customerId', customerId.toString());

    return this.http.post(`${this.url}/saving`, {}, { params });
  }


}
