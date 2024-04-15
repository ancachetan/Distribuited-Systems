import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';
import {Device} from "../../models/Device";
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {DeviceService} from "../../services/DeviceService";

@Component({
  selector: 'app-update-device-dialog',
  templateUrl: './update-device-dialog.component.html',
  styleUrls: ['./update-device-dialog.component.css']
})
export class UpdateDeviceDialogComponent implements OnInit {
  device: Device = new Device();
  usersUsernameList: string[] = []
  user: User = new User()
  username: string = ""

  constructor(
    private dialogRef: MatDialogRef<UpdateDeviceDialogComponent>,
    private userService: UserService,
    private deviceService: DeviceService,
    @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe((users) => {
      users.forEach((user) => {
        if (user.username != undefined) {
          this.usersUsernameList.push(user.username)
        }
      })
    })
    this.usersUsernameList.push("Free device")
  }

  onClickUpdate() {
    this.device.id = this.data.id
    if (this.user.id != undefined && this.username != "Free device") {
      this.device.holderId = this.user.id
    }
    if (this.user.id == undefined){
      this.device.holderId = this.data.holderId
    }
    if (this.username == "Free device"){
      this.device.holderId = undefined
    }
    console.log(this.device)
    this.deviceService.update(this.device).subscribe(() => {
      this.dialogRef.close();
    }, () => {
      alert("Wrong details. Please check the validation specifications!")
    })
  }

  selectedUser() {
    if (this.username != undefined && this.username !== "Free device") {
      this.userService.getByUsername(this.username).subscribe((user) => {
        this.user = user;
      })
    }
  }
}
