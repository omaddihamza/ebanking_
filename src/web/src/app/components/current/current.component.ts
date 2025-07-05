import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../../services/account.service";

@Component({
  selector: 'app-current',
  templateUrl: './current.component.html',
  styleUrls: ['./current.component.css']
})
export class CurrentComponent implements OnInit{
  currentAccountForm !:FormGroup

  constructor(private fb: FormBuilder, private accountService: AccountService) {}

  ngOnInit(): void {
     this.currentAccountForm =   this.fb.group({
      initialBalance: this.fb.control("",[Validators.required]) ,
      overDraft: this.fb.control("",[Validators.required]) ,
      customerId: this.fb.control("",[Validators.required])
     });
    }

  createCurrentAccount() {
    if (this.currentAccountForm.invalid) {
      return;
    }

    const { initialBalance, overDraft, customerId } = this.currentAccountForm.value;

    this.accountService.createCurrentAccount(initialBalance, overDraft, customerId)
      .subscribe({
        next: response => {
          console.log("Account created successfully", response);

        },
        error: err => {
          console.error("Error creating account", err);
        }
      });
  }

}
