import {Component, OnInit} from '@angular/core';
import {MatDialog} from '@angular/material/dialog';
import {DeleteDeviceDialogComponent} from '../delete-device-dialog/delete-device-dialog.component';
import {SaveDeviceDialogComponent} from '../save-device-dialog/save-device-dialog.component';
import {UpdateDeviceDialogComponent} from '../update-device-dialog/update-device-dialog.component';
import {Device} from "../../models/Device";
import {DeviceService} from "../../services/DeviceService";
import {UserService} from "../../services/UserService";

@Component({
  selector: 'app-admin-edit-device-page',
  templateUrl: './admin-edit-device-page.component.html',
  styleUrls: ['./admin-edit-device-page.component.css']
})
export class AdminEditDevicePageComponent implements OnInit {
  filterName: any
  deviceList: Device[] = [];
  displayedColumns: string[] = ['ID', 'Description', 'Address', 'Maximum hourly energy consumption', 'Holder', 'Actions']
  criteria: string = ""

  constructor(private deviceService: DeviceService,
              private userService: UserService,
              private dialog: MatDialog) {
  }

  ngOnInit(): void {
    this.deviceService.getAll().subscribe((data) => {
        this.deviceList = data
      },
      (_error) => {
        alert("No devices available now")
      });
  }

  openSaveDialog() {
    const dialogRef = this.dialog.open(SaveDeviceDialogComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(() => {
      this.deviceService.getAll().subscribe((data) => {
        this.deviceList = data
      })
    })
  }

  openUpdateDialog(device: Device) {
    const dialogRef = this.dialog.open(UpdateDeviceDialogComponent, {
      width: '500px',
      data: device
    });

    dialogRef.afterClosed().subscribe(() => {
      this.deviceService.getAll().subscribe((data) => {
        this.deviceList = data
      })
    })
  }

  openDeleteDialog(id: string) {
    const dialogRef = this.dialog.open(DeleteDeviceDialogComponent, {
      width: '250px',
      data: {id},
    });

    dialogRef.afterClosed().subscribe((result) => {
      if (result) {
        this.deviceList = this.deviceList.filter(
          (_) => _.id !== id
        );
      }
    });
  }

  onSearch() {
    if (this.criteria == "id") {
      this.deviceService.getById(this.filterName).subscribe((data) => {
        this.deviceList = []
        this.deviceList.push(data)
      });
    } else if (this.criteria == "description") {
      this.deviceService.getAllByDescription(this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else if (this.criteria == "address") {
      this.deviceService.getAllByAddress(this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else if (this.criteria == "holderId") {
      this.deviceService.getAllByHolderId(this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else if (this.criteria == "maxEnergyConsumption") {
      this.deviceService.getAllByMaxConsumption(this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else {
      this.deviceService.getAll().subscribe((data) => {
        this.deviceList = data
      });
    }
  }

}
