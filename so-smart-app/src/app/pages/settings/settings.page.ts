import { Component, OnInit } from '@angular/core';
import { UtilsService } from 'src/app/services/utils.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-settings',
  templateUrl: './settings.page.html',
  styleUrls: ['./settings.page.scss'],
})
export class SettingsPage implements OnInit {
   isDark:boolean=false;
  constructor(private utils:UtilsService,private router: Router) { }

  ngOnInit() {
    console.log("Simple")
  }
 
  checkIsDark(){
    if (this.isDark==false) {
      this.enableDark()
    }else{
      this.enableLight();
    }
  }

  enableDark(){
    this.utils.enableDarkMode();
  }

  enableLight(){
    this.utils.enableLightMode();
  }

  startLogout(){
    this.router.navigate(['/logout']);
  }
}
