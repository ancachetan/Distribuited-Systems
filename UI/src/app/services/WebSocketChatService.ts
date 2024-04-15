import {Injectable} from '@angular/core';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {from, Observable, Observer} from 'rxjs';
import {mergeMap} from 'rxjs/operators';
import {Message} from "../models/Message";

@Injectable({
  providedIn: 'root',
})
export class WebSocketChatService {
  baseURL: string = "http://localhost:8084"
  private stompClientChat: Stomp.Client = null!;
  private connectionPromiseChat: Promise<void> = null!;
  private stompClientSeenNotification: Stomp.Client = null!;
  private connectionPromiseSeenNotification: Promise<void> = null!;
  private stompClientTyping: Stomp.Client = null!;
  private connectionPromiseTyping: Promise<void> = null!;

  constructor() {

  }

  initializeChatWebSocketConnection(): void {
    const socket = new SockJS(this.baseURL + '/chat');
    this.stompClientChat = Stomp.over(socket);
    this.connectionPromiseChat = new Promise<void>((resolve) => {
      this.stompClientChat.connect({}, (frame) => {
        console.log(frame);
        resolve();
      });
    });
  }

  initializeSeenNotificationWebSocketConnection(): void {
    const socket = new SockJS(this.baseURL + '/chat-seen-notification');
    this.stompClientSeenNotification = Stomp.over(socket);
    this.connectionPromiseSeenNotification = new Promise<void>((resolve) => {
      this.stompClientSeenNotification.connect({}, (frame) => {
        console.log(frame);
        resolve();
      });
    });
  }

  initializeTypingWebSocketConnection(): void {
    const socket = new SockJS(this.baseURL + '/chat-typing');
    this.stompClientTyping = Stomp.over(socket);
    this.connectionPromiseTyping = new Promise<void>((resolve) => {
      this.stompClientTyping.connect({}, (frame) => {
        console.log(frame);
        resolve();
      });
    });
  }

  private waitForChatConnection(): Promise<void> {
    return this.connectionPromiseChat;
  }

  private waitForSeenNotificationsConnection(): Promise<void> {
    return this.connectionPromiseSeenNotification;
  }

  private waitForTypingConnection(): Promise<void> {
    return this.connectionPromiseTyping;
  }

  subscribeToAdminTopic(id: string): Observable<Message> {
    return from(this.waitForChatConnection()).pipe(
      mergeMap(() =>
        new Observable((observer: Observer<Message>) => {
          this.stompClientChat.subscribe(`/topic/admin/${id}`, (message: { body: string }) => {
            observer.next(JSON.parse(message.body));
          });
        })
      )
    );
  }

  subscribeToClientTopic(id: string): Observable<Message> {
    return from(this.waitForChatConnection()).pipe(
      mergeMap(() =>
        new Observable((observer: Observer<Message>) => {
          this.stompClientChat.subscribe(`/topic/client/${id}`, (message: { body: string }) => {
            observer.next(JSON.parse(message.body));
          });
        })
      )
    );
  }

  subscribeToSeenNotificationTopic(id: string): Observable<Message> {
    return from(this.waitForSeenNotificationsConnection()).pipe(
      mergeMap(() =>
        new Observable((observer: Observer<any>) => {
            this.stompClientSeenNotification.subscribe(`/topic/seen/${id}`, (message: { body: string }) => {
              console.log("from subscribe " + message.body)
              observer.next(JSON.parse(message.body));
            });
          }
        )
      )
    )
  }

  subscribeToTypingTopic(id1: string, id2: string): Observable<string> {
    return from(this.waitForTypingConnection()).pipe(
      mergeMap(() =>
        new Observable((observer: Observer<any>) => {
            this.stompClientTyping.subscribe(`/topic/typing/${id1}/${id2}`, (message: { body: string }) => {
              console.log("from subscribe " + message.body)
              observer.next(JSON.parse(message.body));
            });
          }
        )
      )
    )
  }

  sendTypingMessage(id1: string, id2: string): Observable<string> {
    return from(this.waitForTypingConnection()).pipe(
      mergeMap(() =>
        new Observable((observer: Observer<any>) => {
          this.stompClientTyping.send(`/topic/typing/${id1}/${id2}`, {}, JSON.stringify("typing"))
          observer.next("typing")
        })
      )
    )
  }

  sendSeenNotificationMessage(message: Message): Observable<Message> {
    return from(this.waitForSeenNotificationsConnection()).pipe(
      mergeMap(() =>
        new Observable((observer: Observer<any>) => {
          this.stompClientSeenNotification.send(`/topic/seen/${message.from}`, {}, JSON.stringify(message))
          console.log("from send " + message)
          observer.next(message)
        })
      )
    )
  }

  unsubscribeFromAdminTopic(id: string): void {
    this.stompClientChat.unsubscribe(`/topic/admin/${id}`);
  }

  unsubscribeFromClientTopic(id: string): void {
    this.stompClientChat.unsubscribe(`/topic/client/${id}`);
  }

  unsubscribeFromSeenNotificationsTopic(id: string): void {
    this.stompClientChat.unsubscribe(`/topic/seen/${id}`);
  }

  unsubscribeFromTypingTopic(id1: string, id2: string): void {
    this.stompClientTyping.unsubscribe(`/topic/typing/${id1}/${id2}`);
  }

  disconnectFromSeenNotifications() {
    this.stompClientSeenNotification.disconnect(() => {
    })
  }

  disconnectFromChat() {
    this.stompClientChat.disconnect(() => {
    })
  }

  disconnectFromTyping() {
    this.stompClientTyping.disconnect(() => {
    })
  }
}
