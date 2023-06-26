import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { AuthRoleFeatures } from '../../vo/role';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
@Component({
    selector: 'RoleFeature',
    templateUrl: 'role-feature.component.html'
})

export class RoleFeatureComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;

    searchFeatures = (text$: Observable<string>) =>
    text$.pipe(
        debounceTime(200),
        distinctUntilChanged(),
        map(term => (term === '' ? this.authFeatures : this.authFeatures.filter(v => v.functionalityName.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.featureName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
    );
    featuresFormatter = (x: { functionalityName: string }) => {
    x.functionalityName
};

    authFeatures:any=[]
    authRole: any=[];
    authRoles: AuthRoleFeatures;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    role: any=[];
    page :number = 1;
    pageSize :number = 10;
    feature: any=[];

    roleId:any;
    featureId:any;
    featureName:string='';

    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) {}
    ngOnInit() {
        this.authRoles = new AuthRoleFeatures();
        this.getAuthRole();
        this.getRole();
        this.getFeature();
        this.authorisationService.getAllFeature().subscribe(x=>{
            this.authFeatures=x;
        })
     }

    getAuthRole(){
        this.authorisationService.getAllAuthRole(this.roleId,this.featureId).subscribe(x => {
            this.authRole = x;
        });
    }
    getRole(){
        this.authorisationService.getAllrole().subscribe(x => {
            this.role = x;
        });
    }
    getFeature(){
        this.authorisationService.getAllFeature().subscribe(x => {
            this.feature = x;
        });
    }

    search(){   
        this.authRole =[];    
        this.authorisationService.getAllAuthRole(this.roleId,this.featureId).subscribe(x => {
            this.authRole = x;
        })
    }

    showDetail(id?: any) {
        if(id=='new'){
            this.authRoles = new AuthRoleFeatures();
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        }else{
            this.authorisationService.getAuthRoleById(id).subscribe(x => {
                this.authRoles = x;
                this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
            });
        }
       
    }


    deleteRow(id?: any){
		this.authorisationService.deleteAuthRole(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "auth role Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
        save() {
            if (this.authRoles.id) {
                this.authorisationService.updateAuthRole(this.authRoles.id,this.authRoles).subscribe(x => {
                    this.saveMsg = { 'type': 'success', 'text': "auth role Updated Successfully" };
                    this.getAuthRole();
                    this.modalReference.close();
                });
        }else{
            this.authorisationService.createAuthRole(this.authRoles).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "auth role Created Successfully" };
                this.getAuthRole();
                this.modalReference.close();
            });
        }
        }
        onFeatureSelect($event){
            this.featureName=$event.item.functionalityName+"-"+$event.item.featureName;
            this.featureId=$event.item.id;
        }
        onFeatureSelect2($event){
            this.authRoles.authFeature=$event.item.functionalityName+"-"+$event.item.featureName;
            this.authRoles.authFeatureId=$event.item.id;
        }
}