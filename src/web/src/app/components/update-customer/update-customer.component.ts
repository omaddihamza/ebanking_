import { Component, OnInit } from '@angular/core';
import { CustomerService } from '../../services/customer.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Customer } from '../../modeles/customer.model';

@Component({
  selector: 'app-update-customer',
  templateUrl: './update-customer.component.html',
  styleUrls: ['./update-customer.component.css']
})
export class UpdateCustomerComponent implements OnInit {
  customer!: Customer;
  editCustomer!: FormGroup;
  isLoading = true;

  constructor(
    private customerService: CustomerService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      this.customerService.getCustomer(id).subscribe((customer:any) => {
        this.customer = customer;


        this.editCustomer = this.fb.group({
          name: [customer.name, Validators.required],
          email: [customer.email, [Validators.required, Validators.email]],
          // Add more fields if needed
        });

        this.isLoading = false;
      });
    }
  }

  onSubmit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (this.editCustomer.valid) {
      this.customerService.updateCustomer(id, this.editCustomer.value).subscribe(() => {
        alert('Customer updated successfully');
        this.router.navigate(['/admin/customers']); // Redirect after update
      });
    }
  }
}
