

import { Component } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
@Component({
    selector: 'vacancy-detail',
    templateUrl: './vacancy-detail.component.html'
})
export class VacancyDetailComponent {
    constructor(private router:Router){

    }

    ngOnInit() {
    
    }
    
    listVacancy(){
     this.router.navigateByUrl("master/vacancy/vacancy-list");   
    }
}