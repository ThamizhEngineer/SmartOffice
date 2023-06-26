import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { AuthSubMenus } from '../../vo/menu';

import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: 'sub-menu',
    templateUrl: 'sub-menu.component.html'
})

export class SubMenuComponent implements OnInit {
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
    page :number = 1;
    pageSize :number = 15;
    subMenu: any=[];
    modalReference: any;
    subMenus: AuthSubMenus;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService)  { }

    ngOnInit() {
        this.subMenus = new AuthSubMenus();
        this.getSubMenu();
        this.authorisationService.getAllFeature().subscribe(x=>{
            this.authFeatures=x;
        })
     }

    getSubMenu(){
        this.authorisationService.getAllSubMenu().subscribe(x => {
            this.subMenu = x;
        });
    }

    showDetail(id?: any) {
        this.authorisationService.getSubMenuById(id).subscribe(x => {
            this.subMenus = x;
            if(this.subMenus.isEnabled=='N'){
                this.subMenus.isEnabled=null;
            }
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.subMenus = new AuthSubMenus();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    deleteRow(id?: any){
		this.authorisationService.deleteSubMenu(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "sub-menu Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }

        save() {
            this.subMenus.isEnabled =(this.subMenus.isEnabled?"Y":"N");
            if (this.subMenus.id) {
                this.authorisationService.updateSubMenu(this.subMenus.id,this.subMenus).subscribe(x => {
                    this.saveMsg = { 'type': 'success', 'text': "sub-menu Updated Successfully" };
                    this.getSubMenu();
                    this.modalReference.close();
                });
        }else{
            this.authorisationService.createSubMenu(this.subMenus).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "sub-menu Created Successfully" };
                this.getSubMenu();
                this.modalReference.close();
            });
        }
        }

        onFeatureSelect($event){
            console.log($event)
            this.subMenus.authFeature=$event.item.functionalityName+"-"+$event.item.featureName;
            this.subMenus.authFeatureId=$event.item.id;
        }
}