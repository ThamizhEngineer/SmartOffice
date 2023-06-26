import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../invoice.service';
import { Invoice,InvoiceLine } from '../vo/invoice-process';
import { ActivatedRoute } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';


@Component({
    selector: '',
    templateUrl: 'invoice-edit.component.html'
})

export class InvoiceEditComponent implements OnInit {
    
    invoice:Invoice;
    client:any=[];
    itemMaster:any=[];
    paymentTerms:any=[];
    saleOrder:any=[];
    exchangeType:any=[];
    handledGst:boolean=true;

    clientAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.client : this.client.filter(v => v.clientName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    client_formatter = (x: {clientName: string}) => x.clientName;

    saleOrderAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.saleOrder : this.saleOrder.filter(v => v.saleOrderCode.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    saleOrder_formatter = (x: {saleOrderCode: string}) => x.saleOrderCode;

    itemMasterAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.itemMaster : this.itemMaster.filter(v => v.itemName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    itemMaster_formatter = (x: {itemName: string}) => x.itemName;

    constructor(
        private service:InvoiceService,
        private activatedRoute:ActivatedRoute,
        
        private route:Router
    ) { }

    ngOnInit() {
        this.invoice= new Invoice;
        this.invoice.invoiceLines = [new InvoiceLine];        
       
        this.activatedRoute.params.subscribe(x=>{
            this.service.getInvoiceById(x.id).subscribe(x=>{
                if(x.id!=null){
                    this.invoice=x
                    this.invoice.invoiceDate=this.invoice.invoiceDate.substring(0, 10);
                    this.handledGst=this.invoice.isHandledGst!='Y'?false:true;
                }                
            });
        });   
        
        this.service.getClient().subscribe(x=>this.client=x);
        this.service.getItemMaster().subscribe(x=>this.itemMaster=x);
        this.service.getPaymentTerms().subscribe(x=>this.paymentTerms=x);
        this.service.getCurrency().subscribe(x=>this.exchangeType=x);
     }

     getSaleOder(partnerId){
        this.service.getSaleOrder(partnerId).subscribe(x=>{
            this.saleOrder=x;
        })
     }


     paymentTermsCal(){
         // substring
         let date = new Date();
        if(this.invoice.termsOfPayment.substring(0,3)=='NET'){
            var newdate = new Date(date);
            let value = Number(this.invoice.termsOfPayment.substring(4))            
            newdate.setDate(date.getDate() + value);
            this.invoice.pmtDueDt =newdate.toJSON();
            this.invoice.pmtDueDt= this.invoice.pmtDueDt.substring(0,10);
            this.invoice.pmtDueDt= this.invoice.pmtDueDt+" 10:00:00"
        }else{
            this.invoice.pmtDueDt=date.toJSON();
            this.invoice.pmtDueDt=  this.invoice.pmtDueDt.substring(0,10);
            this.invoice.pmtDueDt= this.invoice.pmtDueDt+" 10:00:00"
        }

        this.invoice.saleOrderCode
         console.log(this.invoice.pmtDueDt);
     }

     calculatePoLineSubTotal(invoiceLines:InvoiceLine){
           
        let total = 0 ,qtr = 0 ,discount = 0, unitAmt =0,subTotalWithoutGst = 0 ,cgstDiscount=0,sgst=0,igst=0;

        if(((invoiceLines.itemQty!=null)&&(invoiceLines.itemQty!=undefined)) && ((invoiceLines.itemRate!=null)&&(invoiceLines.itemRate!=undefined))){
            qtr = invoiceLines.itemQty*1;
            unitAmt = invoiceLines.itemRate*1;
            subTotalWithoutGst =  qtr * unitAmt;
            if(invoiceLines.isDiscount!=null && invoiceLines.isDiscount!=undefined){
                if((invoiceLines.discountPercentage!=null)&&(invoiceLines.discountPercentage!=undefined)){
                    // discount = invoiceLines.lineDiscountAmt*1;
                    if(invoiceLines.isDiscount=='Y'){
                        // discount = discount/100;
                     
                        invoiceLines.lineDiscountAmt = (subTotalWithoutGst * invoiceLines.discountPercentage)/100 ;
                  
                        invoiceLines.itemBeforeTaxAmount = subTotalWithoutGst;
                        subTotalWithoutGst =subTotalWithoutGst -invoiceLines.lineDiscountAmt;
                        invoiceLines.itemTaxAmount = subTotalWithoutGst;
                     
                    }                                   
                }
            }
            
            
        }
        total =subTotalWithoutGst;

        if(((invoiceLines.cgst!=null)&&(invoiceLines.cgst!=undefined))){
  
            invoiceLines.cgstTaxAmt = (subTotalWithoutGst *invoiceLines.cgst)/100 ;      
 
        }
        if((invoiceLines.sgst!=null)&&(invoiceLines.sgst!=undefined)&&(invoiceLines.sgst>0)){
   
     
            invoiceLines.sgstTaxAmt = (subTotalWithoutGst *invoiceLines.sgst)/100 ;     

        }
        if((invoiceLines.igst!=null)&&(invoiceLines.igst!=undefined)&&(invoiceLines.igst>0)){
            invoiceLines.igstTaxAmt = (subTotalWithoutGst *invoiceLines.igst)/100 ;           
        }
        total = subTotalWithoutGst+invoiceLines.igstTaxAmt+invoiceLines.cgstTaxAmt+invoiceLines.sgstTaxAmt;        
        console.log(total)
        invoiceLines.itemTotal = total;
        this.calculate();
    }


     calculate(){ 
        let totalCgst=0,totalIgst=0,totalSgst=0,totalTaxAmt=0,totalDiscountAmt=0,grandTotal=0,subTotal=0;
        this.invoice.invoiceLines.forEach(x=>{
            if(x.cgstTaxAmt!=null&&x.cgstTaxAmt!=undefined){
                totalCgst=totalCgst+(x.cgstTaxAmt*1);
            }
            if(x.sgstTaxAmt!=null&&x.sgstTaxAmt!=undefined){
                totalSgst=totalSgst+(x.sgstTaxAmt*1);
            }
            if(x.igstTaxAmt!=null&&x.igstTaxAmt!=undefined){
                totalIgst=totalIgst+(x.igstTaxAmt*1);
            }
            if(x.itemTotal!=null&&x.itemTotal!=undefined){
                subTotal=subTotal+(x.itemTotal*1);
            }
            totalTaxAmt = (totalCgst*1) + (totalSgst*1)+ (totalIgst*1);
            if(x.lineDiscountAmt!=null&&x.lineDiscountAmt!=undefined){
                totalDiscountAmt=totalDiscountAmt+(x.lineDiscountAmt*1);
            }

        })     
        grandTotal = (subTotal*1+Number(this.invoice.shippingAmt));

        this.invoice.cgstTaxAmt=totalCgst
        this.invoice.sgstTaxAmt=totalSgst
        this.invoice.igstTaxAmt=totalIgst
        this.invoice.totalTaxAmt=totalTaxAmt
        this.invoice.totalDiscountAmt=totalDiscountAmt
        this.invoice.finalPayableAmt = grandTotal        
        this.invoice.invoiceWithoutExAmt = this.invoice.finalPayableAmt*this.invoice.exchangeRate;
    }


    addRows(){
        let line = new InvoiceLine
        this.invoice.invoiceLines.push(line);    
    }
    delRow(i){
        this.invoice.invoiceLines.splice(i,1);
    }

    selectSaleOrder($event){
        this.invoice.saleOrderId=$event.item.id;
        this.invoice.saleOrderCode=$event.item.saleOrderCode;        
    }

    createInvoice(){
        this.invoice.isHandledGst=this.handledGst?"Y":"N";
        if(this.invoice.invoiceDate.toString().length==10){
            this.invoice.invoiceDate=this.invoice.invoiceDate+" 10:00:00";          
        }
        this.invoice.id=null;
        this.invoice.invoiceLines.forEach(element => {
            element.id=null;
            element.invoiceHdrId=null;
        });
        this.service.createInvoice(this.invoice).subscribe(x=>{
            this.route.navigate(['/client/invoice']);
        })
    }

    updateInvoice(){
        this.invoice.isHandledGst=this.handledGst?"Y":"N";
        if(this.invoice.invoiceDate.toString().length==10){
            this.invoice.invoiceDate=this.invoice.invoiceDate+" 10:00:00";          
        }
        // this.invoice.id=null;
        // this.invoice.invoiceLines.forEach(element => {
        //     element.id=null;
        //     element.invoiceHdrId=null;
        // });
        this.service.createInvoice(this.invoice).subscribe(x=>{
            this.route.navigate(['/client/invoice']);
        })
    }

    selectClient($event){
        this.invoice.buyerName=$event.item.companyName;
        this.invoice.buyerGstinOrUin=$event.item.gstNo
        this.invoice.buyerEmail=$event.item.emailId;
        this.invoice.buyerAddress=$event.item.addressLine1;
        this.invoice.buyerState=$event.item.state;
        this.invoice.buyerStateCode=$event.item.tinNo;
        this.invoice.buyerVatTin=$event.item.tinNo;
        this.invoice.buyerContact=$event.item.mobileNo;
        this.invoice.clientId=$event.item.id;
        this.invoice.clientRef=$event.item.referenceNumber;
        this.invoice.companyPanOrIecCode=$event.item.panNo;
        this.invoice.country=$event.item.countryName;
        this.invoice.transactionCurrId=$event.item.transactionCurrId; 
        this.invoice.buyerHasGst=$event.item.companyHasGst;
        this.invoice.buyerHasOverseas=$event.item.companyHasOverseas;
        this.getSaleOder($event.item.id);  
        this.exchangeRate();
        this.checkCompanyGst();
    }

    checkCompanyGst(){
        if(this.invoice.buyerHasGst=='N'){
            this.handledGst=false;
        }else{
            this.handledGst=true;
        }
    }

    exchangeRate(){
        this.service.getExchangeRate(this.invoice.transactionCurrId,'INR').subscribe(x=>{            
            this.invoice.exchangeRate=Number(x.text());
        });
        this.service.getCurrencyByID(this.invoice.transactionCurrId).subscribe(x=>{            
            this.invoice.transactionCurrSymbol=x.currSymbol
        });

    }
    
    
    selectItemMaster($event,line:InvoiceLine){
        line.itemName=$event.item.itemName;
        line.itemId=$event.item.id;
        line.itemRate=$event.item.unitPrice;
        if(this.invoice.buyerHasGst!='N'){
            line.sgst=$event.item.sgstRate;
            line.cgst=$event.item.cgstRate;
            line.igst=$event.item.igstRate;
        }      
        line.itemDescription=$event.item.itemDesc;
        line.itemHsnOrSac=$event.item.hsnSacCode;   
        this.calculatePoLineSubTotal(line);   
    }
}