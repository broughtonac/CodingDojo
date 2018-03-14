import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'

@Injectable()
export class HttpService {
  constructor(private _http: HttpClient) {}
  getPlayers() {
    return this._http.get("/playersdb")
  }
  getPlayer(id) {
    return this._http.get("/playersdb/" + id)
  }
  createPlayer(data) {
    return this._http.post("/playersdb", data)
  }
  updatePlayer(id, data) {
    return this._http.put("/playersdb/" + id, data)
  }
  deletePlayer(id) {
    return this._http.delete("/playersdb/" + id)
  }
}
