import {Component, OnInit} from '@angular/core';
import {AuthService} from "../../services/auth.service";


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit{

  constructor(public authservice: AuthService) {
  }

  ngOnInit(): void {

    }

  logout() {
      this.authservice.logout()
  }
}
