import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NavController, IonSearchbar, ModalController } from '@ionic/angular';

@Component({
  selector: 'app-subpage-header',
  templateUrl: './subpage-header.component.html',
  styleUrls: ['./subpage-header.component.scss'],
})
export class SubpageHeaderComponent implements OnInit {

  showSearchBar:boolean=false;
  @Input('title') title: any;
  @ViewChild('searchBar') searchBar : IonSearchbar;

  constructor(public navController: NavController, public modalCtrl: ModalController) { }

  ngOnInit() {}

  navigateToBack(){
	let data={};
    if(this.modalCtrl) this.modalCtrl.dismiss(data);
    if(this.navController) this.navController.back();
  }

}
