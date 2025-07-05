import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Debit} from "../../modeles/debit.model";
import {OperationService} from "../../services/operation.service";

@Component({
  selector: 'app-debit',
  templateUrl: './debit.component.html',
  styleUrls: ['./debit.component.css']
})
export class DebitComponent implements OnInit, OnChanges{

  debitFormGroup!:FormGroup;
  debit!:Debit
  @Input() account_number: string | undefined;

  constructor(private fb:FormBuilder, private operationService:OperationService) {
  }
  ngOnInit(): void {
      this.debitFormGroup = this.fb.group({
        accountNumber: this.fb.control(this.account_number),
        amount: this.fb.control(""),
        description: this.fb.control("")
      })
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['account_number'] && !changes['account_number'].firstChange) {
      this.debitFormGroup.patchValue({ accountNumber: this.account_number });
    }
  }

  handelDebit() {
    this.debit = this.debitFormGroup.value
    this.operationService.debit(this.debit).subscribe({
      next :data=>{
        alert('operation success')
      },
      error:err => {

      }
    })
  }
}
