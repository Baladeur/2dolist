import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserPageComponent } from './user-page/user-page.component';
import { HomePageComponent } from './home-page/home-page.component';
import { VerificationComponent } from './verification/verification.component';
import { AuthGuardService } from './services/auth-guard.service';

const routes: Routes = [
  { path: '', component: HomePageComponent },
  { path: 'user-page', component: UserPageComponent, canActivate: [AuthGuardService]},
  { path: 'registration/verify/:token', component: VerificationComponent },
  { path: '**', redirectTo: '', pathMatch: 'full' }


]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }