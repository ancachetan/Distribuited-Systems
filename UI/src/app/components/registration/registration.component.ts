import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {Role} from "../../models/Role";
import {HttpErrorResponse} from "@angular/common/http";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent {
  user: User = new User();
  valid: boolean = true

  constructor(private userService: UserService, private router: Router) {

  }

  register() {
    const emailRegex = new RegExp('^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,5}\\.[A-Za-z]{2,4}$');
    const passwordRegex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!@#$%^&*.]).{7,}$');
    this.valid = true
    if (this.user.name == undefined || this.user.name == "") {
      this.valid = false
      alert("Name is mandatory")
    }
    if (!emailRegex.test(<string>this.user.username)) {
      this.valid = false
      alert("Check the correct format for email")
    }
    if (!passwordRegex.test(<string>this.user.password)) {
      this.valid = false
      alert("Check the correct format for password")
    }

    this.user.role = Role.CLIENT
    if (this.valid) {
      if (this.user.username != null) {
        this.userService.register(this.user).subscribe(() => {
          alert("Registration successfully")
          this.router.navigateByUrl('/login')
        }, (response: HttpErrorResponse) => {
          alert(response.error.message())
        })
      }
    }
  }
}
