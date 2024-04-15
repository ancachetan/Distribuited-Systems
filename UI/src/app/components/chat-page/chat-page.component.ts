import {Component, OnInit} from '@angular/core';
import {User} from "../../models/User";
import {UserService} from "../../services/UserService";
import {MatDialog} from "@angular/material/dialog";
import {ChatDialogComponent} from "../chat-dialog/chat-dialog.component";
import {TokenDecoderService} from "../../services/TokenDecoderService";
import {Role} from "../../models/Role";

@Component({
  selector: 'app-chat-page',
  templateUrl: './chat-page.component.html',
  styleUrls: ['./chat-page.component.css']
})
export class ChatPageComponent implements OnInit {
  usersList: User[] = [];
  url: any
  selectedUser: User = new User()

  constructor(private userService: UserService,
              private dialog: MatDialog,
              private tokenService: TokenDecoderService) {
  }

  ngOnInit(): void {
    const role = this.tokenService.getRoleFromToken()
    if (role === "ADMIN") {
      this.url = "/admin"
      this.getAllClients()
    } else {
      this.url = "/client"
      this.getAllAdmins()
    }
  }

  openDialog() {
    const id = this.tokenService.getIdFromToken()
    const role = this.tokenService.getRoleFromToken()
    const selectedId = this.selectedUser.id
    const name = this.selectedUser.name
    this.dialog.open(ChatDialogComponent, {
      width: '1000px',
      height: '700px',
      data: {selectedId, id, role, name}
    });
    this.dialog.afterAllClosed.subscribe(() => {
      window.location.reload()
    })
  }

  getAllAdmins() {
    this.userService.getAllByRole(Role.ADMIN).subscribe((admins) => {
      this.usersList = admins
    })
  }

  getAllClients() {
    this.userService.getAllByRole(Role.CLIENT).subscribe((clients) => {
      this.usersList = clients
    })
  }
}
