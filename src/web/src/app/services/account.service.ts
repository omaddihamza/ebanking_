import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
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
}
