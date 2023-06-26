import { Component, OnInit } from '@angular/core';
import { Router, Event, NavigationEnd } from '@angular/router';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-logout',
  template: `Logout Redirecting...If not, Please kill the app`,
})
export class LogoutComponent {
  constructor(private _router: Router, private _authService: AuthenticationService) { 

    this._router.events.subscribe(
      (event: Event) => {
		  
        if (event instanceof NavigationEnd && event.url == "/logout") {
			this.logout();
        }
      });
   }
  

  redirect(){
    localStorage.clear();
    this._router.navigate(['/login']);
  }



  logout(){
    this._authService.logout().subscribe(x=>{
      this.redirect();
    }, error=>{
      this.redirect();
    });
  }

}
