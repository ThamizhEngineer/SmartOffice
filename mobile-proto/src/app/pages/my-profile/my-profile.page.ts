import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.page.html',
  styleUrls: ['./my-profile.page.scss'],
})
export class MyProfilePage implements OnInit {
  public person: {name: string, company: string, birthdate?: number};
  dob: any;
  age: any;
  showProfile: boolean;
  constructor() {

    this.person = {name: undefined, company: undefined, birthdate: undefined};
    this.dob = undefined;
   }

  ngOnInit() {
    let person = JSON.parse(localStorage.getItem('PERSON'));
    if (person){
      this.person = person;
      this.age = this.getAge(this.person.birthdate);
      this.dob = new Date(this.person.birthdate).toISOString();
    }else{
      this.person ={
        name:"Hoko",
        company : 'CIMB',
        birthdate: 1234567
      }
    }
  }
  
  reset(){
    this.person = {name: null, company: null, birthdate: null};
    this.dob = null;
    this.showProfile = false;
  }

  save(){
    this.person.birthdate = new Date(this.dob).getTime();
    this.age = this.getAge(this.person.birthdate);
    this.showProfile = true;
    localStorage.setItem('PERSON', JSON.stringify(this.person));
  }
  getAge(birthdate){
    let currentTime = new Date().getTime();
     return ((currentTime - birthdate)/31556952000).toFixed(0);
  }

}
