import { Component, OnInit } from '@angular/core';
import { InvoicePaymentService } from '../invoice-payment.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute,Params,Router } from '@angular/router';
import { InvoicePayment,PaymentLine } from '../../vo/invoice-payment';
import { CommonService } from '../../../../shared/common/common.service';
import { DocMetadata, DocInfo } from '../../../vo/doc-info';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import { Observable } from 'rxjs';


@Component({
    selector: '',
    templateUrl: 'invoice-payment-detail.component.html'
})

export class InvoicePaymentDetailComponent implements OnInit {
    
    payment:InvoicePayment
    docIdsForCheckin: DocInfo[] = [];
    clients: any = [];
    clientsAC = (text$: Observable<string>) =>text$.pipe(debounceTime(200),distinctUntilChanged(),map(term => (term === '' ? this.clients: this.clients.filter(v => v.clientName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10)));
    client: any = { id: null };
    client_formatter = (x: { clientName: string }) => { x.clientName };

    constructor(
        private service:InvoicePaymentService,
        private activatedRoute:ActivatedRoute,
        private commonService: CommonService,
        private route: Router

    ) { }

    ngOnInit() {
        this.payment = new InvoicePayment();
        this.payment.paymentLine=[new PaymentLine];
        this.payment.paymentLine.splice(0,1);
        this.activatedRoute.params.subscribe(x=>{
            if(x.id!=null){
                this.service.getInvoicesPaymentById(x.id).subscribe(results=>{
                   this.payment=results
                });
            }           
        });
       this.service.getclients().subscribe(x=>this.clients=x);
     }

     fileChanged($event,type){
        let fileList: FileList = $event.target.files;
        if (fileList.length > 0) {
            let file: File = fileList[0];
            this.commonService.uploadDocument(file, type)
                .subscribe(
                    data => {
                        this.payment.docId = data[0].docId;
                        this.docIdsForCheckin.push(this.docMetaData(data[0].docId,this.payment.clientId));
                        this.CheckIn()
                    },
                    error => console.log(error))
        }
     }

     onClientSelected($event){
        if(this.payment.id==null){
        this.payment.clientId=$event.item.id;
        this.payment.clientName=$event.item.clientName;       
            this.findPaymentLine($event.item.id)
        }
       
     }

     findPaymentLine(id){
        this.service.getUnPaidInvoice(id).subscribe(invoiceHdrs=>{
            for(let invoice of invoiceHdrs){
                let paymentLine=new PaymentLine;
                paymentLine.invoiceHdrId=invoice.id;
                paymentLine.invoiceAmount=invoice.invoiceWithoutExAmt;
                paymentLine.paidAmt=0;    
                paymentLine.invoicePaidAmt=invoice.paidAmt;
                paymentLine.invoiceCode=invoice.invoiceCode;
                paymentLine.date=invoice.invoiceDate.substring(0, 10);
                this.payment.paymentLine.push(paymentLine);            
            }
        })
     }

     docMetaData(docId:any,clientId){
        let docInfo:DocInfo = new DocInfo();
        docInfo.docId = docId;
        docInfo.metadataList = [
        {
        isKey:'Y',
        mdCode:'customer-id',
        mdValue:clientId
        }
    ];
        return docInfo; 
    }
    
    CheckIn(){
	
        if(this.docIdsForCheckin[0]!=null){
            this.commonService.checkInDocuments(this.docIdsForCheckin).subscribe(x=>{
            },error=>{
                console.log(error);
             });
        }
        
    }

    update(){
        console.log("Update")
        this.service.updateInvoicesPayment(this.payment.id,this.payment).subscribe(x=>{
            this.route.navigateByUrl('/client/invoice-payment');
         })
    }

     save(){
         console.log(this.payment);
         if(this.payment.id==null){
            this.service.createInvoicesPayment(this.payment).subscribe(x=>{
                this.route.navigateByUrl('/client/invoice-payment');
             })
         }
        
     }

}