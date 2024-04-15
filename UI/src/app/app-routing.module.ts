import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {HomePageComponent} from "./components/home-page/home-page.component";
import {LoginComponent} from './components/login/login.component';
import {RegistrationComponent} from './components/registration/registration.component';
import {AdminFirstPageComponent} from './components/admin-first-page/admin-first-page.component';
import {ClientFirstPageComponent} from './components/client-first-page/client-first-page.component';
import {AdminEditDevicePageComponent} from './components/admin-edit-device-page/admin-edit-device-page.component';
import {AdminEditUserPageComponent} from "./components/admin-edit-user-page/admin-edit-user-page.component";
import {ClientViewPageComponent} from './components/client-view-page/client-view-page.component';
import {AuthGuardService} from "./guards/auth-guard.service";
import {ClientViewChartPageComponent} from "./components/client-view-chart-page/client-view-chart-page.component";
import {ChatPageComponent} from "./components/chat-page/chat-page.component";

const routes: Routes = [
  {path: 'home', component: HomePageComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegistrationComponent},
  {path: 'admin', component: AdminFirstPageComponent, canActivate: [AuthGuardService]},
  {path: 'client', component: ClientFirstPageComponent, canActivate: [AuthGuardService]},
  {path: 'admin/users', component: AdminEditUserPageComponent, canActivate: [AuthGuardService]},
  {path: 'admin/devices', component: AdminEditDevicePageComponent, canActivate: [AuthGuardService]},
  {path: 'client/devices', component: ClientViewPageComponent, canActivate: [AuthGuardService]},
  {path: 'client/charts', component: ClientViewChartPageComponent, canActivate: [AuthGuardService]},
  {path: 'chat', component: ChatPageComponent},
  {path: '', component: HomePageComponent},
  {path: '**', component: HomePageComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
