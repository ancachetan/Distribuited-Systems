import {Component, OnInit} from '@angular/core';
import {ConsumptionService} from "../../services/ConsumptionService";
import {MatDatepickerInputEvent} from "@angular/material/datepicker";
import {DeviceService} from "../../services/DeviceService";
import {Device} from "../../models/Device";
import {WebSocketService} from "../../services/WebSocketService";
import {TokenDecoderService} from "../../services/TokenDecoderService";

@Component({
  selector: 'app-view-chart-page',
  templateUrl: './client-view-chart-page.component.html',
  styleUrls: ['./client-view-chart-page.component.css']
})
export class ClientViewChartPageComponent implements OnInit {
  token: any
  devices: Device[] = []
  selected: any
  dataPoints: [{ "x": number, "y": number | undefined }] = [{"x": 0, "y": 0}]
  hours: number[] = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23]
  chartOptions = {
    title: {
      text: "Consumption Column Chart"
    },
    animationEnabled: true,
    data: [{
      type: "column",
      dataPoints: this.dataPoints
    }],
    options: {
      responsive: true,
      aspectRatio: 2.5
    }
  }

  constructor(private consumptionService: ConsumptionService,
              private deviceService: DeviceService,
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

    for (let i = 0; i < this.hours.length; i++) {
      this.dataPoints.push({"x": this.hours[i], "y": 0})
    }

    this.deviceService.getAll().subscribe((devices) => {
      for (let device of devices) {
        if (device.holderId === id) {
          this.devices.push(device)
        }
      }
    })
  }

  updateChart(e: MatDatepickerInputEvent<Date>) {
    const date = e.value?.getDate()
    const month = e.value?.getMonth()
    const year = e.value?.getFullYear()
    let fullDate = year + "-";

    if (month != undefined) {
      if ((month + 1) < 10) {
        fullDate = fullDate + "0" + (month + 1) + "-"
      } else {
        fullDate = fullDate + (month + 1) + "-"
      }
    }

    if (date != undefined) {
      if (date < 10) {
        fullDate = fullDate + "0" + date
      } else {
        fullDate = fullDate + date
      }
    }
    console.log(this.selected)
    console.log(fullDate)

    if (this.selected === undefined) {
      alert("Please select a device first!")
    } else {
      for (let i = 0; i < this.hours.length; i++) {
        this.consumptionService.getHourConsumption(this.selected, fullDate, this.hours[i]).subscribe((consumption) => {
          this.dataPoints[i] = {"x": this.hours[i], "y": consumption}
          console.log(consumption)
        })
      }
    }
  }
}
