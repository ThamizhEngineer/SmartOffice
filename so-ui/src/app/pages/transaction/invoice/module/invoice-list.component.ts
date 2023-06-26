import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../invoice.service';
import { Router } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { Observable } from 'rxjs/Observable';
import { status_css } from '../../../vo/status'


@Component({
    selector: '',
    templateUrl: 'invoice-list.component.html'
})

export class InvoiceListComponent implements OnInit {
    constructor(
        private service:InvoiceService,
        private router:Router,
        private commonService:CommonService
    ) { }

    invoices:any=[];
    page :number = 1;    
    pageSize :number = 10
    statuses:any=status_css;


    ngOnInit() {
        this.invoices=[];
        this.service.getInvoices().subscribe(x=>this.invoices=x);
     }

     fileChanged($event){
        let fileList: FileList = $event.target.files;        
        if (fileList.length > 0) {  
            let files: File = fileList[0];          
			 this.commonService.uploadInvoice(files).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {                      
                        this.ngOnInit();                                        
                    },
                    error => console.log(error)
                )
        }     }
}