import { Component, OnInit } from '@angular/core';
import { VacancyRequestService } from '../vacancy-request.service';
import { Router } from '@angular/router';
import { status_css } from '../../../vo/status';

@Component({
    selector: '',
    templateUrl: 'vacancy-request-list.component.html'
})

export class VacancyRequestListComponent implements OnInit {
   
    status:any=status_css;
    vacancyReq:any=[];
    pageSize :number = 10
    page :number = 1;
   
    constructor(
        private service:VacancyRequestService,
        private route:Router
    ) { }

    ngOnInit() {
        this.service.getVacancyrequests().subscribe(x=>{
            this.vacancyReq=x;
        })
     }

     createNew(){
        this.route.navigate(['/recruitment/job-request/new'],{queryParams:{view:'HR'}});        
     }

     navigateToDetailPage(id){
        this.route.navigate(['/recruitment/job-request/view'],{queryParams:{id:id,view:'HR'}});        
     }
}