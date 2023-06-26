import { Component, OnInit,ViewChild, TemplateRef } from '@angular/core';
import { InvoiceService } from '../invoice.service';
import { Invoice,InvoiceLine } from '../vo/invoice-process';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable } from 'rxjs';
import { status_css } from '../../../vo/status'
import {UserService} from '../../../../auth/_services/user.service';
import {User} from '../../../../auth/_models/user';

@Component({
    selector: '',
    templateUrl: 'invoice-view.component.html'
})

export class InvoiceViewComponent implements OnInit {
    
    @ViewChild('sendEmailTemplate') sendEmailTemplate: TemplateRef<any>;
    statuses:any=status_css;
    user:User;
    isHide:boolean=true;

    constructor(
        private service:InvoiceService,
        private activatedRoute:ActivatedRoute,
        private modalService: NgbModal,
        private userService:UserService,
        private commonService: CommonService
    ) { }
        invoice:Invoice;
        modelReference:any = null;

    ngOnInit() {
        this.user = this.userService.getCurrentUser();
        this.user.userGroupMappings.forEach(element => {
            if(element.isDir=='Y'){
                this.isHide=false;
            }
        });


        this.invoice = new Invoice();
        this.activatedRoute.params.subscribe(x=>{
           this.service.getInvoiceById(x.id).subscribe(inv=>this.invoice=inv);
        })    
     }

     sendEmail(){
        this.modelReference =  this.modalService.open(this.sendEmailTemplate, {size: 'lg'});
     }

     generagetPdf(){
         this.service.generatePdf(this.invoice.id).subscribe(x=>{
            this.service.getInvoiceById(this.invoice.id).subscribe(inv=>{
                this.commonService.downloadDocument(inv.pdfDocId,this.invoice.buyerName+""+this.invoice.invoiceCode);
            });
             
         })
     }

     download(docId, docFileName){
        if(docId!=null&&docId!=""&&docId!=undefined){
            this.commonService.downloadDocument(docId,docFileName);
        } 
    }

     uploadDoc(event, docTypeCode){
        let fileList: FileList = event.target.files;
        if (fileList.length > 0) {
            
            this.commonService.uploadDocument(fileList[0], docTypeCode).map((response: Response) => response)
               .catch(error => Observable.throw(error))
               .subscribe(
                   data => {
                       this.invoice.docId = data[0].docId;    
                          this.updateDoc();       
                   },
                   error => console.log(error)
               )
       }
     }

     updateDoc(){
        this.service.updateDocId(this.invoice.id,this.invoice).subscribe(x=>{
            this.ngOnInit();
        })      
     }
     invoiceAction(action){
        this.service.updateInvoice(this.invoice.id,action,this.invoice).subscribe(x=>{
            this.ngOnInit();
        })
     }
}