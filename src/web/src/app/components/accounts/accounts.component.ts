import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountService} from "../../services/account.service";
import {Account} from "../../modeles/account.model";

@Component({
  selector: 'app-accounts',
  templateUrl: './accounts.component.html',
  styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit{
    accountFormGroup !: FormGroup
    account: Account | null = null;
    page:number =0
    size:number =5
    totalPages: number = 0;
  constructor(private fb:FormBuilder, private accountService:AccountService) {
    }
    ngOnInit(): void {
      this.accountFormGroup = this.fb.group({
        accountNumber:this.fb.control('434860225747688612179348')
      })
    }

  handleSearchAccount() {
    let accountNumber = this.accountFormGroup.value.accountNumber;
    this.accountService.searchAccount(accountNumber,this.page,this.size).subscribe({
      next:(data:Account)=>{
        this.account=data
        console.log(  this.account)
        this.totalPages = data.totalPage;
      },
      error:err => {
        console.error('Error searching account:', err);
        this.account = null
      }
    })
  }
  goToPage(page: number) {
    if (page >= 1 && page <= this.totalPages) {
      this.page = page;
      this.handleSearchAccount(); // Fetch new page data
    }
  }
}
