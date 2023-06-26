import { Injectable } from '@angular/core';
import { Events } from 'ionic-angular';
import firebase from 'firebase';


@Injectable()
export class GroupsService {

  firegroup = firebase.database().ref('/groups');
  mygroups: Array<any> = [];
  groupsMessageArr: Array<any>=[];
  currentgroup: Array<any> = [];
  currentgroupname;
  groupmsgs;

  constructor(public events: Events) {
    console.log('Hello GroupsProvider Provider');
  }

  addgroup(newGroup) {
    var promise = new Promise((resolve, reject) => {
      this.firegroup.child(firebase.auth().currentUser.uid).child(newGroup.groupName).set({
        msgboard: '',
        owner: firebase.auth().currentUser.uid
      }).then(() => {
        resolve(true);
        }).catch((err) => {
          reject(err);
      })
    });
    return promise;
  }

  getmygroups() {
    this.firegroup.child(firebase.auth().currentUser.uid).once('value', (snapshot) => {
      this.mygroups = [];
      if(snapshot.val() != null) {
        var temp = snapshot.val();
        for (var key in temp) {
          var newgroup = {
            groupName: key,
            groupimage: temp[key].groupimage
          }
          this.mygroups.push(newgroup);
        }
      }
      this.events.publish('newgroup');
    })
    
  }

  getintogroup(groupname) {
    if (groupname != null) {
      this.firegroup.child(firebase.auth().currentUser.uid).child(groupname).once('value', (snapshot) => {
        if (snapshot.val() != null) {
          var temp = snapshot.val().members;
          this.currentgroup = [];
          for (var key in temp) {
            this.currentgroup.push(temp[key]);
          }
          this.currentgroupname = groupname;
         this.events.publish('gotintogroup');
        }
      })
    }
  }

  getownership(groupname) {
    var promise = new Promise((resolve, reject) => {
      this.firegroup.child(firebase.auth().currentUser.uid).child(groupname).once('value', (snapshot) => {
        var temp = snapshot.val().owner;
        if (temp == firebase.auth().currentUser.uid) {
          resolve(true);
        }
        else {
          resolve(false);
        }
      }).catch((err) => {
          reject(err);
      })
    })
    return promise;
  }


  addMember(newMember,groupName) {
    console.log("newMember",newMember);
    this.currentgroupname=groupName;
    console.log("groupname",this.currentgroupname);
    for(let member of newMember){
      this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).child('members').push(member).then(() => {
        this.firegroup.child(member.uid).child(this.currentgroupname).set({
          owner: firebase.auth().currentUser.uid,
          msgboard: ''
        }).catch((err) => {
          console.log(err);
        })
      })
    }
    this.getintogroup(this.currentgroupname);
  }

  deletemember(member) {           
    this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname)
      .child('members').orderByChild('uid').equalTo(member.uid).once('value', (snapshot) => {
        snapshot.ref.remove().then(() => {
          this.firegroup.child(member.uid).child(this.currentgroupname).remove().then(() => {
            this.getintogroup(this.currentgroupname);
          })
        })
      })
  }

  getgroupmembers(groupname) {
    this.firegroup.child(firebase.auth().currentUser.uid).child(groupname).once('value', (snapshot) => {
      var tempdata = snapshot.val().owner;
      this.firegroup.child(tempdata).child(groupname).child('members').once('value', (snapshot) => {
        var tempvar = snapshot.val();
        for (var key in tempvar) {
          this.currentgroup.push(tempvar[key]);
        }
      })
    })
    this.events.publish('gotmembers');
  }

  leavegroup() {
    return new Promise((resolve, reject) => {
      this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).once('value', (snapshot) => {
      var tempowner = snapshot.val().owner;
      this.firegroup.child(tempowner).child(this.currentgroupname).child('members').orderByChild('uid')
        .equalTo(firebase.auth().currentUser.uid).once('value', (snapshot) => {
          snapshot.ref.remove().then(() => {
            this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).remove().then(() => {
              resolve(true);
            }).catch((err) => {
              reject(err);
            })
          }).catch((err) => {
            reject(err);
          })
      })
    })
    }) 
  }

  deletegroup() {
    return new Promise((resolve, reject) => {
      this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).child('members').once('value', (snapshot) => {
        var tempmembers = snapshot.val();
  
        for (var key in tempmembers) {
          this.firegroup.child(tempmembers[key].uid).child(this.currentgroupname).remove();
        }

        this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).remove().then(() => {
          resolve(true);
        }).catch((err) => {
          reject(err);
        })
        
      })
    })
  }

  addgroupmsg(newmessage) {
    return new Promise((resolve) => {

    
    this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).child('owner').once('value', (snapshot) => {
      var tempowner = snapshot.val();
      this.firegroup.child(firebase.auth().currentUser.uid).child(this.currentgroupname).child('msgboard').push({
        sentby: firebase.auth().currentUser.uid,
        displayName: firebase.auth().currentUser.displayName,
        photoURL: firebase.auth().currentUser.photoURL,
        message: newmessage,
        timestamp: firebase.database.ServerValue.TIMESTAMP
      }).then(() => {
        if (tempowner != firebase.auth().currentUser.uid) {
          this.firegroup.child(tempowner).child(this.currentgroupname).child('msgboard').push({
            sentby: firebase.auth().currentUser.uid,
            displayName: firebase.auth().currentUser.displayName,
            photoURL: firebase.auth().currentUser.photoURL,
            message: newmessage,
            timestamp: firebase.database.ServerValue.TIMESTAMP
          })
        }
        var tempmembers = [];
        this.firegroup.child(tempowner).child(this.currentgroupname).child('members').once('value', (snapshot) => {
          var tempmembersobj = snapshot.val();
          for (var key in tempmembersobj)
            tempmembers.push(tempmembersobj[key]);
        }).then(() => {
          let postedmsgs = tempmembers.map((item) => {
            if (item.uid != firebase.auth().currentUser.uid) {
              return new Promise((resolve) => {
                this.postmsgs(item, newmessage, resolve);
              })
            }
          })
          Promise.all(postedmsgs).then(() => {
            this.getgroupmsgs(this.currentgroupname);
            resolve(true);
          })
        })
      })
    })
    })  
  }

  postmsgs(member, msg, cb) {
    this.firegroup.child(member.uid).child(this.currentgroupname).child('msgboard').push({
            sentby: firebase.auth().currentUser.uid,
            displayName: firebase.auth().currentUser.displayName,
            photoURL: firebase.auth().currentUser.photoURL,
            message: msg,
            timestamp: firebase.database.ServerValue.TIMESTAMP
    }).then(() => {
      cb();
    })
  }

  getgroupmsgs(groupname) {
    this.firegroup.child(firebase.auth().currentUser.uid).child(groupname).child('msgboard').on('value', (snapshot) => {
      var tempmsgholder = snapshot.val();
      this.groupmsgs = [];
      for (var key in tempmsgholder){
        this.groupmsgs.push(tempmsgholder[key]);
        console.log("this.groupmsgs in service",this.groupmsgs);
      } 
      this.events.publish('newgroupmsg');
    })
  }

  getAllGroupsLastMessage() {
    this.firegroup.child(firebase.auth().currentUser.uid).once('value', (snapshot) => {
      this.groupsMessageArr = [];
      if(snapshot.val() != null) {
        let jobData = snapshot.val();
        for (var jobKey in jobData) {
          this.firegroup.child(firebase.auth().currentUser.uid).child(jobKey).child('msgboard').limitToLast(1).on('value',(snap)=>{
            if(snap.val() !=null){
              var msgData = snap.val();
              var tempObj={};
              for(var msgKey in msgData){
                console.log("msgData",msgData);
                tempObj=msgData[msgKey];
                console.log("tempObj",tempObj);
              }
              var newgroup = {
                groupName: jobKey,
                groupimage: jobData[jobKey].groupimage,
                displayName: msgData[msgKey].displayName,
                message: msgData[msgKey].message,
                sentby: msgData[msgKey].sentby,
                timestamp: msgData[msgKey].timestamp
              };
              console.log("newgroup",newgroup);
              this.groupsMessageArr.push(newgroup);
            }else{
              newgroup = {
                groupName: jobKey,
                groupimage: jobData[jobKey].groupimage,
                displayName: "",
                message: "No Message to display",
                sentby: "",
                timestamp: ""
              };
              console.log("newgroup",newgroup);
              this.groupsMessageArr.push(newgroup);
            }
          });
        }
      }
      this.events.publish('groupmessage');
    });
    
  }

}
