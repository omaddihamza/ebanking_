import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../services/profile.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit{

  passwordData = {
    oldPassword: '',
    newPassword: ''
  };

  constructor(private profileServise :ProfileService, private router:Router) {}
  ngOnInit(): void {

  }


  onChangePassword() {
    this.profileServise.updatePassword({
      currentPassword: this.passwordData.oldPassword,
      newPassword: this.passwordData.newPassword
    }).subscribe({
      next: res => {
        alert(res.message)
        this.router.navigateByUrl("/admin/profile")
      },
      error: err => alert('Password update failed: ' + err.error.message)
    });
    console.log('Password update request:', this.passwordData);
  }

}
