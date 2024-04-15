import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Message} from "../models/Message";

@Injectable({
  providedIn: 'root'
})
export class ChatService {
  baseURL: string = "http://localhost:8084/chat";

  constructor(private httpClient: HttpClient) {
  }

  sendAndSaveAdminMessage(message: Message): Observable<Message> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.post<Message>(this.baseURL + "/sendAndSave/admin", message, {headers: header});
  }

  sendAndSaveClientMessage(message: Message): Observable<Message> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.post<Message>(this.baseURL + "/sendAndSave/client", message, {headers: header});
  }

  getMessagesFromAConversation(id1: string, id2: string): Observable<Message[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Message[]>(this.baseURL + "/getMessages/" + id1 + "/" + id2, {headers: header});
  }

  updateSeenStatus(message: Message): Observable<Message> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.post<Message>(this.baseURL + "/update", message, {headers: header});
  }
}
