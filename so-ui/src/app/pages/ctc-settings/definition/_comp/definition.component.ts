import { Component,OnInit,ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompensationService } from '../../compensation.service';

import { CTCConfig } from '../../../vo/ctc-config';

const itAgeCodes = [
	{'code': "LESS-THAN-60", 'value': 'Age Less than 60 years'},
	{'code': "GREATER-THAN-60", 'value': 'Age Greater than 60 years'}
]
const codes = [
	{'code': "EPF-CONFIG", 'name': 'EPF Configuration'},
	{'code': "ESI-CONFIG", 'name': 'ESI Configuration'}
]
const details = [
	{'code': "CONTRIBUTION", 'name': 'Contribution'},
	{'code': "ADMIN", 'name': 'Admin Charges'}
]

@Component({
    selector: 'ctc-definition',
    templateUrl: './definition.component.html'
})
export class CtcDefinitionComponent implements OnInit {

    constructor(private router:Router, private route: ActivatedRoute, private _service:CompensationService){

    }

	itAgeCodes = itAgeCodes;
	codes = codes;
	details = details;
	itModified:boolean = false;
	cModified:boolean = false;
	itSlabs: any;
	configs: Array<CTCConfig>;
	fiscalYears: any;
    ngOnInit() {
		if (this.route.params['_value']['fyear']) {
			this.route.params.subscribe( (params: Params)=>{
				let id = this.fyear = params['fyear'];
				
				this._service.getMultpleDefinitions().subscribe(x=>{
					this.itSlabs = x[0];
					let epfs = x[1];
					this.fiscalYears = x[2];
					this.objModify(epfs);
				});
			});
		}
		else this._service.getFiscalYears().subscribe(x=> this.fiscalYears = x );
		
    }
	
	fyear: string;    
	yearChange($e){
		this.router.navigateByUrl("/ctc-settings/definition/"+$e);
	}
	
	objModify(epfs){
		let f = this.fiscalYears.filter(x=>x.id==this.fyear).pop();
		epfs = epfs.filter(x=>x.fiscalYearCode == f.fiscalCode);
		let i = 1;
		epfs.forEach(x=>{
			x.serial = i;
			x.details = x.configCode.split("-");
			let c = x.details.shift();
			x.conDetail = this.details.filter(x=>x.code == c).pop();
			x.catDetail = x.details.shift();
			i++;
		});
		this.configs = epfs;
	}
	
	saveMsg: any;
	updateDefinitions(){
		if(this.itModified)
		this._service.updateITslab(this.itSlabs).subscribe(y=>{
			this.itModified = false;
			this.saveMsg = {'type': 'success', 'text': "Definitions saved Successfully"}
		});
		
		if(this.cModified)
		this._service.updateDeduction(this.configs).subscribe(y=>{
			this.cModified = false;
			this.saveMsg = {'type': 'success', 'text': "Deductions saved Successfully"}
		});
	}
}


