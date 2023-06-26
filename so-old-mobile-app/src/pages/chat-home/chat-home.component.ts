import { Component, NgZone } from '@angular/core';
import { NavController, NavParams, Events, LoadingController } from 'ionic-angular';

import { ChatRoomPage } from '../chat-room/chat-room.component';
import { SearchPage } from '../search/search.component';
import { CreateGroupPage } from '../create-group/create-group.component';

import { SingleChatPage } from '../single-chat/single-chat.component';
import { GroupChatPage } from '../group-chat/group-chat.component';
import { ChatService } from '../../services/chat.service';
import { UserService } from '../../services/user.service';
import { GroupsService } from '../../services/groups.service';

@Component({
  selector: 'page-chat-home',
  templateUrl: 'chat-home.component.html',
})
export class ChatHomePage {

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public events: Events,
              public zone: NgZone,
              public loadingCtrl: LoadingController,
              public userService: UserService,
              public groupsService: GroupsService,
              public chatService: ChatService) {
                this.allMessageArr=[];
                this.lastMessageArr=[];
                this.groupsMessageArr=[];
                this.events.subscribe('colleguemessage',()=>{
                  this.zone.run(()=>{
                    setTimeout(()=>{
                      this.collegues=this.chatService.allCollegueMessages;
                      this.lastMessageArr=this.chatService.lastMessageArr;
                      console.log("this.lastMessageArr",this.lastMessageArr);
                      console.log("this.allmessages",this.collegues);
                    },1000);
                  })
                });

                this.events.subscribe('groupmessage', () => {
                  this.zone.run(()=>{
                    setTimeout(()=>{
                      this.groupsMessageArr = this.groupsService.groupsMessageArr;
                      console.log("this.groupsMessageArr",this.groupsMessageArr);
                      console.log("this.last",this.lastMessageArr);
                      this.allMessageArr=[...this.groupsMessageArr,...this.lastMessageArr];
                      this.allMessageArr.sort((a,b)=> a.timestamp > b.timestamp ? -1 : a.timestamp < b.timestamp ? 1 : 0);
                    },1000);
                  })
                });    
  }

  collegues:any;
  lastMessage:any;
  lastMessageArr:Array<any> = [];
  groupsMessageArr:Array<any> = [];
  allMessageArr:Array<any> = [];
  allmygroups;

  ionViewWillEnter() {
    let loader = this.loadingCtrl.create({
      content: 'Getting your Messages, Please wait...'
    });
    loader.present();
    this.chatService.getAllCollegueMessages();
    this.groupsService.getAllGroupsLastMessage();
    loader.dismiss();
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ChatHomePage');
  }

  ionViewDidLeave() {
    this.events.unsubscribe('allmessages');
  }

  chatRoom(){
    this.navCtrl.push(ChatRoomPage);
  }

  addFriends(){
    this.navCtrl.push(SearchPage);
  }

  createGroupPage(){
    this.navCtrl.push(CreateGroupPage);
  }

  chats(group) {
    if(group.groupName){
      console.log("group",group);
      this.groupsService.getintogroup(group.groupName);
      this.navCtrl.push(GroupChatPage, { groupName: group.groupName });
    }else{
      this.userService.getUserByUID(group.uid).then((response)=>{
        if(response) {
          console.log("res",response);
          this.chatService.initializeMessage(response);
          this.navCtrl.push(SingleChatPage);
        }
      });
    }
    
  }
}
