import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

import { CreateGroupPage } from '../create-group/create-group.component';

import { UserService } from '../../services/user.service';

import  firebase  from 'firebase';

/**
 * Generated class for the GroupContactPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-group-contact',
  templateUrl: 'group-contact.component.html',
})
export class GroupContactPage {

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public userService: UserService) {
  }

  myInput:string=null;
  tempUsers:any=[];
  users:any=[{displayName:null,email:null,uid:null}];
  selectedContacts:any=[];


  ionViewDidLoad() {
    console.log('ionViewDidLoad GroupContactPage');
    this.getAllUsers();
  }

  ionViewWillEnter() {
    this.getAllUsers();
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

  createGroup(){
    this.navCtrl.push(CreateGroupPage,{
      data:this.selectedContacts
    })
  }

  selectUser(event , user){
    console.log("event",event.checked);
    console.log("user",user);
    if(event.checked){
      this.selectedContacts.push(user);
    }else{
      this.selectedContacts.pop(user);
    }
    console.log("selectedContacts",this.selectedContacts);
  }

}
