import { Component, OnInit } from '@angular/core';
import { VacancyRequestService } from '../vacancy-request.service';
import { Router } from '@angular/router';
import { status_css } from '../../../vo/status';

@Component({
    selector: '',
    templateUrl: 'vacancy-request-dir-list.component.html'
})

export class VacancyRequestDirListComponent implements OnInit {
   
    status:any=status_css;
    vacancyReq:any=[];
   
    constructor(
        private service:VacancyRequestService,
        private route:Router
    ) { }

    ngOnInit() {
        this.service.getVacancyrequests().subscribe(x=>{
            this.vacancyReq=x;
        })
     }

     navigateToDetailPage(id){
        this.route.navigate(['/recruitment/job-request/view'],{queryParams:{id:id,view:'DIR'}});
     }
}