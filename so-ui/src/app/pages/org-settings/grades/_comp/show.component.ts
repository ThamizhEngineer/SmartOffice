import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { SortablejsOptions } from 'angular-sortablejs';

import { GradesService } from './../grades.service';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

import { Grade } from '../../../vo/grade';
import { Designation } from '../../../vo/designation';

@Component({
    selector: 'grades-show',
	styleUrls: ['./sortable.css'],
    templateUrl: './show.component.html'
})
export class GradesShowComponent implements OnInit {

	@ViewChild('cdelete') cdelete: TemplateRef<any>;
	@ViewChild('cedit') cedit: TemplateRef<any>;
	@ViewChild('dedit') dedit: TemplateRef<any>;
    
	grades: any;
	designations: any;
	
	options: SortablejsOptions;
	gradeCounts: string;
	gModified: boolean = false;
   
	constructor(private router:Router, private modalService: NgbModal, private _service: GradesService){
		 this.options = {
			onUpdate: (event: any) => {
			  this.gModified = true;
			  this.grades.forEach((x,i)=>{
				  x.gradeOrder = (i+1).toString();
			  });
			}
		 };
    }
	
    ngOnInit() {
		
		this.getData();
		
    }
	
	getData(){
		this._service.getMultipleData().subscribe(x=>{
			this.grades = x[0];
			this.gradeCounts = this.grades.length;
			this.objModify(x[1]);
		});
	}
	
	objModify(desig){
		desig.forEach(x=>{
			x.grade = this.grades.filter(y=>y.id==x.gradeId).pop();
		});
		this.designations = desig;
		console.log(this.designations);
	}
	
	modalReference:any = null;
	modalData: any;
	dType: string;
	deleteType(modal: any, type: string){
		this.modalData = modal;
		this.dType = type;
		this.modalReference = this.modalService.open(this.cdelete);
	}
	
	saveMsg: any;
	confirmDelete(data, dType){
		let x = dType == 'grade' ? this._service.deleteGrade(data.id) : this._service.deleteDesignation(data.id);
		let y = dType == 'grade' ? "Grade" : "Designation";
		
		x.subscribe(x=>{
			this.saveMsg = {'type': 'success', 'text': y+" Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		}, e=> {
			this.saveMsg = {'type': 'success', 'text': y+" Deleted Successfully"}
			this.getData();
			this.modalReference.close();
		});
	}
	
	isEdit: boolean = false;
	editGrade(grade: Grade){
		this.modalData = grade;
		this.isEdit = true;
		this.modalReference = this.modalService.open(this.cedit);
	}
	
	newGrade(){
		this.modalData = new Grade();
		this.isEdit = false;
		this.modalReference = this.modalService.open(this.cedit);
	}
	
	updateGrade(data: Grade){
		if(data.id){
			this._service.updateGrade(data.id, data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Grade updated Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}else{
			this._service.createGrade(data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Grade Created Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}
	}
	
	updateGradeOrder(){
		this._service.updateGradeOrder(this.grades).subscribe(y=>{
			this.gModified = false;
			this.saveMsg = {'type': 'success', 'text': "Grade Ordered Successfully"};
		},e=> {
			this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
		});
	}

	editDesignation(designation: Designation){
		this.modalData = designation;
		this.isEdit = true;
		this.modalReference = this.modalService.open(this.dedit);
	}
	
	newDesignation(){
		this.modalData = new Designation();
		this.isEdit = false;
		this.modalReference = this.modalService.open(this.dedit);
	}
	
	updateDesignation(data: Designation){
		if(data.id){
			this._service.updateDesignation(data.id, data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Designation updated Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}else{
			this._service.createDesignation(data).subscribe(y=>{
				this.saveMsg = {'type': 'success', 'text': "Designation Created Successfully"};
				this.getData();
				this.modalReference.close();
			},e=> {
				this.modalReference.close();
				this.saveMsg = {'type': 'danger', 'text': "Error in Service"};
			});
		}
	}
}