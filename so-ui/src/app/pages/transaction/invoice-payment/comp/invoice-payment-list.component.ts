import { Component, OnInit } from '@angular/core';
import { InvoicePaymentService } from '../invoice-payment.service';

@Component({
    selector: '',
    templateUrl: 'invoice-payment-list.component.html'
})

export class InvoicePaymentListComponent implements OnInit {
    
    payments:any=[];
    page :number = 1;    
    pageSize :number = 10

    constructor(
        private service:InvoicePaymentService
    ) { }

    ngOnInit() { 
        this.service.getInvoicesPayments().subscribe(x=>this.payments=x);
    }
}