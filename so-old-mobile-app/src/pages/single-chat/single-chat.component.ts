import { Component, NgZone, ViewChild } from '@angular/core';
import { NavController, NavParams, Events, Content } from 'ionic-angular';
import { ChatService } from '../../services/chat.service';

/**
 * Generated class for the SingleChatPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-single-chat',
  templateUrl: 'single-chat.component.html',
})
export class SingleChatPage {
  @ViewChild('content') content: Content;
  newmessage:any;
  collegue:any={};
  allMessages=[];

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public chatService: ChatService,
              public events: Events,
              public zone: NgZone) {

                this.collegue = this.chatService.collegue;
                console.log("this.collegue",this.collegue);   
                this.scrollto();         
                this.events.subscribe('newmessage',()=>{
                  this.allMessages=[];
                  this.zone.run(()=>{
                    this.allMessages=this.chatService.collegueMessages;
                    console.log("this.all",this.allMessages);
                  })
                })
  }
 
  ionViewDidLoad() {
  
  }

  ionViewDidEnter() {
    this.chatService.getCollegueMessages();
  }

  sendMessage(){
    this.chatService.addnewmessage(this.newmessage).then(()=>{
      this.newmessage="";
    })
  }

  scrollto(){
    setTimeout(() => {
      this.content.scrollToBottom();
    }, 1000);
  }


  

}
