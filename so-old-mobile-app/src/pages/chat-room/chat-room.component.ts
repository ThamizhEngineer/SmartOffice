import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { Observable } from 'rxjs';

import { AngularFireDatabase } from 'angularfire2/database';

/**
 * Generated class for the ChatPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-chat-room',
  templateUrl: 'chat-room.component.html',
})
export class ChatRoomPage {

  firstname: string = '';
  currentUser:any;
  message: string = '';
  _chatSubscription;
  messages: Observable<any[]>;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public db: AngularFireDatabase) {

    this.currentUser=JSON.parse(localStorage.getItem('currentUser'));
    this.firstname = this.currentUser.firstName;
    this.messages = this.db.list('/chat').valueChanges();
    console.log("this.mess",this.messages);
  }

  sendMessage() {
    this.db.list('/chat').push({
      username: this.firstname,
      message: this.message
    }).then( () => {
      // message is sent
    });
    this.message = '';
  }

  

  ionViewDidLoad() {
    this.db.list('/chat').push({
      specialMessage: true,
      message: `${this.firstname} has joined the room`
    });
  }

  ionViewWillLeave(){
    //this._chatSubscription.unsubscribe();
    this.db.list('/chat').push({
      specialMessage: true,
      message: `${this.firstname} has left the room`
    });
  }

}
