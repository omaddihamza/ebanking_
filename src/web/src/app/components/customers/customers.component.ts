import {Component, OnInit} from '@angular/core';
import {CustomerService} from "../../services/customer.service";
import {Customer} from "../../modeles/customer.model";
import {catchError, Observable, throwError} from "rxjs";
import {NgForm} from "@angular/forms";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})
export class CustomersComponent implements OnInit{

  customers!:Observable<Customer[]>;
  errorMessage!:string;
  constructor(private customerService:CustomerService) {
  }

    ngOnInit(): void {
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

  deleteCustomr(id: number) {
    let con = confirm("are you sure")
    if(!con) return
    this.customerService.deleteCustomer(id).subscribe({
      next: data=>{

      },
      error: err => {
        console.log(err)
      }
    })
  }
}
