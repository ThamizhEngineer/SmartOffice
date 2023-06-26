import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { Functionality } from '../../vo/functionality';


@Component({
    selector: 'functionality',
    templateUrl: 'functionality.component.html'
})

export class FunctionalityComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;
    fun: any=[];
    page :number = 1;
    pageSize :number = 10;
    funs: Functionality;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private router:Router,private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
    { }

    ngOnInit() {
        console.log(this.router.url);
        this.funs = new Functionality();
        this.getFunctionality();
     }

    getFunctionality(){
        this.authorisationService.getAllFunctionality().subscribe(x => {
            this.fun = x;
        });
    }

    showDetail(id?: any) {
        this.authorisationService.getFunctionalityById(id).subscribe(x => {
            this.funs = x;
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.funs = new Functionality();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    save() {
        if (this.funs.id) {
            this.authorisationService.updateFunctionality(this.funs.id,this.funs).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "Functionality Updated Successfully" };
                this.getFunctionality();
                this.modalReference.close();
            });
    }else{
        this.authorisationService.createFunctionality(this.funs).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Functionality Created Successfully" };
            this.getFunctionality();
            this.modalReference.close();
        });
    }
    }

    deleteRow(id?: any){
		this.authorisationService.deleteFunctionality(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "Functionality Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}