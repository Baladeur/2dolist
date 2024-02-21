import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisterEmail } from '../models/dto/RegisterEmail';
import { RegisterPassword } from '../models/dto/RegisterPassword';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private apiUrl = environment.apiUrl;
  private registrationToken: string | null = null;

  constructor(private http: HttpClient) { }

  registerEmail(body: RegisterEmail): Observable<any> {
    body.frontUrl = `http://${window.location.host}/registration/verify/`;
    return this.http.post<any>(`${this.apiUrl}/registration`, body, { withCredentials: true });
  }

  verifyRegistrationToken(registrationToken: string | null): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/registration/verify`, { registrationToken });
  }

  registerEmailAndPassword(body: RegisterPassword): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/registration/complete`, body);
  }

  setRegistrationToken(token: string | null): void {
    if (token === null) {
      return;
    }
    this.registrationToken = token;
    localStorage.setItem(this.registrationToken, token);
  }

  getRegistrationToken(): string {
    if (this.registrationToken) {
      return this.registrationToken;
    }
    const token = localStorage.getItem('registrationToken');
    if (token) {
      this.registrationToken = token;
      return token;
    }
    return '';
  }

}
