import { Component, NgZone, ViewChild } from '@angular/core';
import { NavController, NavParams, Events, Content, Navbar } from 'ionic-angular';

import { GroupsService } from '../../services/groups.service';
import { UserService } from '../../services/user.service';
import { ChatHomePage } from '../chat-home/chat-home.component';

/**
 * Generated class for the GroupChatPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-group-chat',
  templateUrl: 'group-chat.component.html',
})
export class GroupChatPage {
  @ViewChild('content') content: Content;
  @ViewChild(Navbar) navBar: Navbar;
  owner: boolean = false;
  groupName:any;
  displayName:string;
  groupMembers:any=[];
  newmessage;
  alignuid:string;
  allgroupmsgs:any;

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public groupsService: GroupsService,
              public userService: UserService,
              public events: Events,
              public zone: NgZone) {
               
                // this.groupsService.getownership(this.groupName).then((res) => {
                //   if (res)
                //     this.owner = true;  
                //   }).catch((err) => {
                //     alert(err);
                // })

                this.events.subscribe('gotintogroup', () => {
                  this.zone.run(()=>{
                    this.groupMembers = this.groupsService.currentgroup;
                    console.log("this.groupMembers",this.groupMembers);
                  })
                })

                this.events.subscribe('newgroupmsg', () => {
                  this.allgroupmsgs = [];
                  this.zone.run(()=>{
                    this.allgroupmsgs = this.groupsService.groupmsgs;
                    console.log("this.groupmsg",this.allgroupmsgs);
                  })
                })
                 
  }

  ionViewWillEnter() {
    console.log('ionViewDidLoad GroupChatPage');
  }

  ionViewDidLoad() {
    this.alignuid = this.userService.getCurrentUserId();
    this.groupName=this.navParams.get('groupName');
    console.log("uid",this.alignuid);
    console.log("this.groupName",this.groupName);
    this.groupsService.getintogroup(this.groupName);
    this.groupsService.getgroupmsgs(this.groupName);

    this.navBar.backButtonClick=()=>{
      this.navCtrl.setRoot(ChatHomePage);
    }

    //https://github.com/aggarwalankush/ionic-push-base
  }

  sendMessage() {
    this.groupsService.addgroupmsg(this.newmessage).then(() => {
      this.scrollto();
      this.newmessage = '';
    })
  }

  scrollto() {
    setTimeout(() => {
      this.content.scrollToBottom();
    }, 1000);
  }

  backToChatHome(){
    alert("back to Home");
  }

}
