import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { User } from '../../../../auth/_models';

@Component({
    selector: 'user-view',
    templateUrl: 'user-view.component.html'
})

export class UserViewComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;
    authUser: any=[];
    page :number = 1;
    pageSize :number = 15;
    authUsers: any;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
    { }

    ngOnInit() {
        this.authUsers = new User();
        this.getUserView();
     }

    getUserView(){
        this.authorisationService.getAllUser().subscribe(x => {
            this.authUser = x;
        });
    }

    showDetail(id?: any) {
        this.authorisationService.getUserById(id).subscribe(x => {
            this.authUsers = x;
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.authUsers = new User();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    save() {
        if (this.authUsers.id) {
            this.authorisationService.updateUser(this.authUsers.id,this.authUsers).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "features Updated Successfully" };
                this.getUserView();
                this.modalReference.close();
            });
    }else{
        this.authorisationService.createUser(this.authUsers).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "features Created Successfully" };
            this.getUserView();
            this.modalReference.close();
        });
    }
    }
    deleteRow(id?: any){
		this.authorisationService.deleteUser(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "feature Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}