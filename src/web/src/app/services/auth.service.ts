import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {jwtDecode} from "jwt-decode";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isAuthenticated: boolean = false;
  roles : any;
  username:any;
  accessToken : any;

  constructor(private http:HttpClient, private router:Router ) { }

  public login(username: string, password: string) {
    const body = new URLSearchParams();
    body.set('username', username);
    body.set('password', password);

    const headers = new HttpHeaders({
      'Content-Type': 'application/x-www-form-urlencoded'
    });

    return this.http.post("http://localhost:8080/auth/login", body.toString(), { headers });
  }

  loadProfile(data: any) {
    this.isAuthenticated = true;
    this.accessToken = data["access-token"];
    const decodedToken: any = jwtDecode(this.accessToken);
    this.username = decodedToken.sub;
    this.roles = decodedToken.scope;
    window.localStorage.setItem("jwt-token", this.accessToken)
  }

  logout() {
    this.isAuthenticated = false;
    this.roles = undefined;
    this.username = undefined;
    this.accessToken = undefined;
    window.localStorage.removeItem("jwt-token");
    this.router.navigateByUrl('/login'); // Assure-toi que /login existe
  }

  loadJwtTokenFromLocalStorage() {
   let token = window.localStorage.getItem("jwt-token")
    if(token){
      this.loadProfile({"access-token": token})
      this.router.navigateByUrl("/admin/customers")
    }
  }
}
