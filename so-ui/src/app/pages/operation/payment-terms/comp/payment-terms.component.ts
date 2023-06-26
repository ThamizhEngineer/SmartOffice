import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { PaymentTermsService } from '../payment-terms.service';
import { PaymentTerms } from '../vo/payment-terms';


@Component({
    selector: 'payment-terms',
    templateUrl: 'payment-terms.component.html'
})

export class PaymentTermsComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;
    rows:any=[];
    page :number = 1;
    pageSize :number = 10;
    paymentTerms: PaymentTerms;
    modalData: any;
    modalReference: any;
    saveMsg: { 'type': string; 'text': string; };
    constructor(private router:Router,private modalService:NgbModal,private service:PaymentTermsService,private route:ActivatedRoute) { }

    ngOnInit() { 
        this.getData();
        this.paymentTerms = new PaymentTerms();
    }

    getData(){
        this.service.getPaymentTerms().subscribe(x=> {
            this.rows = x;
        })
    }

    create(){
        this.paymentTerms=new PaymentTerms();
        this.modalData = new PaymentTerms();
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
        
    }
    show(id:string){
        this.service.getPaymentTermsById(id).subscribe(x=>{
            this.paymentTerms=x;
            if(this.paymentTerms.isEnabled=="N"){
                this.paymentTerms.isEnabled=null;
            }
            this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
            });
    }

    save(){
        this.paymentTerms.isEnabled=(this.paymentTerms.isEnabled?"Y":"N");
        if(this.paymentTerms.id){
                this.service.updatePaymentTerms(this.paymentTerms, this.paymentTerms.id).subscribe(x => { 
                this.saveMsg = { 'type': 'success', 'text': "DayRange Updated Successfully" };
                this.getData();
                this.modalReference.close();
            } );             
         }
        
         else{
            this.service.addPaymentTerms(this.paymentTerms).subscribe(x =>{
            this.saveMsg = { 'type': 'success', 'text': "Day Range Created Successfully" };
            this.getData();
            this.modalReference.close();
            
        });
        }
    }

    delete(id:string){
        this.service.deletePaymentTerms(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }

}