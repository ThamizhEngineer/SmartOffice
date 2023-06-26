import { Component, OnInit, Pipe, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Observer } from 'rxjs';
import { ResponseContentType, Http } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import { FunctionGroupService } from '../function-group.service';
import { FunctionGroup, Division, Function } from '../vo/function-group';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector: '',
    templateUrl: 'function-group-detail.component.html'
})

export class FunctionGroupDetailComponent implements OnInit {
    @ViewChild('Division') DivisionView: TemplateRef<any>;

    functionGroup:FunctionGroup;
    divisions:[Division];
    division:Division;
    modalReference:any;
    saveMsg: { 'type': string; 'text': string; };

  
 
    constructor(
        private activatedRoute: ActivatedRoute,
        private service:FunctionGroupService,
        private modalService: NgbModal,
        private router:Router
    ) { }

    ngOnInit() {
        this.functionGroup = new FunctionGroup();
        this.functionGroup.functions = [new Function];        
        this.divisions =[new Division];
        this.division = new Division();
        this.activatedRoute.params.subscribe(x=>{
            console.log(x)
            if(x.id!=null){
                this.service.getFunctionGroupId(x.id).subscribe(results=>{
                    console.log(results)
                            this.functionGroup=results;
                            console.log(this.functionGroup);
                            if(this.functionGroup.deliveryType=='goods'){
                                this.functionGroup.deliveryType="goods";
                            }else{
                              this.functionGroup.deliveryType="service";
                            }
                            if(this.functionGroup.isEnabled=='N'){
                              this.functionGroup.isEnabled=null;
                          }
                });
            }           
        });
     }

     openModal(divisions) {   
         this.divisions = [new Division];
         this.division =new Division();
         if(divisions!='empty'){
            this.divisions=divisions;
         }                   
        this.modalReference = this.modalService.open(this.DivisionView, {size: 'lg'});
    }

    addRows(){
        let funct = new Function();   
        funct.division =[new Division];     
        this.functionGroup.functions.push(funct);
    }
    delRow(i){
        this.functionGroup.functions.splice(i,1);
    }

    saveFunction(){
        this.functionGroup.isEnabled=(this.functionGroup.isEnabled?"Y":"N");
        console.log(this.functionGroup.id)
        console.log()
        if(this.functionGroup.id){
            this.service.updateFunctionGroup(this.functionGroup.id, this.functionGroup).subscribe(x=>{
                this.router.navigate(['/operation/function-group/list']);
                this.saveMsg = {'type': 'success', 'text': "Manufacturer Updated Successfully"};
            });	
        }
        else{
            console.log(this.functionGroup)
            this.service.addFunctionGroup(this.functionGroup).subscribe(x=>{
                this.router.navigate(['/operation/function-group/list']);
                this.saveMsg = {'type': 'success', 'text': "Manufacturer Created Successfully"};
            });	
        }
    }

    delDivRow(i){
        this.divisions.splice(i,1);
    }
}