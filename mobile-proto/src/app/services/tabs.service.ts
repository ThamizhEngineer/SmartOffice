import { Injectable } from '@angular/core';
import { Router, NavigationEnd } from '@angular/router';
import { Platform } from '@ionic/angular';
import { filter } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TabsService {

  hideTabBarPages:any=[
    '/home',
    '/overspeed',
    '/route',
    '/schedule',
    '/track',
    '/start-trip',
    '/notification'
  ];

  constructor(private router: Router, private platform: Platform) { 
    this.platform.ready().then(()=>{
      this.navEvents();
    });
  }

  navEvents(){
    this.router.events.pipe(filter(e => e instanceof NavigationEnd)).subscribe((e: any) => {
      this.showHideTabs(e);
    });
  }

  private showHideTabs(e: any) {
    console.log('e',e);
    setTimeout(()=> this.hideTabBarPages.indexOf(e.url) > -1 ? this.hideTabs() : this.showTabs(), 300);
  }

  public hideTabs() {
    const tabBar = document.getElementById('myTabBar');
    tabBar.style.display = 'none';
  }
  
  public showTabs() {
    const tabBar = document.getElementById('myTabBar');
    tabBar.style.display = 'flex';
  }
}
