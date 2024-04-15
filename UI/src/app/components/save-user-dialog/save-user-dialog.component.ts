import {Component} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {MatRadioChange} from '@angular/material/radio';
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {Role} from "../../models/Role";

@Component({
  selector: 'app-save-user-dialog',
  templateUrl: './save-user-dialog.component.html',
  styleUrls: ['./save-user-dialog.component.css']
})
export class SaveUserDialogComponent {
  user: User = new User();
  valid: boolean = true

  constructor(
    private dialogRef: MatDialogRef<SaveUserDialogComponent>,
    private userService: UserService) {
  }

  onClickSave() {
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

    this.userService.save(this.user).subscribe(() => {
      this.dialogRef.close();
    })
  }

  onRoleChange(role: MatRadioChange) {
    if (role.value == 'ADMIN') {
      this.user.role = Role.ADMIN
    } else {
      this.user.role = Role.CLIENT
    }
  }
}
