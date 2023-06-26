import { Component, OnInit, Input } from "@angular/core";

@Component({
    selector: 'flag-alert',
    template: `
		<div *ngIf="message" class="alert alert-{{message.type}}">
			<button type="button" class="close" (click)="close()"><i class="fa fa-close text-danger"></i></button>
			<span>{{message.text}}</span>
		</div>
	`
})

export class FlagComponent implements OnInit {
    @Input() message: any;
    constructor() {
    }

    ngOnInit() {
        
    }

	ngOnChanges(){
		if(this.message) {
			setTimeout(()=>this.message = false, 5000);
		}
	}
  
    close() {
        this.message = false;
    }
}