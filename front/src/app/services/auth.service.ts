import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Authenticate } from '../models/dto/Authenticate';
import { TokensRequest } from '../models/dto/TokensRequest';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private isLoggedInSubject = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {}

  login(body: Authenticate): Observable<any> {
    return this.http.post<TokensRequest>(`${this.apiUrl}/authentication`, body).pipe(
      tap(tokens => {
        const { accessToken, refreshToken, email } = tokens;
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('email', email);
        this.saveRefreshTokenToCookie(refreshToken);
        this.isLoggedInSubject.next(true);
        this.router.navigate(['/user-page']);
      })
    );
  }

  private saveRefreshTokenToCookie(refreshToken: string): void {
    document.cookie = `refreshToken=${refreshToken};`;
  }

  getAccessToken(): string | null {
    return localStorage.getItem('accessToken');
  }

  getRefreshTokenFromCookie(): string | null {
    const cookie = document.cookie.split(';').find(c => c.trim().startsWith('refreshToken='));
    return cookie ? cookie.split('=')[1] : null;
  }

  isLoggedIn(): Observable<boolean> {
    return this.isLoggedInSubject.asObservable();
  }

  logout(): void {
    localStorage.removeItem('accessToken');
    localStorage.removeItem('email');
    document.cookie = 'refreshToken=;';
    this.isLoggedInSubject.next(false);
    this.router.navigate(['/']);
  }
}
