import { Component, OnInit } from "@angular/core";
import { Router } from "@angular/router";
import { AuthenticationService } from "../_services/authentication.service";

@Component({
    selector: 'app-logout',
    template: `<h1>Logout</h1>`
})

export class LogoutComponent implements OnInit {

    constructor(private _router: Router,
        private _authService: AuthenticationService) {
    }

    ngOnInit(): void {
        this._authService.logout().subscribe(x=>{
			this.redirect();
		}, error=>{
			this.redirect();
		});
    }
	redirect(){
		localStorage.removeItem('currentUser');
		this._router.navigateByUrl('/login');
	}
}