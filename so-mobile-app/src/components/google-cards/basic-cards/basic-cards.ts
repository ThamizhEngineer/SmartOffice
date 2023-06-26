import { Component, Input } from '@angular/core';
import { IonicPage, NavController } from 'ionic-angular';

@IonicPage()
/**
 * Generated class for the CardsComponent component.
 *
 * See https://angular.io/api/core/Component for more info on Angular
 * Components.
 */
@Component({
  selector: 'basic-cards',
  templateUrl: 'basic-cards.html'
})
export class BasicCardsComponent {

  @Input('data') data: any;
  @Input('events') events: any;
  
  constructor(public navCtrl: NavController) {
    
  }

  onEvent(event: string, item: any, e: any) {
    this.navCtrl.push(item.page);
  }

}
