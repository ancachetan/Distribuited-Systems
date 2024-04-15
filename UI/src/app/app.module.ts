import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from "@angular/common/http";
import {MatListModule} from '@angular/material/list';
import {MatTableModule} from '@angular/material/table';
import {MatDialogModule} from "@angular/material/dialog";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {MatRadioModule} from "@angular/material/radio";
import {MatMenuModule} from '@angular/material/menu';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatIconModule} from "@angular/material/icon";
import {CdkMenuModule} from '@angular/cdk/menu'


import {AppComponent} from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {HomePageComponent} from './components/home-page/home-page.component';
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {AdminFirstPageComponent} from './components/admin-first-page/admin-first-page.component';
import {ClientFirstPageComponent} from './components/client-first-page/client-first-page.component';
import {DeleteUserDialogComponent} from './components/delete-user-dialog/delete-user-dialog.component';
import {UpdateUserDialogComponent} from './components/update-user-dialog/update-user-dialog.component';
import {SaveUserDialogComponent} from './components/save-user-dialog/save-user-dialog.component';
import {AdminEditDevicePageComponent} from './components/admin-edit-device-page/admin-edit-device-page.component';
import {DeleteDeviceDialogComponent} from './components/delete-device-dialog/delete-device-dialog.component';
import {SaveDeviceDialogComponent} from './components/save-device-dialog/save-device-dialog.component';
import {UpdateDeviceDialogComponent} from './components/update-device-dialog/update-device-dialog.component';
import {AdminEditUserPageComponent} from "./components/admin-edit-user-page/admin-edit-user-page.component";
import {ClientViewPageComponent} from './components/client-view-page/client-view-page.component';
import {ClientViewChartPageComponent} from "./components/client-view-chart-page/client-view-chart-page.component";
import {CanvasJSAngularChartsModule} from "@canvasjs/angular-charts";
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatNativeDateModule} from "@angular/material/core";
import {MatInputModule} from "@angular/material/input";
import {ChatPageComponent} from "./components/chat-page/chat-page.component";
import {ChatDialogComponent} from "./components/chat-dialog/chat-dialog.component";
import {MatSnackBar} from "@angular/material/snack-bar";

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    LoginComponent,
    RegistrationComponent,
    AdminFirstPageComponent,
    ClientFirstPageComponent,
    AdminEditUserPageComponent,
    DeleteUserDialogComponent,
    UpdateUserDialogComponent,
    SaveUserDialogComponent,
    AdminEditDevicePageComponent,
    DeleteDeviceDialogComponent,
    SaveDeviceDialogComponent,
    UpdateDeviceDialogComponent,
    ClientViewPageComponent,
    ClientViewChartPageComponent,
    ChatPageComponent,
    ChatDialogComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    MatTableModule,
    MatDialogModule,
    MatToolbarModule,
    MatCardModule,
    MatFormFieldModule,
    MatListModule,
    MatCardModule,
    MatButtonModule,
    MatRadioModule,
    MatMenuModule,
    BrowserAnimationsModule,
    MatIconModule,
    CdkMenuModule,
    MatSelectModule,
    CanvasJSAngularChartsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatInputModule,
  ],
  providers: [MatSnackBar],
  bootstrap: [AppComponent]
})
export class AppModule {
}
