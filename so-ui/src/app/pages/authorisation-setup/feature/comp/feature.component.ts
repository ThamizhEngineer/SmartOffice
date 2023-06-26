import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { AuthorisationService } from '../../authorisation.service';
import { Features } from '../../vo/feature';

@Component({
    selector: 'feature',
    templateUrl: 'feature.component.html'
})

export class FeatureComponent implements OnInit {
    @ViewChild('popup') popup: TemplateRef<any>;

    feature: any = [];
    features: Features;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    page :number = 1;
    pageSize :number = 10;
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal, private authorisationService: AuthorisationService) 
        { }

    ngOnInit() { 
    this.features = new Features();
    this.getFeatures();
    }

    getFeatures(){
    this.authorisationService.getAllFeature().subscribe(x => {
        this.feature = x;
    });
}

    showDetail(id?: any) {
        this.authorisationService.getFeatureById(id).subscribe(x => {
            this.features = x;
            this.modalReference = this.modalService.open(this.popup, { size: 'lg' });
        });
    }

    createNew(){
        this.features = new Features();
        this.modalReference = this.modalService.open(this.popup, {size: 'lg'});	
    }

    save() {
        if (this.features.id) {
            this.authorisationService.updateFeature(this.features.id,this.features).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "features Updated Successfully" };
                this.getFeatures();
                this.modalReference.close();
            });
    }else{
        this.authorisationService.createFeature(this.features).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "features Created Successfully" };
            this.getFeatures();
            this.modalReference.close();
        });
    }
    }
    deleteRow(id?: any){
		this.authorisationService.deleteFeature(id).subscribe(x=>{
            this.saveMsg = {'type': 'success', 'text': "feature Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}


