import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Debit} from "../../modeles/debit.model";
import {Credit} from "../../modeles/credit.model";
import {OperationService} from "../../services/operation.service";

@Component({
  selector: 'app-credit',
  templateUrl: './credit.component.html',
  styleUrls: ['./credit.component.css']
})
export class CreditComponent implements OnInit, OnChanges{
  creditFormGroup!:FormGroup;
  credit!:Credit
  @Input() account_number: string | undefined;
  constructor(private fb:FormBuilder, private operationService:OperationService) {
  }
  ngOnInit(): void {
    this.creditFormGroup = this.fb.group({
      accountNumber: this.fb.control(this.account_number),
      amount: this.fb.control(""),
      description: this.fb.control("")
    })
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['account_number'] && !changes['account_number'].firstChange) {
      this.creditFormGroup.patchValue({ accountNumber: this.account_number });
    }
  }

  handelCredit() {
    this.credit = this.creditFormGroup.value
    this.operationService.debit(this.credit).subscribe({
      next :data=>{
        alert('operation success')
      },
      error:err => {

      }
    })
  }

}
