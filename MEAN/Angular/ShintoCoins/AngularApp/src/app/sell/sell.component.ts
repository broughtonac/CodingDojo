import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {
  private transaction: any
  public coins: any
  public value: any
  constructor(private _httpService: HttpService, private _router: Router) {}
  ngOnInit() {
    this.transaction = {action: "sold", amount: "", value: ""}
    this.coins = this._httpService.getCoins()
    this.value = this._httpService.getValue()
  }
  sell() {
    if (this._httpService.getCoins() > this.transaction["amount"]) {
      this._httpService.decrementValue()
      this.transaction["value"] = this._httpService.getValue()
      this._httpService.addTransaction(this.transaction)
      this._httpService.removeCoins(this.transaction["amount"])
      this.coins = this._httpService.getCoins()
      this.value = this._httpService.getValue()
      this.transaction = {action: "sold", amount: "", value: ""}
    }
  }
}
