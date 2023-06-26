import { Component, NgZone } from '@angular/core';
import { NavController, NavParams, Events, LoadingController } from 'ionic-angular';
import { GroupsService } from '../../services/groups.service';
import { AlertService } from '../../services/alert.service';

import { GroupChatPage } from '../group-chat/group-chat.component';

/**
 * Generated class for the CreateGroupPage page.
 *
 * See https://ionicframework.com/docs/components/#navigation for more info on
 * Ionic pages and navigation.
 */

@Component({
  selector: 'page-create-group',
  templateUrl: 'create-group.component.html',
})
export class CreateGroupPage {

  newgroup:any;
  allmygroups;
  contactsList:any=[];
  groupMembers:any=[];
  selectedContactList:any=[]
  

  constructor(public navCtrl: NavController, 
              public navParams: NavParams,
              public groupsService: GroupsService,
              public alertService:AlertService,
              public events: Events,
              public loadingCtrl: LoadingController,
              public zone: NgZone) {    
                
                this.events.subscribe('gotintogroup', () => {
                  this.zone.run(()=>{
                    this.groupMembers = this.groupsService.currentgroup;
                    console.log("this.groupMembers",this.groupMembers);
                  })
                })

                this.events.subscribe('newgroup', () => {
                  this.zone.run(()=>{
                    this.allmygroups = this.groupsService.mygroups;
                  }) 
                })

  }

  ionViewWillEnter() {
   
  }

  ionViewDidLeave() {
    this.events.unsubscribe('newgroup');
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad CreateGroupPage');
    this.contactsList=this.navParams.get('data');
    console.log("contactsList",this.contactsList);
    
    let loader = this.loadingCtrl.create({
      content: 'Getting your groups, Please wait...'
    });
    loader.present();
    this.groupsService.getmygroups();
    loader.dismiss();
   
  }

  openchat(group) {
    console.log("group",group);
    this.groupsService.getintogroup(group.groupName);
    this.navCtrl.push(GroupChatPage, { groupName: group.groupName });
  }

  removeMember(collegue){
    let index=this.contactsList.indexOf(collegue);
    if(index>=0){
      this.contactsList.splice(index,1);
      console.log("this.contactList",this.contactsList);
    }
  }

  createGroup(groupname){
    this.newgroup = {
      groupName: groupname
    }
    this.groupsService.addgroup(this.newgroup).then((res) => {
      if(res){
        this.alertService.presentToast("Group has been created",1000,"bottom");
        this.groupsService.addMember(this.contactsList,this.newgroup.groupName);
        this.navCtrl.push(GroupChatPage,{
          groupName: this.newgroup.groupName
        });
      }
    }).catch((err) => {
      alert(JSON.stringify(err));
    });
  }

}
