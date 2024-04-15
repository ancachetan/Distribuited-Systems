import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material/dialog';
import {User} from "../../models/User";
import {Device} from "../../models/Device";
import {DeviceService} from "../../services/DeviceService";
import {UserService} from "../../services/UserService";

@Component({
  selector: 'app-save-device-dialog',
  templateUrl: './save-device-dialog.component.html',
  styleUrls: ['./save-device-dialog.component.css']
})
export class SaveDeviceDialogComponent implements OnInit {
  device: Device = new Device()
  usersUsernameList: string[] = []
  user: User = new User()
  username: string = ""

  constructor(
    private dialogRef: MatDialogRef<SaveDeviceDialogComponent>,
    private deviceService: DeviceService,
    private userService: UserService) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe((users) => {
      users.forEach((user) => {
        if (user.username != undefined) {
          this.usersUsernameList.push(user.username)
        }
      })
    })
  }

  onClickSave() {
    console.log(this.usersUsernameList)
    this.device.holderId = this.user.id
    this.deviceService.save(this.device).subscribe(() => {
      this.dialogRef.close();
    }, () => {
      alert("Wrong details. Please check the validation specifications!")
    });
  }

  selectedUser() {
    this.userService.getByUsername(this.username).subscribe((user) => {
      this.user = user
    })
  }
}
