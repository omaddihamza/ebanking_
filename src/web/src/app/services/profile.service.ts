import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../environments/environment.development";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  url= environment.path
  constructor(private http:HttpClient) { }

  getUserProfile(username: string): Observable<any> {
    const params = new HttpParams().set('username', username);
    return this.http.get(`${this.url}/profile`, { params });
  }

  updatePassword(data: { currentPassword: string; newPassword: string }) {
    return this.http.post<{ message: string }>(
      `http://localhost:8080/auth/update-password`,
      data
    );
  }
}
