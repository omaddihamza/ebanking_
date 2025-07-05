import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../environments/environment.development";
import {Observable} from "rxjs";
import {Debit} from "../modeles/debit.model";
import {Credit} from "../modeles/credit.model";

@Injectable({
  providedIn: 'root'
})
export class OperationService {
  url= environment.path
  constructor(private http:HttpClient) { }

  debit(debit :Debit):Observable<any>{
     return this.http.post(`${this.url}/account/debit`, debit);
  }

  credit(credit: Credit):Observable<any>{
    return this.http.post(`${this.url}/account/credit`, credit);
  }

  transfer(transfer:any):Observable<any>{
    return this.http.post(`${this.url}/account/transfer`, transfer);
  }
}
