import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import { AngularFireDatabase } from 'angularfire2/database';

import { Events } from 'ionic-angular';

import firebase from 'firebase';

@Injectable()
export class ChatService {

  collegueschats=firebase.database().ref('/collegueschats');
  fireusers= firebase.database().ref('/allusers');
  collegue: any;
  collegueMessages = [];
  allCollegueMessages= [];
  lastMessage={};
  lastMessageArr:Array<any>=[];
  tempKeys:any;

  constructor(public afireauth: AngularFireAuth,
              public db: AngularFireDatabase,
              public events: Events) {
    console.log('Hello ChatProvider Provider');
  }

  initializeMessage(collegue) {
    this.collegue = collegue;
  }

  addnewmessage(msg) {
    if(this.collegue){
      var promise = new Promise((resolve, reject) => {
        this.collegueschats.child(firebase.auth().currentUser.uid).child(this.collegue.uid).push({
          sentby: firebase.auth().currentUser.uid,
          message: msg,
          timestamp: firebase.database.ServerValue.TIMESTAMP,
          sentto: this.collegue.uid
        }).then(() => {
          this.collegueschats.child(this.collegue.uid).child(firebase.auth().currentUser.uid).push().set({
            sentby: firebase.auth().currentUser.uid,
            message: msg,
            timestamp: firebase.database.ServerValue.TIMESTAMP,
            sentto: this.collegue.uid
          }).then(() => {
            resolve(true);
            }).catch((err) => {
              reject(err);
          })
        })
      })
      return promise;
    }
  }

  getCollegueMessages() {
    let temp;
    this.collegueschats.child(firebase.auth().currentUser.uid).child(this.collegue.uid).on('value', (snapshot) => {
      this.collegueMessages = [];
      temp = snapshot.val();
      for (var tempkey in temp) {
        this.collegueMessages.push(temp[tempkey]);
        console.log("this",this.collegueMessages);
      }
      this.events.publish('newmessage');
    })
  }

  getAllCollegueMessages(){
    let temp ;
    this.collegueschats.child(firebase.auth().currentUser.uid).on('value',(snapshot)=>{
      this.allCollegueMessages=[];
      temp = snapshot.val();      
      for (var tempkey in temp) {
        console.log("tempkey",tempkey);
        this.getLastMessage(tempkey);
        setTimeout(()=>{
          this.allCollegueMessages.push(temp[tempkey]);
        },500);
      }
      this.events.publish('colleguemessage');
    })
  }

  getLastMessage(uid){
    this.collegueschats.child(firebase.auth().currentUser.uid).child(uid).limitToLast(1).once('value',(snapshot)=>{
      this.lastMessageArr=[];
      if(snapshot.val()!=null)
      var msgData = snapshot.val();
      for (var msgKey in msgData) {
        msgData[msgKey].uid=snapshot.key;
        this.fireusers.orderByChild('uid').equalTo(snapshot.key).once('value',(snap)=>{
          var userData=snap.val();
          let tempobj={};
          for(var userKey in userData){
            console.log("userdata",userData);
            tempobj=userData[userKey].displayName;
          }
          msgData[msgKey].displayName=tempobj;
          this.lastMessageArr.push(msgData[msgKey]);
        });
      }
    });
  }
}
