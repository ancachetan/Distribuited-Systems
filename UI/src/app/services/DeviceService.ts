import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Device} from "../models/Device";

@Injectable({
  providedIn: 'root'
})

export class DeviceService {
  baseURL: string = "http://localhost:8081/devices";

  constructor(private httpClient: HttpClient) {
  }

  getById(id: string | undefined): Observable<Device> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device>(this.baseURL + "/getById/" + id, {headers: header});
  }

  getAllByDescription(description: string): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByDescription/" + description, {headers: header});
  }

  getAllByHolderIdAndDescription(holderId: string, description: string): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByHolderIdAndDescription/" + holderId + "/" + description, {headers: header});
  }

  getAllByAddress(address: string): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByAddress/" + address, {headers: header});
  }

  getAllByHolderIdAndAddress(holderId: string, address: string): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByHolderIdAndAddress/" + holderId + "/" + address, {headers: header});
  }

  getAllByHolderId(holderId: string): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByHolderId/" + holderId, {headers: header});
  }

  getAllByMaxConsumption(maxEnergyConsumption: number): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByMaxConsumption/" + maxEnergyConsumption, {headers: header});
  }

  getAllByHolderIdAndMaxEnergyConsumption(holderId: string, maxEnergyConsumption: number): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAllByHolderIdAndMaxConsumption/" + holderId + "/" + maxEnergyConsumption, {headers: header});
  }

  getAll(): Observable<Device[]> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<Device[]>(this.baseURL + "/getAll", {headers: header});
  }

  save(device: Device): Observable<Device> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.post<Device>(this.baseURL + "/save", device, {headers: header});
  }

  update(device: Device): Observable<Device> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.put<Device>(this.baseURL + "/update", device, {headers: header});
  }

  delete(id: string): Observable<Device> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete<Device>(this.baseURL + "/delete/" + id, {headers: header});
  }

  deleteByHolderId(id: string): Observable<Device> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.delete<Device>(this.baseURL + "/deleteByHolderId/" + id, {headers: header});
  }
}
