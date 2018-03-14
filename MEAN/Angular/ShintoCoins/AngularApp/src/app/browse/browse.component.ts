import { Component, OnInit } from '@angular/core';
import { HttpService } from '../http.service'
import { Router } from '@angular/router'

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit {
  public transactions: any
  constructor(private _httpService: HttpService, private _router: Router) { }
  ngOnInit() {
    this.transactions = this._httpService.getTransactions()
    console.log(this.transactions)
  }
}
