import {Component, OnInit} from '@angular/core';
import {AccountService} from "../../services/account.service";

@Component({
  selector: 'app-list-accounts',
  templateUrl: './list-accounts.component.html',
  styleUrls: ['./list-accounts.component.css']
})
export class ListAccountsComponent implements OnInit{
  bankaccounts :any;

  constructor(private accountService: AccountService) {
  }
  ngOnInit(): void {
    this.accountService.getAccounts().subscribe({
      next:data =>{
        this.bankaccounts  = data
          console.log(data)
      },
      error:err=>{

      }
    })
  }

}
