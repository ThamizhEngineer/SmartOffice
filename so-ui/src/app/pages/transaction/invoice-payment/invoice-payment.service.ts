import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';


@Injectable()
export class InvoicePaymentService {

  
    constructor(
        private http:Http,
        private commonService:CommonService
    ) {
    }

    getInvoicesPayments() {
        return this.http.get(environment.serviceApiUrl + 'transaction/invoice-payment', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }  
    getInvoicesPaymentById(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/invoice-payment/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    createInvoicesPayment(payment) {
        return this.http.post(environment.serviceApiUrl + 'transaction/invoice-payment',payment, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }  
    updateInvoicesPayment(id,payment) {
        console.log(id,payment)
        return this.http.patch(environment.serviceApiUrl + 'transaction/invoice-payment/'+id,payment, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    deletePayment(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/invoice-payment/'+id, this.commonService.jwt()).map((response: Response) => response);    
    }
    getclients() {
        return this.http.get(environment.serviceApiUrl + 'master/clients' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getUnPaidInvoice(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/invoice/'+id+'/checkBalance' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());        
    }
}
