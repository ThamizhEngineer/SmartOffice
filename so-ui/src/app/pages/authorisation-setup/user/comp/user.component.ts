import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { User } from '../../../../auth/_models';

@Component({
    selector: 'User',
    templateUrl: 'user.component.html'
})

export class UserComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;
    user: any=[];
    page :number = 1;
    pageSize :number = 10;
    users: User;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) { }

    ngOnInit() {
        this.users = new User();
        this.getUser();
     }

    getUser(){
        this.authorisationService.getAllUser().subscribe(x => {
            this.user = x;
        });
    }

    showDetail(id?: any) {
        this.authorisationService.getUserById(id).subscribe(x => {
            this.users = x;
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.users = new User();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    save() {
        if (this.users.id) {
            this.authorisationService.updateUser(this.users.id,this.users).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "user Updated Successfully" };
                this.getUser();
                this.modalReference.close();
            });
    }else{
        this.authorisationService.createUser(this.users).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "user Created Successfully" };
            this.getUser();
            this.modalReference.close();
        });
    }
    }

    deleteRow(id?: any){
		this.authorisationService.deleteUser(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "user Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}