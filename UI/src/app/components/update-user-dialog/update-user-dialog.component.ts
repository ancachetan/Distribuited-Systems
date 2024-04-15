import {Component, Inject} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";

@Component({
  selector: 'app-update-user-dialog',
  templateUrl: './update-user-dialog.component.html',
  styleUrls: ['./update-user-dialog.component.css']
})
export class UpdateUserDialogComponent {
  user: User = new User();
  valid: boolean = true

  constructor(
    private dialogRef: MatDialogRef<UpdateUserDialogComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  onClickUpdate() {
    const emailRegex = new RegExp('^[A-Za-z0-9+_.-]+@[A-Za-z0-9]{2,5}\\.[A-Za-z]{2,4}$');
    const passwordRegex = new RegExp('^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[!@#$%^&*.]).{7,}$');
    this.valid = true

    if (this.user.username != undefined && !emailRegex.test(<string>this.user.username)) {
      this.valid = false
      alert("Check the correct format for email")
    }
    if (this.user.password != undefined && !passwordRegex.test(<string>this.user.password)) {
      this.valid = false
      alert("Check the correct format for password")
    }

    this.user.id = this.data.id
    this.user.role = this.data.role
    this.userService.update(this.user).subscribe(() => {
      this.dialogRef.close();
    })
  }
}
