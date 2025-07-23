import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../modeles/customer.model";
import {catchError, Observable, throwError} from "rxjs";
import {NgForm} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit{

  customers!:Observable<Customer[]>;
  errorMessage!:string;
  constructor(private customerService:CustomerService, public authService:AuthService, private router:Router) {
  }

    ngOnInit(): void {
      this.allCustomer()
    }

  allCustomer(){
    this.customers = this.customerService.customers().pipe(
      catchError(err => {
        this.errorMessage = err.message;
        return throwError(err);
      })
    )
  }

  search(form: NgForm) {
    this.customers = this.customerService.getCustomerByName(form.value.keyword)
  }

  deleteCustomer(id: number) {
    let con = confirm("are you sure")
    if(!con) return
    this.customerService.deleteCustomer(id).subscribe({
      next: data=>{
      this.allCustomer()
      },
      error: err => {
        console.log(err)
      }
    })
  }

  updateCustomer(id: number) {
    this.router.navigate(['admin/update-customer', id]);
  }
}
