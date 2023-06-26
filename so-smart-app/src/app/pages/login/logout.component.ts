import { Component, OnInit } from '@angular/core';
import { Router, Event, NavigationEnd } from '@angular/router';
import { AuthenticationService } from './../../services/authentication.service';
import { UtilsService } from 'src/app/services/utils.service';

@Component({
  selector: 'app-logout',
  template: ``,
})
export class LogoutComponent {
  constructor(private _router: Router, private _authService: AuthenticationService,private utilsService: UtilsService) { 

    this._router.events.subscribe(
      (event: Event) => {
		  
        if (event instanceof NavigationEnd && event.url == "/logout") {
			this.lodadLOgout();
        }
      });
   }

   lodadLOgout() {
    this.utilsService.presentLoader().then((x) => {
      this.logout();
    });
  }

  redirect(){
    localStorage.clear();
    this._router.navigate(['/login']);
  }

  logout(){
    this._authService.logout().subscribe(x=>{
      this.redirect();
      this.utilsService.presentAlertPositive("You have been logged out");
      this.utilsService.dismissLoader();
    }, error=>{
      let message = this.utilsService.getErrorStatus(error.status);
      this.utilsService.dismissLoader();
      this.utilsService.presentAlert(message);
      this.redirect();
    });
  }

}
