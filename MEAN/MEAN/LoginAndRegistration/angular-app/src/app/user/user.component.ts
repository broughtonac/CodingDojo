import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service'
import {Router} from"@angular/router"

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  private user: any // registering
  private loggedInUser: any // logging in
  constructor(private _userService: UserService, private _router: Router) {}
  init() {
    this.user = {
      first: "",
      last: "",
      email: "",
      password: "",
      errors: []
    }
    this.loggedInUser = {
      email: "",
      password: "",
      errors: ""
    }
  }
  ngOnInit() {
    this.init()
    this._userService.logout()
  }
  register() {
    this._userService.register(this.user, (data) => {
      if (data.errors) {
        let es = []
        if (typeof(data.errors[0]) == "string") {
          es.push(data.errors[0])
        }
        else {
          for (let e in data.errors) {
            es.push(data.errors[e].message)
          }
        }
        this.user.errors = es
      }
      else {
        this.init()
        this._router.navigate(["/dashboard"])
      }
    })
  }
  login() {
    this._userService.login(this.loggedInUser, (data) => {
      if (data.errors) {
        this.loggedInUser.errors = data.errors
      }
      else {
        this.init()
        this._router.navigate(["/dashboard"])
      }
    })
  }
}
