import { Injectable } from '@angular/core';
import {environment} from "../../environments/environment.development";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../modeles/customer.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  url= environment.path
  constructor(private http:HttpClient) {}
  customers():Observable<Customer[]>{
       return this.http.get<Customer[]>(`${this.url}/customers`)
  }

  getCustomerByName(name:string):Observable<any>{
    return this.http.get(`${this.url}/customers/search?keyword=${name}`)
  }

  saveCustomer(customer:Customer):Observable<Customer>{
     return this.http.post<Customer>(`${this.url}/customer`,customer)
  }

  deleteCustomer(id: number):Observable<any>{
    return this.http.delete(`${this.url}/deletecustomer/${id}`)
  }
}
