
import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'application-detail',
    templateUrl: './application-detail.component.html'
})
export class ApplicationDetailComponent {

    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    listApplication(){
     this.router.navigateByUrl("master/application/application-list");   
    }
}
