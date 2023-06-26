import { Component, OnInit } from '@angular/core';
import { Router, NavigationStart, NavigationEnd } from '@angular/router';

import { NgbDatepickerConfig, NgbDateParserFormatter } from '@ng-bootstrap/ng-bootstrap';
import { DateParserService } from '../app/shared/_services/date-parser.service';



@Component({
    selector: 'body',
    templateUrl: './app.component.html',
    providers: [{provide: NgbDateParserFormatter, useClass: DateParserService}]

})
export class AppComponent implements OnInit {
    title = 'app';

    loaded: boolean = false;

    constructor(private _router: Router) {

    }

    ngOnInit() {

    }
    ngAfterViewInit() {
        setTimeout(() => {
            this.loaded = true;
        });
    }
}