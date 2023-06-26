
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'vacancy-list',
    templateUrl: './vacancy-list.component.html'
})
export class VacancyListComponent  implements OnInit {

    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    navigateToDetailPage(){
     this.router.navigateByUrl("master/vacancy/vacancy-detail");   
    }
}
{
}