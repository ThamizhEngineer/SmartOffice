import { Injectable } from '@angular/core';
import { AngularFireAuth } from 'angularfire2/auth';
import { AngularFireDatabase } from 'angularfire2/database';

import firebase from 'firebase';

@Injectable()
export class UserService {
  userId: string;
  user: any=null;
  searchUser: any= null;
  firedata= firebase.database().ref('/allusers');
  constructor(public afireauth: AngularFireAuth,
              public db: AngularFireDatabase) {
    console.log('ChatServiceProvider Provider');
    this.afireauth.authState.subscribe((response)=>{
      this.user=response;
      console.log("this.user",response.uid);
    });
  }

/* *
   * After successful Login , the system will add a new user to the firebase database
   * Called from - homepage.ts
   * Inputs - The new user object containing the email, password taken from localstorage
   * Outputs - Promise.
   * */

  addUser(newuser){
    var promise = new Promise((resolve, reject) => {
      this.afireauth.auth.createUserWithEmailAndPassword(newuser.email, newuser.password).then(() => {
        this.afireauth.auth.currentUser.updateProfile({
          displayName: newuser.nickname,
          photoURL:''
        }).then(() => {
          this.firedata.child(this.afireauth.auth.currentUser.uid).set({
            uid: this.afireauth.auth.currentUser.uid,
            displayName: newuser.nickname,
            email:newuser.email,
            status: "online"
          }).then(() => {
            resolve({ success: true });
            }).catch((err) => {
              reject(err);
          })
          }).catch((err) => {
            reject(err);
        })
      }).catch((err) => {
        reject(err);
      })
    })
    return promise;
  }

  loginUser(user){
    var promise=new Promise((resolve,reject)=>{
      this.afireauth.auth.signInWithEmailAndPassword(user.email,user.password).then((newUser)=>{
        console.log("1",newUser);
        this.searchUser=newUser;
        resolve(this.searchUser);
      }).catch((err)=>{
        reject(err);
      })
    });
    return promise;
  }

  getAllUsers(){
    var promise=new Promise((resolve,reject)=>{
      this.firedata.orderByChild('email').once('value',(snapshot)=>{
        let userdata=snapshot.val();
        let temparr=[];
        for(var key in userdata){
          temparr.push(userdata[key]);
        }
        console.log("temparr",temparr);
        resolve(temparr);
      }).catch((err) => {
        reject(err);
      })
   })
   return promise;
  }

  getUser(email){
    var promise=new Promise((resolve,reject)=>{
      this.firedata.orderByChild('email').equalTo(email).once('value',(snapshot)=>{
        let userdata=snapshot.val();
        let tempobj={};
        for(var key in userdata){
          tempobj=userdata[key];
        }
        resolve(tempobj);
      }).catch((err)=>{
        reject(err);
      })
    })
    return promise;
  }

  getUserByUID(uid){
    var promise=new Promise((resolve,reject)=>{
      this.firedata.orderByChild('uid').equalTo(uid).once('value',(snapshot)=>{
        let userdata=snapshot.val();
        let tempobj={};
        for(var key in userdata){
          tempobj=userdata[key];
        }
        resolve(tempobj);
      }).catch((err)=>{
        reject(err);
      })
    })
    return promise;
  }




  getCurrentUserId(): string{
    return (this.user !==null) ? this.user.uid : null;
  }

  getCurrentUser():any{
    return (this.user !==null) ? this.user : null;
  }

  signout(){
    this.afireauth.auth.signOut();
  }

  updateOnConnect(){
    let connectedRef=firebase.database().ref('.info/connected');
    connectedRef.on("value",function(snapshot){
      console.log("snap",snapshot);
    
      if(snapshot.val()===true){
        //this.updateStatus('online');
        console.log("connected");
      }else{
        console.log("Disconnected");
       // this.updateStatus('online');
      }
    
    });
    //return this.fireInstance.child('info/connected').

    // return this.db.object('.info/connected')
    //               .do(connected => {
    //                 let status = connected.$value ? 'online' : 'offline';
    //                 this.updateStatus(status);
    //             })
    //             .subscribe();
  }

  updateStatus(status:string){
    if (localStorage.getItem('uid')){
      this.db.object(`allusers/` + localStorage.getItem('uid')).update({ status: status }); 
    }
   
  }



  
}
