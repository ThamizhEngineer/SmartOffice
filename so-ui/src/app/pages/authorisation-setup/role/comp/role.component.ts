import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { Role } from '../../vo/role';

@Component({
    selector: 'role',
    templateUrl: 'role.component.html'
})

export class RoleComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;

    role: any = [];
    roles: Role;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    page :number = 1;
    pageSize :number = 10;
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
        { }

    ngOnInit() { 
    this.roles = new Role();
    this.getRole();
    }

    getRole(){
    this.authorisationService.getAllrole().subscribe(x => {
        this.role = x;
    });
}

    showDetail(id?: any) {
        this.authorisationService.getroleById(id).subscribe(x => {
            this.roles = x;
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.roles = new Role();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    save() {
        console.log(this.roles)
        if (this.roles.id) {
            this.authorisationService.updaterole(this.roles.id,this.roles).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "role Updated Successfully" };
                this.getRole();
                this.modalReference.close();
            });
    }else{
        console.log(this.roles)
        this.authorisationService.createrole(this.roles).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "role Created Successfully" };
            this.getRole();
            this.modalReference.close();
        });
    }
    }
    deleteRow(id?: any){
		this.authorisationService.deleterole(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "role Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}


