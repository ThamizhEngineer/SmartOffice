import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';

@Injectable()
export class InvoiceService {

  
    constructor(
        private http:Http,
        private commonService:CommonService
    ) {
    }

    getInvoices() {
        return this.http.get(environment.serviceApiUrl + 'transaction/invoice', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 

    getInvoiceById(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/invoice/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 

    generatePdf(id){
        return this.http.get(environment.documentApiUrl + 'document/invoice-pdf/'+id+'/generate-pdf', this.commonService.jwt()).map((response: Response) => response); 
    }
    
    updateDocId(id,invoice){
        return this.http.patch(environment.serviceApiUrl + 'transaction/invoice/update-doc/'+id,invoice, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
   
    createInvoice(invoice){
        return this.http.post(environment.serviceApiUrl + 'transaction/invoice/create-invoice',invoice, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());      
    }
    updateInvoice(id,action,invoice){
        return this.http.patch(environment.serviceApiUrl + 'transaction/invoice/'+id+'/'+action,invoice, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());      
    }

    getClient() {
        return this.http.get(environment.serviceApiUrl + 'master/clients', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 
    getItemMaster(){
        return this.http.get(environment.serviceApiUrl + 'master/item', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());         
    }

    getPaymentTerms(){
        return this.http.get(environment.serviceApiUrl + 'seed/payment-terms', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getSaleOrder(id){
        return this.http.get(environment.serviceApiUrl+'transaction/sale-orders/so-jobs?partnerId='+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getCurrency(){
        return this.http.get(environment.serviceApiUrl+'seed/currency',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getCurrencyByID(id){
        return this.http.get(environment.serviceApiUrl+'seed/currency/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getExchangeRate(fromCurrId,toCurrId){
        return this.http.get(environment.serviceApiUrl+'transaction/invoice/exchange-rate/'+fromCurrId+'/'+toCurrId,this.commonService.jwt()).map((response: Response) => response).map(res => res);
    }
}
