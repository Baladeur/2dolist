import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { Authenticate } from '../models/dto/Authenticate';
import { TokensRequest } from '../models/dto/TokensRequest';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  login(body: Authenticate): Observable<any> {
    return this.http.post<TokensRequest>(`${this.apiUrl}/authentication`, body).pipe(
      tap(tokens => {
        const { accessToken, refreshToken, email } = tokens;
        localStorage.setItem('accessToken', accessToken);
        localStorage.setItem('email', email);
        this.saveRefreshTokenToCookie(refreshToken);
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
}
