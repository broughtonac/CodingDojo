import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../user.service';
import { BicycleService } from '../bicycle.service';

@Component({
  selector: 'app-listings',
  templateUrl: './listings.component.html',
  styleUrls: ['./listings.component.css']
})
export class ListingsComponent implements OnInit {
  private user: any
  private newBicycle: any
  private oldBicycle: any
  private userBicycles: any
  constructor(
    private _userService: UserService,
    private _bicycleService: BicycleService,
    private _router: Router
  ) {}
  reset() {
    this.newBicycle = {
      title: "",
      description: "",
      price: "",
      location: ""
    }
    this.oldBicycle = {
      title: "",
      description: "",
      price: "",
      location: ""
    }
  }
  getByUser(user) {
    this._bicycleService.getByUser(user, data => {
      this.userBicycles = data.userBicycles
    })
  }
  ngOnInit() {
    this.reset()
    this._userService.session(data => {
      if (data) {
        this.user = data.user
        this.getByUser(this.user)
      }
      else {
        this._router.navigate(["/"])
      }
    })
  }
  create() {
    this._bicycleService.create(this.newBicycle, this.user, data => {
      this.reset()
      this.getByUser(this.user)
    })
  }
  update(bid) {
    this._bicycleService.update(bid, this.oldBicycle, data => {
      this.reset()
      this.getByUser(this.user)
    })
  }
  destroy(bid) {
    this._bicycleService.destroy(bid, data => {
      this.getByUser(this.user)
    })
  }
}