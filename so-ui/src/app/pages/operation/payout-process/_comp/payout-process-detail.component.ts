import { Component, OnInit } from '@angular/core';
import { NG_VALIDATORS, Validator, Validators, AbstractControl, ValidatorFn } from '@angular/forms';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { PayoutProcess } from './../../../vo/payout-process';
import { PayoutProcessService } from '../payout-process.service';

@Component({
    selector: 'payout-process-detail',
    templateUrl: './payout-process-detail.component.html'
})
export class PayoutProcessDetailComponent implements OnInit {
    addScreen: boolean = true;
    payoutProcess:PayoutProcess;
    startDtStr:any;
    endDtStr:any;

    
    constructor(private router:Router,private route: ActivatedRoute,private service: PayoutProcessService){

    }

    ngOnInit() {
        this.payoutProcess = new PayoutProcess();
       

    	if (this.route.params['_value']['_id']) {
			this.addScreen = false;
			this.route.params
				.switchMap((params: Params) => this.service.getPayoutProcessById(params['_id']))
				.subscribe((x: PayoutProcess) => {
                    this.payoutProcess = x

                    this.startDtStr = this.payoutProcess.startDt.toString();
                    this.startDtStr =this.startDtStr.substring(0,10);
                    this.payoutProcess.startDt = this.startDtStr;


                    this.endDtStr = this.payoutProcess.endDt.toString();
                    this.endDtStr =this.endDtStr.substring(0,10);
                    this.payoutProcess.endDt = this.endDtStr;

                   
                  
				
				});
		}
    }
    
   
}