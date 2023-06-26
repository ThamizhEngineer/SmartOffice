import { Injectable } from '@angular/core';

import { LocationAccuracy } from '@ionic-native/location-accuracy';

@Injectable()
export class GlobalService {

  constructor(public locationAccuracy: LocationAccuracy) {
   
  }

  isLoggedIn(){
    if(localStorage.getItem('isLoggedIn')){
      return localStorage.getItem('isLoggedIn')=="yes";
    }
  }

  isConnected(){
    if(localStorage.getItem('isConnected')){
      return localStorage.getItem('isConnected')=="yes";
    }
  }

  isCheckedIn(){
    let currentUser=JSON.parse(localStorage.getItem('currentUser'));
    if(currentUser.isCheckedIn){
      return currentUser.isCheckedIn == "true";
    }
  }

  lastCheckIn(){
    let currentUser=JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.lastCheckInDt;
  }

  lastCheckOut(){
    let currentUser=JSON.parse(localStorage.getItem('currentUser'));
    return currentUser.lastCheckOutDt;
  }


}
