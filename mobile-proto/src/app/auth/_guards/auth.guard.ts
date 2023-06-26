import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate  {

  constructor(
    private _router: Router,
    private _authService: AuthenticationService){
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean{
    return this._authService.validate().pipe(map(data=>{
      if (data && data.appToken) {
        return true;
    }
    else this.redirect(state);
    },err=>{
      this.redirect(state);
    }));
  }

  redirect(state: RouterStateSnapshot){
    this._router.navigate(['/auth/login'],{ queryParams:{ returnUrl: state.url} });
    return false;
  }
  
}
