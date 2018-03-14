import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-buy',
  templateUrl: './buy.component.html',
  styleUrls: ['./buy.component.css']
})
export class BuyComponent implements OnInit {
  private transaction: any
  public coins: any
  public value: any
  constructor(private _httpService: HttpService, private _router: Router) {}
  ngOnInit() {
    this.transaction = {action: "bought", amount: "", value: ""}
    this.coins = this._httpService.getCoins()
    this.value = this._httpService.getValue()
  }
  buy() {
    this._httpService.incrementValue()
    this.transaction["value"] = this._httpService.getValue()
    this._httpService.addTransaction(this.transaction)
    this._httpService.addCoins(this.transaction["amount"])
    this.coins = this._httpService.getCoins()
    this.value = this._httpService.getValue()
    this.transaction = {action: "bought", amount: "", value: ""}
  }
}
