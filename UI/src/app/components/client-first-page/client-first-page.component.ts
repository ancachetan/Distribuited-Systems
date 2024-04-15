import {Component, OnInit} from '@angular/core';
import jwtDecode from "jwt-decode";
import {WebSocketService} from "../../services/WebSocketService";
import {TokenDecoderService} from "../../services/TokenDecoderService";

@Component({
  selector: 'app-client-first-page',
  templateUrl: './client-first-page.component.html',
  styleUrls: ['./client-first-page.component.css']
})
export class ClientFirstPageComponent implements OnInit {
  token: any

  constructor(private webSocketService: WebSocketService,
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
  }

  logout() {
    localStorage.clear()
  }
}
