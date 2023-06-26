
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'application-list',
    templateUrl: './application-list.component.html'
})
export class ApplicationListComponent implements OnInit {

    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    navigateToDetailPage(){
     this.router.navigateByUrl("master/application/application-detail");   
    }
}


