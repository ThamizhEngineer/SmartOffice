import { Component, OnInit, ViewChild, TemplateRef, Output, EventEmitter, Input, ViewEncapsulation } from '@angular/core';
import {  AutoCompleteService } from './../_services/autocomplete.service';
import { Employee } from '../../pages/vo/employee'
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { isNgTemplate } from '@angular/compiler';


@Component({
    selector: 'autocomplete',
	templateUrl: './autocomplete.component.html',
	styles: ['.pace-active { display: none; }'],
	providers: [AutoCompleteService],
})
export class AutoCompeleteComponent implements OnInit {
	
	employeeList:[Employee]
	@Input() placeholder: string;
	@Input() value:string;
	@Output() empDetail = new EventEmitter();
	formatter = (x: {empName: string}) => x.empName;
	profileAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.employeeList : this.employeeList.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.empCode.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
	constructor(
		private service:AutoCompleteService
	){}
	ngOnInit() {	
	this.employeeList=[new Employee];
		this.service.getEmployees().subscribe(x=>{
			this.employeeList=x;
		})	
	}

	projectSelected($event){
		this.empDetail.emit({"id":$event.item.id,
		"empCode":$event.item.empCode,
		"empName":$event.item.empName,
		"firstName":$event.item.firstName,
		"lastName":$event.item.lastName,
		"deptId":$event.item.deptId,
		"deptName":$event.item.deptName});
	}

}