import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomePageComponent } from './home-page/home-page.component';
import { FooterComponent } from './footer/footer.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginModalComponent } from './login-modal/login-modal.component';
import { SignUpModalComponent } from './sign-up-modal/sign-up-modal.component';
import { UserPageComponent } from './user-page/user-page.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { AddWorkspaceComponent } from './add-workspace/add-workspace.component';
import { AddWorkspaceDialogComponent } from './add-workspace-dialog/add-workspace-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    FooterComponent,
    NavbarComponent,
    LoginModalComponent,
    SignUpModalComponent,
    UserPageComponent,
    SidebarComponent,
    AddWorkspaceComponent,
    AddWorkspaceDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
