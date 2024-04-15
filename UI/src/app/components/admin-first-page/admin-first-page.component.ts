import {Component, OnInit} from '@angular/core';
import jwtDecode from 'jwt-decode';
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {WebSocketService} from "../../services/WebSocketService";
import {TokenDecoderService} from "../../services/TokenDecoderService";

@Component({
  selector: 'app-admin-first-page',
  templateUrl: './admin-first-page.component.html',
  styleUrls: ['./admin-first-page.component.css']
})
export class AdminFirstPageComponent implements OnInit {
  user: User = new User()
  token: any

  constructor(private userService: UserService,
              private webSocketService: WebSocketService,
              private tokenDecoder: TokenDecoderService) {
  }

  ngOnInit(): void {
    const id = this.tokenDecoder.getIdFromToken();
    if (id != null) {
      this.getById(id)
    }
    this.webSocketService.subscribeToNotifications().subscribe((message) => {
      console.log(id)
      console.log(message.message)
      if (message.id == id) {
        alert(message.message)
      }
    });
  }

  getById(id: string) {
    this.userService.getById(id).subscribe((data) => {
      this.user = data;
    });
  }

  logout() {
    localStorage.clear()
  }
}
