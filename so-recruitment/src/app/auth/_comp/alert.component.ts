import { Component, OnInit } from "@angular/core";
import { AlertService } from "../_services/index";

@Component({
    selector: 'app-alert',
    template: `
		<div *ngIf="message" class="alert alert-{{message.type}}">
			<button type="button" class="close" (click)="close()"><i class="fa fa-close text-danger"></i></button>
			<span>{{message.text}}</span>
		</div>
	`
})

export class AlertComponent implements OnInit {
    message: any;
    constructor(private _alertService: AlertService) {
    }

    ngOnInit() {
        this._alertService.getMessage().subscribe(message => {
            this.message = message;
        });
    }

    close() {
        this.message = false;
    }
}