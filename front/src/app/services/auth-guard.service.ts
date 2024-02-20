import { Injectable, InjectionToken } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

export const AUTH_GUARD = new InjectionToken<((route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree)>('AUTH_GUARD');

export function canActivateFactory(authService: AuthService): (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
  return (route: ActivatedRouteSnapshot, state: RouterStateSnapshot) => {
    const isLoggedIn = authService.isLoggedIn();
    return isLoggedIn;
  };
}

@Injectable({
  providedIn: 'root',
  useFactory: canActivateFactory,
  deps: [AuthService]
})
export class AuthGuardService {
}