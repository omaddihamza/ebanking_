import {Component, OnInit} from '@angular/core';
import {Form, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../services/account.service";

@Component({
  selector: 'app-saving',
  templateUrl: './saving.component.html',
  styleUrls: ['./saving.component.css']
})
export class SavingComponent implements OnInit{
    savingAccountForm !:FormGroup

    constructor(private fb:FormBuilder, private serviceAccount :AccountService) {
    }
    ngOnInit(): void {
        this.savingAccountForm = this.fb.group({
          initialBalance: this.fb.control("",[Validators.required]) ,
          interestRate: this.fb.control("",[Validators.required]) ,
          customerId: this.fb.control("",[Validators.required])
        })
    }

  createSavingAccount(){
    if(this.savingAccountForm.invalid){
      return
    }

    const {initialBalance, interestRate, customerId } = this.savingAccountForm.value

    this.serviceAccount.createSavingAccount(initialBalance, interestRate, customerId).subscribe({
      next: response => {
        console.log("Account created successfully", response);

      },
      error: err => {
        console.error("Error creating account", err);
      }
    })
  }

}
