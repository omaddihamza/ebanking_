import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CustomerService} from "../../services/customer.service";
import {Router} from "@angular/router";
import {Customer} from "../../modeles/customer.model";

@Component({
  selector: 'app-new-customer',
  templateUrl: './new-customer.component.html',
  styleUrls: ['./new-customer.component.css']
})
export class NewCustomerComponent implements OnInit{

  customerFormGroup!: FormGroup
   constructor(private fb:FormBuilder, private customerService:CustomerService, private router:Router) {
   }

    ngOnInit(): void {
        this.customerFormGroup = this.fb.group({
          name:this.fb.control("", [Validators.required, Validators.minLength(5)]),
          email:this.fb.control("", [Validators.required, Validators.email])
        })
    }

  handleCustomer() {
   let customer:Customer = this.customerFormGroup.value
    this.customerService.saveCustomer(customer).subscribe({
      next: data=>{
        this.router.navigate(["/customers"]);
      },
      error: err => {
        console.log(err)
      }
    })
  }
}
