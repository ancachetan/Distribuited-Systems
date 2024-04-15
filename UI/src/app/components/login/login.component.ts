import {Component} from '@angular/core';
import {Router} from '@angular/router';
import jwtDecode from "jwt-decode";
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {Role} from "../../models/Role";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user: User = new User();
  token: any

  constructor(private userService: UserService,
              private router: Router) {
  }

  userLogin() {
    this.userService.login(this.user.username, this.user.password).subscribe((response: any) => {
      localStorage.setItem("token", response.token);
      this.token = localStorage.getItem("token")
      let tokenDecoded: any
      tokenDecoded = jwtDecode(this.token)
      const role = tokenDecoded.role;
      alert("Login successful!")

      if (role == Role.ADMIN) {
        this.router.navigateByUrl('/admin')
      } else {
        this.router.navigateByUrl('/client')
      }
    }, () => {
      alert("Incorrect email or password")
    })
  }

  userRegister() {
    this.router.navigateByUrl('/register')
  }
}
