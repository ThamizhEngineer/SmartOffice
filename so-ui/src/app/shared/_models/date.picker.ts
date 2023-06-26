import { Component, Output, EventEmitter,  OnInit, Input, OnChanges, ElementRef, HostListener } from '@angular/core';

@Component({
    selector: 'so-datepicker',
    template: `
		<div class="input-group">
            <input class="form-control" placeholder="dd-mm-yyyy" [(ngModel)]="modelDate" ngbDatepicker #d1="ngbDatepicker" (ngModelChange)="updateModel($event);">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" (click)="d1.toggle()" type="button"><i class="fa fa-calendar"></i></button>
            </div>						  
        </div>
	`
})

export class SODatePickerComponent{
    @Input() model: any;
    modelDate= {year: '', month: '', day: ''};
	
	@Output() change = new EventEmitter();

	ngOnInit(){
		this.set();
	}

	ngOnChanges() {
        this.set();
    }
	
	constructor() {
    }
	
	set(){
		let str = this.model;
		let year:any; let month:any; let day:any; let o:any;
		if(str){
			year = new Date(str).getFullYear();
			month = new Date(str).getMonth() + 1;
			day = new Date(str).getDate();
			
		}
		this.modelDate = {year: year, month: month, day: day};
	}

	updateModel($e){
		let e = new Date($e.year+'-'+$e.month+'-'+$e.day);
		this.model = new Date(e.getTime() - (e.getTimezoneOffset() * 60000)).toJSON();
		this.change.emit(this.model);
	}
}