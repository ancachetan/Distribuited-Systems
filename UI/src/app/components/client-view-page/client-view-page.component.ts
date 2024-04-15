import {Component, OnInit} from '@angular/core';
import {Device} from "../../models/Device";
import {DeviceService} from "../../services/DeviceService";
import {MatDialog} from "@angular/material/dialog";
import jwtDecode from "jwt-decode";
import {WebSocketService} from "../../services/WebSocketService";
import {TokenDecoderService} from "../../services/TokenDecoderService";

@Component({
  selector: 'app-client-view-page',
  templateUrl: './client-view-page.component.html',
  styleUrls: ['./client-view-page.component.css']
})
export class ClientViewPageComponent implements OnInit {
  filterName: any
  deviceList: Device[] = [];
  displayedColumns: string[] = ['ID', 'Description', 'Address', 'Maximum hourly energy consumption']
  criteria: string = ""
  token: any;

  constructor(private deviceService: DeviceService,
              private dialog: MatDialog,
              private webSocketService: WebSocketService,
              private tokenService: TokenDecoderService) {
  }

  ngOnInit(): void {
    const id = this.tokenService.getIdFromToken();
    this.webSocketService.subscribeToNotifications().subscribe((message) => {
      console.log(id)
      console.log(message.message)
      if (message.id == id) {
        alert(message.message)
      }
    });

    this.deviceService.getAllByHolderId(id).subscribe((data) => {
        this.deviceList = data
      },
      (_error) => {
        alert("No devices available now")
      });
  }

  onSearch() {
    this.token = localStorage.getItem("token")
    let tokenDecoded: any
    tokenDecoded = jwtDecode(this.token)
    const id = tokenDecoded.id;

    if (this.criteria == "description") {
      this.deviceService.getAllByHolderIdAndDescription(id, this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else if (this.criteria == "address") {
      this.deviceService.getAllByHolderIdAndAddress(id, this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else if (this.criteria == "maxEnergyConsumption") {
      this.deviceService.getAllByHolderIdAndMaxEnergyConsumption(id, this.filterName).subscribe((data) => {
        this.deviceList = data
      });
    } else {
      this.deviceService.getAllByHolderId(id).subscribe((data) => {
          this.deviceList = data
        },
        (_error) => {
          alert("No devices available now")
        });
    }
  }
}
