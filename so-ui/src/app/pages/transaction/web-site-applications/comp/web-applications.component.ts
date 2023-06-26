import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { WebApplicationsService } from '../web-applications.service';
import { filter } from 'rxjs/operator/filter';

@Component({
    selector: '',
    templateUrl: 'web-applications.component.html'
})

export class WebApplicationsComponent implements OnInit {

    job: any = [];

    constructor(
        private route: Router,
        private service: WebApplicationsService
    ) { }

    ngOnInit() {
        this.service.getWebApplications('1').subscribe(x => {
            let jrIds = Object.keys(x);

            jrIds.forEach(element => {
                console.log(element)
                if (x == element) {
                    console.log("HIT");
                }
            });

            for (let check of jrIds) {
                let id = check;
                console.log(x.id);
            }

            console.log(jrIds);

        })
    }

    navigateToValidation(id) {
        this.route.navigate(['recruitment/web-applications/validate/'], { queryParams: { id: id } })
    }


}