import { Component, OnInit } from '@angular/core';
import { HttpService } from './http.service'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  newTask: any
  taskToUpdate: any
  allTasks = []
  foundTask: any
  taskCurrentlyEditing: any
  constructor(private _httpService: HttpService) {}
  ngOnInit() {
    this.getTasksFromService()
    this.newTask = {title: "", description: ""} // angular model
    this.taskToUpdate = {} // angular model
    this.foundTask = {} // mongo model
    this.taskCurrentlyEditing = {}
  }
  getTasksFromService() {
    let observable = this._httpService.getTasks()
    observable.subscribe(data => {
      console.log("got data", data)
      this.allTasks = data["tasks"]
    })
  }
  getTaskFromServiceById(id: string) {
    let observable = this._httpService.getTaskById(id)
    observable.subscribe(data => {
      console.log("got data", data)
      this.foundTask = data["task"]
    })
  }
  onSubmitCreateTask(): void {
    let observable = this._httpService.createTask(this.newTask)
    observable.subscribe(data => {
      this.getTasksFromService()
      this.allTasks = data["tasks"]
      this.newTask = {title: "", description: ""}
    })
  }
  onClickUpdateTask(id: string): void {
    let observable = this._httpService.getTaskById(id)
    observable.subscribe(data => {
      this.taskCurrentlyEditing = data["task"]
      this.taskToUpdate = data["task"]
      console.log("compare", this.taskToUpdate, this.taskCurrentlyEditing)
    })
  }
  onSubmitUpdateTask(id: string): void {
    let observable = this._httpService.updateTask(id, this.taskToUpdate)
    observable.subscribe(data => {
      this.getTasksFromService()
      this.taskToUpdate = {}
    })
  }
  onClickDeleteTask(id: string): void {
    let observable = this._httpService.deleteTask(id)
    observable.subscribe(data => {
      this.getTaskFromServiceById(id)
      console.log(this.foundTask, this.taskToUpdate)
      if (this.taskToUpdate._id == this.taskCurrentlyEditing._id) {
        this.taskToUpdate = {}
        this.taskCurrentlyEditing = {}
      }
      this.getTasksFromService()
    })
  }
}
