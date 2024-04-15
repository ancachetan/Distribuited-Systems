import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from "rxjs";
import {User} from "../models/User";
import {Role} from "../models/Role";

@Injectable({
  providedIn: 'root'
})

export class UserService {
  baseURL: string = "http://localhost:8082/users";

  constructor(private httpClient: HttpClient) {
  }

  login(username: any, password: any) {
    const body = JSON.stringify({
        username: username,
        password: password
      }
    )
    return this.httpClient.post(this.baseURL + "/login", body, {headers: new HttpHeaders().set('Content-Type', 'application/json')});
  }

  register(user: User) {
    return this.httpClient.post(this.baseURL + "/register", user);
  }

  getById(id: string | undefined): Observable<User> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User>(this.baseURL + "/getById/" + id, {headers: header});
  }

  getAllByName(name: string): Observable<User[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/getAllByName/" + name, {headers: header});
  }

  getByUsername(username: string): Observable<User> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User>(this.baseURL + "/getByUsername/" + username, {headers: header});
  }

  getAll(): Observable<User[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/getAll", {headers: header});
  }

  getAllByRole(role: Role): Observable<User[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<User[]>(this.baseURL + "/getAllByRole/" + role, {headers: header});
  }

  save(user: User): Observable<User> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.post<User>(this.baseURL + "/save", user, {headers: header});
  }

  update(user: User): Observable<User> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.put<User>(this.baseURL + "/update", user, {headers: header});
  }

  delete(id: string): Observable<User> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete<User>(this.baseURL + "/delete/" + id, {headers: header});
  }
}
