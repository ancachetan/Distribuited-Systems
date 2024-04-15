import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import jwtDecode from "jwt-decode";
import {Role} from "../models/Role";

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
    routeURL: any;
    token: any

    constructor(private router: Router) {

    }

    canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> {
        return new Promise((resolve) => {
            this.routeURL = state.url;
            if (localStorage.getItem("token") == null && this.routeURL !== '/login') {
                this.routeURL = 'login';
                this.router.navigate(['/login'], {
                    queryParams: {
                        return: 'login'
                    }
                });
                return resolve(false);
            } else if (localStorage.getItem("token") != null) {
                this.token = localStorage.getItem("token")
                let tokenDecoded: any
                tokenDecoded = jwtDecode(this.token)
                const role = tokenDecoded.role;

                if (role == Role.ADMIN && (this.routeURL === "/client" || this.routeURL === "/client/devices" || this.routeURL === "/client/charts")) {
                    this.routeURL = 'admin';
                    this.router.navigate(['/admin'], {
                        queryParams: {
                            return: 'admin'
                        }
                    });
                    return resolve(false);
                } else if (role == Role.ADMIN) {
                    console.log(this.routeURL)
                    this.routeURL = this.router.url;
                    return resolve(true);
                } else if (role == Role.CLIENT && (this.routeURL === "/admin" || this.routeURL === "/admin/users" || this.routeURL === "/admin/devices")) {
                    this.routeURL = 'client';
                    this.router.navigate(['/client'], {
                        queryParams: {
                            return: 'client'
                        }
                    });
                    return resolve(false);
                } else if (role == Role.CLIENT) {
                    this.routeURL = this.router.url;
                    return resolve(true);
                }
            } else {
                this.routeURL = this.router.url;
                return resolve(true);
            }
        })
    }
}
