import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {Observable} from 'rxjs';
import {Notification} from "../models/Notification";

@Injectable({
  providedIn: 'root',
})
export class WebSocketService {
  private stompClient: any;

  constructor() {
  }

  subscribeToNotifications(): Observable<Notification> {
    return new Observable((observer) => {
      const socket = new SockJS('http://localhost:8083/webSocket');
      this.stompClient = Stomp.over(socket);
      this.stompClient.connect({}, () => {
        this.stompClient.subscribe('/topic', (message: { body: string; }) => {
          observer.next(JSON.parse(message.body));
        });
      });
    });
  }
}
