import { Component } from '@angular/core';
import { IonicPage, NavController, NavParams } from 'ionic-angular';

import { TasksService } from './../../providers/tasks.service';

/**
 * Generated class for the TasksPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@IonicPage()
@Component({
  selector: 'page-tasks',
  templateUrl: 'tasks.html',
})
export class TasksPage {

  tasks:any={};

  constructor(public navCtrl: NavController, public navParams: NavParams, public tasksService: TasksService) {
    this.getAllTasks();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad TasksPage');    
    
  }

  getAllTasks(){
    console.log('1');
    this.tasksService.getCurrentUserAllTasks().subscribe((x)=>{
      console.log('alltasks',x);
      this.tasks=x.content;
    },error=>{

    });
  }

  navigateToCreateTask(){

  }
}
