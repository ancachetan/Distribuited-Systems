import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {DeleteUserDialogComponent} from '../delete-user-dialog/delete-user-dialog.component';
import {SaveUserDialogComponent} from '../save-user-dialog/save-user-dialog.component';
import {UpdateUserDialogComponent} from '../update-user-dialog/update-user-dialog.component';
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {DeviceService} from "../../services/DeviceService";

@Component({
  selector: 'app-admin-edit-page',
  templateUrl: './admin-edit-user-page.component.html',
  styleUrls: ['./admin-edit-user-page.component.css']
})
export class AdminEditUserPageComponent implements OnInit {
  filterName: any
  usersList: User[] = [];
  displayedColumns: string[] = ['ID', 'Name', 'Username', 'Password', 'Role', 'Actions']
  criteria: string = ""

  constructor(private userService: UserService,
              private deviceService: DeviceService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.userService.getAll().subscribe((data) => {
        this.usersList = data
      },
      (_error) => {
        alert("No users available now")
      })
  }

  openSaveDialog() {
    const dialogRef = this.dialog.open(SaveUserDialogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(() => {
      this.userService.getAll().subscribe((data) => {
        this.usersList = data
      })
    })
  }

  openUpdateDialog(user: User) {
    const dialogRef = this.dialog.open(UpdateUserDialogComponent, {
      width: '500px',
      data: user
    })

    dialogRef.afterClosed().subscribe(() => {
      this.userService.getAll().subscribe((data) => {
        this.usersList = data
      })
    })
  }

  openDeleteDialog(id: string) {
    const dialogRef = this.dialog.open(DeleteUserDialogComponent, {
      width: '250px',
      data: {id},
    });

    this.deviceService.getAllByHolderId(id).subscribe((devices) => {
      for (let device of devices) {
        device.holderId = undefined
        this.deviceService.update(device).subscribe()
      }
    })

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.usersList = this.usersList.filter(
          (_) => _.id !== id
        )
      }
    })
  }

  onSearch() {
    if (this.criteria == "id") {
      this.userService.getById(this.filterName).subscribe((data) => {
        this.usersList = []
        this.usersList.push(data)
      })
    } else if (this.criteria == "name") {
      this.userService.getAllByName(this.filterName).subscribe((data) => {
        this.usersList = data
      })
    } else if (this.criteria == "username") {
      this.userService.getByUsername(this.filterName).subscribe((data) => {
        this.usersList = []
        this.usersList.push(data)
      })
    } else if (this.criteria == "role") {
      this.userService.getAllByRole(this.filterName).subscribe((data) => {
        this.usersList = data
      })
    } else {
      this.userService.getAll().subscribe((data) => {
        this.usersList = data
      })
    }
  }
}
