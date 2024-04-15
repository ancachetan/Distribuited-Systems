import {Injectable} from "@angular/core";
import jwtDecode from "jwt-decode";

@Injectable({
  providedIn: 'root'
})

export class TokenDecoderService {
  tokenDecoded: any

  constructor() {
    let token: any = localStorage.getItem("token")
    this.tokenDecoded = jwtDecode(token)
  }

  getIdFromToken(): string {
    return this.tokenDecoded.id
  }

  getRoleFromToken(): string {
    return this.tokenDecoded.role
  }
}
