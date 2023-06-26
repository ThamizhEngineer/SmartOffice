import { Component,OnInit,ViewEncapsulation } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

import { CompensationService } from '../../compensation.service';

import { Compensation, CTCLine } from '../../../vo/compensation';

const formulaCodes = [
	{'headId': 1, 'code': 'FB'},
	{'headId': 2, 'code': 'AFB'},
	{'headId': 3, 'code': 'VB'},
	{'headId': 4, 'code': 'AB'},
	{'headId': 5, 'code': 'RB'}

]

@Component({
    selector: 'grade-compensation-show',
    templateUrl: './show.component.html',
	styleUrls: ['./salary.css'],
	encapsulation: ViewEncapsulation.None 
})
export class GradeCompensationShowComponent implements OnInit {

    constructor(private router:Router, private route: ActivatedRoute, private _service:CompensationService){

    }

	grades: any;
	_ID: any;
	ctcs: any;
	accordions: any;
	payoutTypes: any;
	formulaCodes: any = formulaCodes;
	regex = /\[([^)]+)\]/;
    ngOnInit() {
		
		if (this.route.params['_value']['_id']) {
			this.route.params.subscribe( (params: Params)=>{
				let id = this._ID = params['_id'];
				
				this._service.getMultpleGradeComp().subscribe(x=>{
					this.grades = x[0]; 						//All Grades
					this.payoutTypes = x[1];					//All Payouts
					
					this.objModify();
				});
			});
		}
		else this._service.getGrades().subscribe(x=> this.grades = x );
    }
	
	objModify(){
		let obj = [];
		
		this.ctcs = new Compensation();
		if(!this.ctcs.employeeCtcLines) this.ctcs.employeeCtcLines = [];
		
		for(var i in this.ctcs){
			if(i.substr(0,4) == 'head' && i.substr(-4) == 'Name' && this.ctcs[i]) {
				let h = this.ctcs['head'+i.substr(4,1)+'Total'];
				obj.push({'id': i.substr(4,1), 'name':this.ctcs[i], 'headTotal': h, 'hTotal': h, msg: null});
			}
		}
		
		if(this.ctcs.employeeCtcLines)
		this.ctcs.employeeCtcLines.forEach((x,i)=>{
			let paytype = this.payoutTypes.filter(y=>y.id == x.sPayoutTypeId).pop(); 
			x.payType = paytype;
			let f = x.formula.split('*').shift();
			x.formulaValue = f.split('/').shift();
			
			let r = this.regex.exec(x.formula).pop();
			x.formulaCalc = r;
		});
		this.accordions = obj;
	}
	
	updateHeadTotal(head){
		let c = this.ctcs.employeeCtcLines.filter(y=>y.head == head.id);
		head.msg = null;
		let t = 0;
		c.forEach(x=>{
			x.calculatedAmt = x.isLumpsum == 'N' ? this.getCalcAmt(x) : x.calculatedAmt;
			t += parseFloat(x.calculatedAmt);
			
			x.formula = x.formulaValue+'/100*['+x.formulaCalc+']';
			
		});
		head.hTotal = t.toFixed(2);
		
		head.msg = parseFloat(head.hTotal) == parseFloat(head.headTotal) ? null : {type: 'warning', text: "Total must be equal to "+head.headTotal};
	}
	
	getCalcAmt(ctcLine: any){
		let head = this.formulaCodes.filter(y => y.code == ctcLine.formulaCalc);
		let amt = 0;
		if(head.length > 0){
			head = head.pop();
			let h1 = this.accordions.filter(x => x.id == head.headId).pop();
			amt = (parseFloat(ctcLine.formulaValue) || 0)/100*parseFloat(h1.headTotal);
		}
		else{
			let fc = this.ctcs.employeeCtcLines.filter(y => y.payType.payTypeCode == ctcLine.formulaCalc);
			if(fc.length > 0){
				fc = fc.pop();
				amt = (parseFloat(ctcLine.formulaValue) || 0)/100*parseFloat(fc.calculatedAmt);
			}
		}
		return amt.toFixed(2);;
	}
	
	saveMsg: any;
	updateCompensation(){
		this.accordions.forEach(x=>{
			this.ctcs['head'+x.id+'Total'] = x.hTotal;
		});
		
		this._service.updateCTCs(this.ctcs).subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': "Compensation saved Successfully"}
		});
	}
	
	payCodes: any;
	addSplitUp(head: any){
		let fCode = this.formulaCodes.filter(x=>x.headId == head.id).pop();
		let clines = {calculatedAmt: 0, employeeCtcId: this._ID, head: head.id, formulaCalc:fCode.code,  formulaValue:0, formula: "0/100*["+fCode.code+']', payType: this.payCodes, sPayoutTypeId: this.payCodes.id};
		this.payCodes = {};
		this.ctcs.employeeCtcLines.push(clines);
	}
	
	delRow(c: any, head: any){
		let index = this.ctcs.employeeCtcLines.indexOf(c);
		this.ctcs.employeeCtcLines.splice(index, 1);
		this.updateHeadTotal(head);
	}
	
	selectGrade(id){
		this.router.navigateByUrl("/ctc-settings/grade-compensation/"+id);
	}
}
