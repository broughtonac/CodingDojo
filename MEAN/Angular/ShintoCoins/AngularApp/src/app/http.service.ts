import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable()
export class HttpService {
  private value = 0
  private transactions = []
  private coins = 0
  constructor(private _http: HttpClient) {}
  addTransaction(transaction) {
    this.transactions.push(transaction)
  }
  incrementValue() {
    this.value += 1
  }
  decrementValue() {
    this.value -= 1
  }
  getTransactions() {
    return this.transactions
  }
  getValue() {
    return this.value
  }
  addCoins(amount) {
    this.coins += amount
  }
  removeCoins(amount) {
    if (this.coins > 0) {
      this.coins -= amount
    }
  }
  getCoins() {
    return this.coins
  }
}
