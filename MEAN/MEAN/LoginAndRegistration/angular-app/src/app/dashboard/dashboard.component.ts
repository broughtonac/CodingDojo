import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service'
import {Router} from"@angular/router"

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private user: any
  constructor(private _userService: UserService, private _router: Router) {}
  ngOnInit() {
    this._userService.session((data) => {
      if (data.errors) {
        this._router.navigate(["/register"])
      }
      else {
        this.user = data
      }
    })
  }
}
