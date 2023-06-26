import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { UserType } from '../../vo/user-type';


@Component({
    selector: 'UserType',
    templateUrl: 'user-type.component.html'
})

export class UserTypeComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;
    userType: any=[];
    page :number = 1;
    pageSize :number = 10;
    userTypes: UserType;
    modalReference: any;
    soWebapp:boolean;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
    { }

    ngOnInit() {
        this.userTypes = new UserType();
        this.getUserType();
     }

    getUserType(){
        this.authorisationService.getAllUserType().subscribe(x => {
            this.userType = x;
        });
    }

    showDetail(id?: any) {
        this.authorisationService.getUserTypeById(id).subscribe(x => {
            this.userTypes = x;
            if(this.userTypes.soWebapp=="N"){
                this.userTypes.soWebapp=null;
            }    
            if(this.userTypes.soMobile=="N"){
                this.userTypes.soMobile=null;
            }
            if(this.userTypes.recruitmentWebapp=="N"){
                this.userTypes.recruitmentWebapp=null;
            }        
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.userTypes = new UserType();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    save(){
        this.userTypes.soWebapp=(this.userTypes.soWebapp?"Y":"N");
        this.userTypes.soMobile=(this.userTypes.soMobile?"Y":"N");
        this.userTypes.recruitmentWebapp=(this.userTypes.recruitmentWebapp?"Y":"N");
		if(this.userTypes.id){
			this.authorisationService.updateUserType(this.userTypes.id, this.userTypes).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "userType Updated Successfully"};
                this.getUserType();
                this.modalReference.close();
			});	
		}
		else{
			this.authorisationService.createUserType(this.userTypes).subscribe(x=>{
				this.saveMsg = {'type': 'success', 'text': "userType Created Successfully"};
                this.getUserType();
                this.modalReference.close();
			});	
		}
    }

    deleteRow(id?: any){
		this.authorisationService.deleteUserType(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "userType Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}