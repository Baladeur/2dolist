import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, ReplaySubject, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Authenticate } from '../models/dto/Authenticate';
import { TokensRequest } from '../models/dto/TokensRequest';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;
  private isLoggedInSubject: ReplaySubject<boolean> = new ReplaySubject<boolean>(1);

  constructor(private http: HttpClient, private router: Router, private snackBar: MatSnackBar) {
    this.hasToken().subscribe(isLoggedIn => {
      this.isLoggedInSubject.next(isLoggedIn);
    });
  }

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

  getRefreshTokenFromCookie(): string {
    const cookie = document.cookie.split(';').find(c => c.trim().startsWith('refreshToken='));
    return cookie ? cookie.split('=')[1] : '';
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

  private hasToken(): Observable<boolean> {

    const accessToken = localStorage.getItem('accessToken');
    if (!accessToken) {
      return of(false);
    }

    return this.updateAccessToken().pipe(
      map(response => {
        const tokenIsValid = !!response.accessToken;
        return tokenIsValid;
      }),
      catchError(error => {
        console.error(error);
        return of(false);
      })
    );
  }

  updateAccessToken(): Observable<any> {
    const accessToken = localStorage.getItem('accessToken');
    const body = new TokensRequest(accessToken, this.getRefreshTokenFromCookie(), localStorage.getItem('email'));

    return this.http.post<any>(`${this.apiUrl}/authentication/refresh`, body).pipe(
      tap(response => {
        if (response.success) {
          localStorage.setItem('accessToken', response.accessToken);
        } else {
          this.logout();
        }
      }),
      catchError(error => {
        console.error(error);
        this.snackBar.open('Error refreshing access token. Please log in again.', 'Fermer', {
          duration: 5000,
        });
        this.router.navigate(['/']);
        return of(false);
      })
    );
  }
}
