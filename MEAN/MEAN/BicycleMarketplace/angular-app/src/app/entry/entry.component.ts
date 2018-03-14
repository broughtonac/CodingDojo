import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-entry',
  templateUrl: './entry.component.html',
  styleUrls: ['./entry.component.css']
})
export class EntryComponent implements OnInit {
  private unregisteredUser: any // registering
  private registeredUser: any // logging in
  constructor(private _userService: UserService, private _router: Router) { }
  reset() {
    this.unregisteredUser = {
      first: "",
      last: "",
      email: "",
      password: "",
      backendErrors: []
    }
    this.registeredUser = {
      email: "",
      password: "",
      backendErrors: []
    }
  }
  ngOnInit() {
    this.reset()
    this._userService.logout()
  }
  register() {
    this._userService.register(this.unregisteredUser, (data) => {
      if (data.backendErrors) {
        this.unregisteredUser.backendErrors = data.backendErrors
      }
      else {
        this.reset()
        this._router.navigate(["/browse"])
      }
    })
  }
  login() {
    this._userService.login(this.registeredUser, (data) => {
      if (data.backendErrors) {
        this.registeredUser.backendErrors = data.backendErrors
      }
      else {
        this.reset()
        this._router.navigate(["/browse"])
      }
    })
  }
}
