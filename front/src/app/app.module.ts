import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatInputModule } from '@angular/material/input';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatDialogModule } from '@angular/material/dialog';

import { FlexLayoutModule } from '@angular/flex-layout';
import { FlexLayoutServerModule } from '@angular/flex-layout/server';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import { SignUpModalComponent } from './sign-up-modal/sign-up-modal.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { LoginModalComponent } from './login-modal/login-modal.component';
import { UserPageComponent } from './user-page/user-page.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AddWorkspaceDialogComponent } from './add-workspace-dialog/add-workspace-dialog.component';
import { RegisterEmailComponent } from './register-email/register-email.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent,
    FooterComponent,
    SignUpModalComponent,
    LoginModalComponent,
    UserPageComponent,
    SidebarComponent,
    AddWorkspaceDialogComponent,
    RegisterEmailComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,

    MatToolbarModule,
    MatButtonModule,
    MatIconModule,
    MatInputModule,
    MatDialogModule,
    MatSidenavModule,
    MatListModule,

    FlexLayoutModule,
    FlexLayoutServerModule,
    HttpClientModule,
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
