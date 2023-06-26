import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { NavController, IonSearchbar, PopoverController, Events } from '@ionic/angular';
import { CommonPopoverComponent } from '../common-popover/common-popover.component';
import { Router } from '@angular/router';
import { CommonService } from 'src/app/services/common.service';
import { ChildPopoverComponent } from '../child-popover.component';

@Component({
  selector: 'app-common-header',
  templateUrl: './common-header.component.html',
  styleUrls: ['./common-header.component.scss']
})
export class CommonHeaderComponent implements OnInit {

  showSearchBar:boolean=false;
  isDriver:boolean;
  isParent:boolean;
  studentList:any;
  studentName:string;
  student:any;
  user:any;
  @Input('title') title: any;
  @ViewChild('searchBar') searchBar : IonSearchbar;
 
  constructor(public navController: NavController,
              public popoverCtrl: PopoverController,
              public commonService: CommonService,
              public router: Router,
              public events: Events) { }

  ngOnInit() {
	  
  }

  navigateToBack(){
    this.navController.pop();
  }

  focusButton(){
    setTimeout(() => {
      this.searchBar.setFocus;
    }, 500);
  }

  searchTerm: string = "";
  searchText(e){
    
  }

  onCancel(e){
    this.showSearchBar=false;
  }

  checkBlur(e){
    this.showSearchBar=false;
  }

  handleRedirect(){
	this.router.navigate(['/shopping/', this.searchTerm]);
  }
  
  async presentPopover(ev: any) {
    const popover = await this.popoverCtrl.create({
      component: CommonPopoverComponent,
      event: ev,
      translucent: false
    });
    return await popover.present();
  }

  navigateToNotification(){
    this.router.navigate(['/notification']);
  }

  async presentChildPopover(ev:any){
    const popover = await this.popoverCtrl.create({
      component: ChildPopoverComponent,
      event: ev,
      cssClass: 'child-popover',
      translucent: true,
      showBackdrop: false
    });
    return await popover.present();
  }

}
