import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-mine',
  templateUrl: './mine.component.html',
  styleUrls: ['./mine.component.css']
})
export class MineComponent implements OnInit {
  public value: any
  public attempt: any
  private answer: any
  private transaction: any
  constructor(private _httpService: HttpService, private _router: Router) { }
  ngOnInit() {
    this.value = this._httpService.getValue()
    this.answer = 1
    this.attempt = ""
    this.transaction = {action: "mined", amount: 1, value: ""}
  }
  mine() {
    if (this.attempt == this.answer) {
      this._httpService.incrementValue()
      this.value = this._httpService.getValue()
      this.transaction["value"] = this._httpService.getValue()
      this._httpService.addTransaction(this.transaction)
      console.log(this._httpService.getTransactions())
      this.transaction = {action: "mined", amount: 1, value: ""}
    }
    this.attempt = ""
  }
}
