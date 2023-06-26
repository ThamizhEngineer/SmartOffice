import { Component } from '@angular/core';
import { NavController, NavParams } from 'ionic-angular';

@Component({
  selector: 'page-expense-tracker',
  templateUrl: 'expense-tracker.component.html',
})
export class ExpenseTrackerPage {

  expenses:any=[];

  constructor(public navCtrl: NavController, public navParams: NavParams) {
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ExpenseTrackerPage');
    this.expenses=[
       { 'uploadDate': '12-04-2018', 'uploadAmount': '2000' , 'uploadStatus':'Pending'},
       { 'uploadDate': '10-04-2018', 'uploadAmount': '3000' , 'uploadStatus':'Rejected'},
       { 'uploadDate': '01-04-2018', 'uploadAmount': '4500' , 'uploadStatus':'Approved'}
    ];
  }

}
