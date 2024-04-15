import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ConsumptionService {
  baseURL: string = "http://localhost:8083/consumptions";

  constructor(private httpClient: HttpClient) {
  }

  getHourConsumption(deviceId: string, date: string, hour: number): Observable<number> {
    let token = localStorage.getItem("token")
    let header = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('Authorization', `Bearer ${token}`)
    return this.httpClient.get<number>(this.baseURL + "/getHourConsumption/" + deviceId + "/" + date + "/" + hour, {headers: header});
  }
}
