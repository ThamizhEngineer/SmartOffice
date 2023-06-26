import { Component, Input } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

import { TaskDetailsPage } from './../../../pages/task-details/task-details';

@IonicPage()

/**
 * Generated class for the TaskCardsComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'task-cards',
  templateUrl: 'task-cards.html'
})
export class TaskCardsComponent {

  @Input('data') data: any;
  @Input('events') events: any;
  
  constructor(public navCtrl: NavController) {
    
  }

  onEvent(event: string, item: any, e: any) {
    this.navCtrl.push(TaskDetailsPage,{
      currentTask:item
    });
  }


}
