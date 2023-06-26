import { Component, OnInit } from '@angular/core';
import { VacancyDecriptionService } from '../vacancy-description.service';
import { Router } from '@angular/router';


@Component({
    selector: '',
    templateUrl: 'vacancy-description-list.component.html'
})

export class vacancyDescriptionListComponent implements OnInit {
   
   list:any[];
   pageSize :number = 10
   page :number = 1;

    constructor(
        private route:Router,
        private service:VacancyDecriptionService
    ) { }

    ngOnInit() {
        this.service.getVacancyDescriptions().subscribe(x=>{
            this.list=x;
        })
     }

     navigateToDetailPage(id:number){
         console.log(id);
         this.route.navigateByUrl("/recruitment/job-description/view/"+id);
     }

}