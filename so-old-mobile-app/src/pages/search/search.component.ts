import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { SingleChatPage } from '../single-chat/single-chat.component';
import { GroupChatPage } from '../group-chat/group-chat.component';
import { GroupContactPage } from '../group-contact/group-contact.component';

import { UserService } from '../../services/user.service';
import { ChatService } from '../../services/chat.service';

import  firebase  from 'firebase';

/**
 * Generated class for the SearchPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-search',
  templateUrl: 'search.component.html'
})
export class SearchPage {



  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public userService: UserService,
              public chatService: ChatService) {
  }
  myInput:string=null;
  currentUid:string;
  friends:any;
  jobs:any;
  tempUsers:any=[];
  tempResponse:any=[];
  users:any=[{displayName:null,email:null,uid:null}];
  //users:any;

  ionViewDidLoad() {
    console.log('ionViewDidLoad SearchPage');
    this.getAllUsers();
    this.userService.updateOnConnect();
  }

  ionViewWillEnter() {
    this.currentUid=this.userService.getCurrentUserId();
  }

  getAllUsers(){
    let self = this;
    this.userService.getAllUsers().then(function(response:any){
      if(response){
        self.users=response.filter((obj)=>{
          return obj.uid != firebase.auth().currentUser.uid;
        });
        self.tempUsers=self.users;
      }
    });
  }

  searchUser(inputValue){
    let searchInput=inputValue.target.value;
    console.log("inputValue",inputValue);
    if (searchInput && searchInput.trim() != '') {
      this.tempUsers = this.users.filter((user) => {
        console.log("user",user);
        return (user.displayName.toLowerCase().indexOf(searchInput.toLowerCase()) > -1);
      })
    }else{
      this.tempUsers=this.users;
    }
  }

  onCancel(inputValue){
    this.myInput="";
  }

  isOnline(status){
    let colour= (status=="online") ? "green" : "red";
    return colour;
  }
  

  singleChat(collegue){
    this.chatService.initializeMessage(collegue);
    this.navCtrl.push(SingleChatPage);
  }

  groupChat(members){
    this.navCtrl.push(GroupChatPage,{
      data:members
    });
  }

  selectContactGroupPage(){
    this.navCtrl.push(GroupContactPage);
  }

}
