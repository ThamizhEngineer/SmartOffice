import { Component, Input } from '@angular/core';

import { NavController } from 'ionic-angular';

/**
 * Generated class for the BasicTableComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'basic-table',
  templateUrl: 'basic-table.html'
})
export class BasicTableComponent {

  @Input('data') data: any;
  @Input('events') events: any;

  constructor(public navCtrl: NavController) {
    console.log('Hello BasicTableComponent Component');
  }

  onEvent(event: string, item: any, e: any) {
    this.navCtrl.push(item.page);
  }


}
