import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {Transfer} from "../../modeles/transfer.model";
import {OperationService} from "../../services/operation.service";

@Component({
  selector: 'app-transfer',
  templateUrl: './transfer.component.html',
  styleUrls: ['./transfer.component.css']
})

export class TransferComponent implements OnInit, OnChanges {

  transferFormGroup!: FormGroup
  transfer!:Transfer
  @Input() account_number: string | undefined;

  constructor(private fb:FormBuilder, private operationService:OperationService) {
  }

  ngOnInit(): void {
      this.transferFormGroup = this.fb.group({
        accountNumberSource : this.fb.control(this.account_number),
        accountNumberDestination : this.fb.control(""),
        amount: this.fb.control("")
      })
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['account_number'] && !changes['account_number'].firstChange){
      this.transferFormGroup.patchValue({ accountNumberSource: this.account_number });
    }
  }

  handelTransfer() {
    this.transfer = this.transferFormGroup.value
    this.operationService.transfer(this.transfer).subscribe({
      next :data=>{
        alert('operation success')
      },
      error:err => {

      }
    })
  }
}
