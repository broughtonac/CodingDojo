import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { BicycleService } from '../bicycle.service';

@Component({
  selector: 'app-browse',
  templateUrl: './browse.component.html',
  styleUrls: ['./browse.component.css']
})
export class BrowseComponent implements OnInit {
  constructor(
    private _userService: UserService,
    private _bicycleService: BicycleService,
    private _router: Router
  ) {}
  private user: any
  private bicycles: any
  private bicycle: any
  getAll() { // improvement: chop up into observable
    this._bicycleService.getAll(data => {
      this.bicycles = data.bicycles
    })
  }
  includeSellers() { // improvement: chop up into observable
    let bicycles
    this._bicycleService.getAll(data1 => {
      bicycles = data1.bicycles
      for (let i = 0; i < bicycles.length; i++) {
        this._userService.getOne(bicycles[i]._user, data2 => {
          bicycles[i]["userName"] = data2.user.first + " " + data2.user.last
          bicycles[i]["userEmail"] = data2.user.email
        })
      }
      this.bicycles = bicycles
    })
  }
  ngOnInit() {
    this._userService.session((data) => {
      if (data) {
        this.user = data.user
      }
      else {
        this._router.navigate(["/"])
      }
    })
    this.getAll()
    this.includeSellers()
  }
  destroy(bid) {
    this._bicycleService.destroy(bid, data => {
      this.getAll()
    })
  }
  viewContact(uid) {
    this._userService.getOne(uid, data => {
      alert(
        "SELLER CONTACT INFO:\n\n" +
        "first name: " + data.user.first + "\n" +
        "last name: " + data.user.last + "\n" +
        "email: " + data.user.email
      )
    })
  }
  search(query) {
    if (query) {
      let bicycles
      this._bicycleService.search(query, data1 => {
        bicycles = data1.bicycles
        for (let i = 0; i < bicycles.length; i++) {
          this._userService.getOne(bicycles[i]._user, data2 => {
            bicycles[i]["userName"] = data2.user.first + " " + data2.user.last
            bicycles[i]["userEmail"] = data2.user.email
          })
        }
        this.bicycles = bicycles
      })
    }
    else {
      this.includeSellers()
    }
  }
}
