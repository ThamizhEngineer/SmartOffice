import { Component, ViewChild, TemplateRef, Input, Output, EventEmitter  } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'confirm-delete',
    template: `
		<span *ngIf="type=='icon';else temButton"><a href="javascript:void(0)" data-toggle="tooltip" data-original-title="Close" (click)="openConfirm()"> <i class="fa fa-close text-danger"></i> </a></span>
		
		<ng-template #temButton>
			<button type="submit" class="btn btn-warning waves-effect waves-light m-r-15" (click)="openConfirm()">Delete</button>
		</ng-template>
		<ng-template #confirm let-c="close">
			<div class="modal-header">
				<h4 class="modal-title">Are you sure want to Delete?</h4>
				<button type="button" class="close" aria-label="Close" (click)="c()">
				  <span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-footer">
				<button type="submit" class="btn btn-primary waves-effect waves-light m-r-15" (click)="yesClicked()">Yes</button>
				<button type="submit" class="btn btn-outline-dark waves-effect waves-light" (click)="c()">Cancel</button>
			</div>
		</ng-template>
	`
})

export class ConfirmDeleteComponent{
    @Input() type: any;
	
	@Output() confirmed = new EventEmitter<Boolean>();
	
	@ViewChild('confirm') confirm: TemplateRef<any>;
    constructor(private modalService: NgbModal) {
    }
	
	yesClicked() {
		this.confirmed.emit(true);
		this.modalReference.close();
	}
	
	modalReference: any;
	openConfirm(){
		this.modalReference = this.modalService.open(this.confirm, {size: 'sm'});		
	}
}