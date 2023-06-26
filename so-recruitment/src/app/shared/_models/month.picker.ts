import { Component, OnInit, Input, OnChanges, ElementRef, Output, EventEmitter, HostListener } from '@angular/core';

@Component({
    selector: 'month-picker',
    template: `	
	<div fxLayout="row" fxLayoutAlign="start center" class="ml-xs mr-xs" style="width: 100%;" >
		<input placeholder="{{(config && config.placeHolder)? config.placeHolder:'Month'}}" value="{{model|date: 'MM/yyyy'}}" class="form-control" (click)="onCalendarIconClick()">
		
        <div class="month-picker" *ngIf="__monthPicker.state=='open'">
			<div class="mp-title">{{__monthPicker.selectedMonth}}, {{__monthPicker.selectedYear}}</div>
			<div class="mp-nav">
				<div class="col-mp-nav-1 text-center"><span (click)="onPrevYearSelection()"><</span></div>
				<div class="col-mp-nav-6 text-center"><span (click)="switchToYearMode()">{{__monthPicker.displayYear}}</span></div>
				<div class="col-mp-nav-1 mp-right text-center"><span (click)="onNextYearSelection()">></span></div>
			</div>
			<div class="mp-elem">
				<div class="col-mp-nav-3" *ngFor="let item of __monthPicker.selectionItems">
					<span (click)="onselectionItemClick(item)">{{item.text}}</span>
				</div>
			</div>
		</div>
	</div>
	
	`
})
export class MonthPicker implements OnInit, OnChanges {
    @Input() model: any;
    @Input() config: ImonthPickerConfig;
    @Output() mpOnModelChange  = new EventEmitter();
    __monthPicker: monthPicker;
    constructor(private _elementRef: ElementRef) {
        this.__monthPicker = new monthPicker();
		
    }
    ngOnInit() {
        if (this.model) {
			//this.model = new Date();
        }
    }
    ngOnChanges(changes:any) {
        if (this.model && this.model.month && this.model.year) {
			var v = new Date();
			v.setMonth(parseInt(this.model.month)-1);
			v.setFullYear(this.model.year);
			this.model = v;
			
			this.__monthPicker.setCurrentdate(new Date(this.model));
        }
    }
 
    onCalendarIconClick() {
        this.switchToMonthMode();
        this.__monthPicker.setCurrentdate(this.model ? new Date(this.model):new Date());
        this.__monthPicker.toggleState();
    }
    switchToYearMode() {
        this.__monthPicker.viewMode = 'y';
        this.__monthPicker.fillYearsInSelectionList();
    }
    switchToMonthMode() {
        this.__monthPicker.viewMode = 'm';
        this.__monthPicker.fillMonthsInSelectionList();
		this.__monthPicker.yearClicked = false;
    }
    onselectionItemClick(item: IDatePickerSelectionItem) {
        if (item.type == 'y') {
            this.__monthPicker.displayYear = item.value;
			this.switchToMonthMode();
			this.__monthPicker.yearClicked = true;
			
        } else if (item.type == 'm') {
            this.onSelectMonth(item);
        }
    }
    onSelectMonth(item: IDatePickerSelectionItem) {
        this.__monthPicker.displayMonth = item.text;
        this.__monthPicker.displayMonthIndex = item.value;
 
        this.__monthPicker.selectedMonth = item.text;
        this.__monthPicker.selectedMonthIndex = item.value;
        this.__monthPicker.selectedYear = this.__monthPicker.displayYear;
 
        this.model = (this.__monthPicker.selectedMonthIndex + 1) + "/01/" +this.__monthPicker.selectedYear;
        //this.model = new Date(this.__monthPicker.selectedYear, this.__monthPicker.selectedMonthIndex, 1);
        this.__monthPicker.state = "closed";
		var v = {'month': ("0" + (this.__monthPicker.selectedMonthIndex + 1)).slice(-2), 'year': this.__monthPicker.selectedYear.toString()};
        this.mpOnModelChange.next(v);
    }
 
    onPrevYearSelection() {
        this.__monthPicker.displayYear--;
        if (this.__monthPicker.viewMode == 'y') { this.__monthPicker.fillYearsInSelectionList(); }
    }
    onNextYearSelection() {
        this.__monthPicker.displayYear++;
        if (this.__monthPicker.viewMode == 'y') { this.__monthPicker.fillYearsInSelectionList(); }
    }
 
    onCancel() {
        this.__monthPicker.state = "closed";
    }
 
    @HostListener('document:click', ['$event', '$event.target'])
    public onClick(event: MouseEvent, targetElement: HTMLElement): void {
        if (!targetElement) {
            return;
        }
 
        const clickedInside = this._elementRef.nativeElement.contains(targetElement);
        if (!clickedInside) {
            if(!this.__monthPicker.yearClicked) this.__monthPicker.state = "closed";
        }
    }
}
export interface ImonthPickerConfig {
    readonly?: boolean;
    placeHolder?: string;
	type?: string;
}
export interface IDatePickerSelectionItem {
    text: string;
    value: number;
    type: string;
}
class monthPicker {
    state: string;
    selectionItems: Array<IDatePickerSelectionItem>;
    selectedMonth: string;
    selectedMonthIndex: number;
    selectedYear: number;
    displayMonth: string;
    displayMonthIndex: number;
    displayYear: number;
    viewMode: string;
	yearClicked:boolean = false;
    private months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
    constructor() {
        this.state = "closed";
        this.viewMode = 'm';
        this.fillMonthsInSelectionList();
        let currentDate = new Date();
        this.setCurrentdate(currentDate);
    }
    toggleState() {
        this.state = this.state == "closed" ? "open" : "closed";
    }
 
    fillMonthsInSelectionList() {
		this.selectionItems = [];
        this.months.forEach((v: string, i: number) => this.selectionItems.push({ text: v, value: i, type: 'm' }));
    }
    fillYearsInSelectionList() {
        this.selectionItems = [];
        for (let start = this.displayYear - 6; start <= this.displayYear + 5; start++) {
            this.selectionItems.push({ text: start.toString(), value: start, type: 'y' });
        }
    }
    setCurrentdate(currentDate: Date)
    {
        this.displayMonth = this.months[(currentDate.getMonth() - 1)];
        this.displayMonthIndex = currentDate.getMonth();
        this.displayYear = currentDate.getFullYear();
 
        this.selectedMonth = this.displayMonth;
        this.selectedMonthIndex = this.displayMonthIndex;
        this.selectedYear = this.displayYear;
    }
}