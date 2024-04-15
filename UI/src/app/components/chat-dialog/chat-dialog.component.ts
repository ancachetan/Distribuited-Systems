import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {ChatService} from "../../services/ChatService";
import {Message} from "../../models/Message";
import {TokenDecoderService} from "../../services/TokenDecoderService";
import {WebSocketChatService} from "../../services/WebSocketChatService";
import {delay, from} from "rxjs";
import {MatSnackBar} from "@angular/material/snack-bar";

@Component({
  selector: 'app-chat-dialog',
  templateUrl: './chat-dialog.component.html',
  styleUrls: ['./chat-dialog.component.css']
})
export class ChatDialogComponent implements OnInit {
  messages: Message[] | undefined
  newMessage: string = ''

  constructor(private dialogRef: MatDialogRef<ChatDialogComponent>,
              private webSocketService: WebSocketChatService,
              private chatService: ChatService,
              private tokenService: TokenDecoderService,
              private snackBar: MatSnackBar,
              @Inject(MAT_DIALOG_DATA) public data: any) {
  }

  ngOnInit(): void {
    this.initializeMessages()
    this.webSocketService.initializeChatWebSocketConnection()
    this.webSocketService.initializeSeenNotificationWebSocketConnection()
    this.webSocketService.initializeTypingWebSocketConnection()
    this.webSocketService.sendSeenNotificationMessage(this.composeInitMessage()).subscribe()
    if (this.data.role === "ADMIN") {
      this.webSocketService.subscribeToAdminTopic(this.data.id).subscribe((message) => {
        if (message.from === this.data.selectedId && message.to === this.data.id) {
          message.seen = true
          this.messages?.push(message)
          this.chatService.updateSeenStatus(message).subscribe()
          this.webSocketService.sendSeenNotificationMessage(message).subscribe()
        }
      });
    } else {
      this.webSocketService.subscribeToClientTopic(this.data.id).subscribe((message) => {
        if (message.from === this.data.selectedId && message.to === this.data.id) {
          message.seen = true
          this.messages?.push(message)
          this.chatService.updateSeenStatus(message).subscribe()
          this.webSocketService.sendSeenNotificationMessage(message).subscribe()
        }
      });
    }
    this.webSocketService.subscribeToSeenNotificationTopic(this.data.id).subscribe(() => {
      this.chatService.getMessagesFromAConversation(this.data.id, this.data.selectedId).subscribe((messages: Message[]) => {
        this.messages = messages
      })
    })

    this.webSocketService.subscribeToTypingTopic(this.data.id, this.data.selectedId).subscribe(() => {
      this.snackBar.open("typing", "", {
        duration: 2000,
      });
    })
  }

  onSend() {
    const id = this.tokenService.getIdFromToken()
    if (this.data.role === "ADMIN") {
      this.chatService.sendAndSaveAdminMessage(this.composeMessage()).subscribe()
    } else {
      this.chatService.sendAndSaveClientMessage(this.composeMessage()).subscribe()
    }
    this.newMessage = ''
    this.chatService.getMessagesFromAConversation(this.data.id, this.data.selectedId).subscribe(messages => {
      this.messages = messages
    })
  }

  onClose(): void {
    if (this.data.role === "ADMIN") {
      this.webSocketService.unsubscribeFromAdminTopic(this.data.id)
    } else {
      this.webSocketService.unsubscribeFromClientTopic(this.data.id)
    }
    this.webSocketService.unsubscribeFromTypingTopic(this.data.id, this.data.selectedId)
    this.webSocketService.unsubscribeFromSeenNotificationsTopic(this.data.id)
    this.webSocketService.disconnectFromSeenNotifications()
    this.webSocketService.disconnectFromChat()
    this.webSocketService.disconnectFromTyping()
    from([true]).pipe(delay(100)).subscribe(() => {
      this.dialogRef.close();
    });
  }

  composeMessage(): Message {
    let message = new Message()
    message.to = this.data.selectedId
    message.from = this.data.id
    message.content = this.newMessage
    message.seen = false
    return message
  }

  composeInitMessage(): Message {
    let message = new Message()
    message.to = this.data.id
    message.from = this.data.selectedId
    message.content = this.newMessage
    message.seen = false
    return message
  }

  initializeMessages() {
    this.chatService.getMessagesFromAConversation(this.data.id, this.data.selectedId).subscribe((messages: Message[]) => {
      for (let message of messages) {
        if (message.seen == false && message.to == this.data.id) {
          message.seen = true
          this.chatService.updateSeenStatus(message).subscribe()
        }
      }
    })
    this.chatService.getMessagesFromAConversation(this.data.id, this.data.selectedId).subscribe((messages: Message[]) => {
      this.messages = messages
    })
  }

  sendTypingNotification() {
    this.webSocketService.sendTypingMessage(this.data.selectedId, this.data.id).subscribe()
  }
}
