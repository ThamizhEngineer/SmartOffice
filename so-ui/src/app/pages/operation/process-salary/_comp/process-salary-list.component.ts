
import { Component, OnInit ,ViewChild, TemplateRef  } from '@angular/core';
import { Router, ActivatedRoute, Route,Params } from '@angular/router';
import { PayoutProcess } from './../../../vo/payout-process';
import { PayoutProcessService } from '../../payout-process/payout-process.service';
@Component({
    selector: 'process-salary-list',
    templateUrl: './process-salary-list.component.html'
})
export class ProcessSalaryListComponent implements OnInit {
    payoutProcess:PayoutProcess;
    rows: Array<PayoutProcess>;
    searchEmployeeId:string;
    saveMsg:string;
    message:string;
    //Month config
    mpFromConfig;
    mpSalModel;

    constructor(private router: Router,private route: ActivatedRoute, private payoutProcessService: PayoutProcessService) {

    }

    ngOnInit() {
        this.payoutProcess = new PayoutProcess();
        this.rows = new Array<PayoutProcess>();
    }

    search(){
        this.router.navigate(['/operation/payout-process/list']);
        this.payoutProcessService.processSalaries(this.payoutProcess).subscribe(_emps => {
            this.message = _emps;           
        })
    }
      
    mpOnModelChange($event) {
     this.payoutProcess.month = $event.month;
     this.payoutProcess.year = $event.year;
    }
    
}
