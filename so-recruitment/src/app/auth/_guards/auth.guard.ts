import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { AuthenticationService } from "../_services/authentication.service";
import { Observable } from "rxjs/Rx";

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private _router: Router, private _authService: AuthenticationService) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {

		return this._authService.validate().map(data=>{
			if(data) return true
			else this.redirect(state);
		}).catch(() => {
			this.redirect(state);
			return Observable.of(false);
		});
        
    }
	redirect(state: RouterStateSnapshot){
		this._router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
		return false;
	}
}