import {Component, OnInit} from '@angular/core';
import {ProfileService} from "../../services/profile.service";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

// @ts-ignore
@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit{
profile :any;
    constructor(private profileService:ProfileService, private auth:AuthService, private router:Router) {
    }
    ngOnInit(): void {
      const username = this.auth?.username;
      this.profileService.getUserProfile(username).subscribe({
        next:data=>{
          this.profile = data
          console.log(data)
        },
        error:err=>{
          console.error('Error fetching profile:', err);
        }
      })
    }

  changePassword() {
    this.router.navigateByUrl('admin/profile/change-password');
  }
}
