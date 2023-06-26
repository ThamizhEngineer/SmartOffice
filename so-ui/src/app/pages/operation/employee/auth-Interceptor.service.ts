import { HttpRequest, HttpHandler, HttpEvent, HttpInterceptor } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        request = request.clone({
            setHeaders: {
                // change the logic to replace authorization only
                Authorization: currentUser.appToken
            }
        });        
        return next.handle(request);
    }
}