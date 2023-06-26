
import { Component, OnInit ,ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Route,Params } from '@angular/router';
import { PayoutProcess } from './../../../vo/payout-process';
import { PayoutProcessService } from '../payout-process.service';

@Component({
    selector: 'payout-process-list',
    templateUrl: './payout-process-list.component.html'
})
export class PayoutProcessListComponent implements OnInit {

    rows: Array<PayoutProcess>;
    page :number = 1;
    pageSize :number = 10;
    constructor(private router: Router,private route: ActivatedRoute, private service: PayoutProcessService) {

    }

    ngOnInit() {

        this.rows = new Array<PayoutProcess>();

      
        this.service.getPayoutProcess().subscribe(_emps => {
            this.rows = _emps;
            console.log(this.rows);
        })

    }

    navigateToDetailPage(id: number) {
        this.router.navigateByUrl("/operation/payout-process/detail/" + id);
    }

   

    
}
