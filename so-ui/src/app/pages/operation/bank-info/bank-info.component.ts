import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BankInfo } from './vo/bank-info';
import { BankInfoService } from './bank-info.service';

@Component({
    selector: 'bank-info',
    templateUrl: './bank-info.component.html'
})

export class BankInfoComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;

     
    constructor(private router:Router,private route:ActivatedRoute,private modalService:NgbModal,private service:BankInfoService) { 
      
    }
    rows:Array<BankInfo>;
    bankinfo:BankInfo;
    page :number = 1;
    pageSize :number = 10;
    
    saveMsg:any;
    ngOnInit() { 
        this.getData();
        this.bankinfo=new BankInfo();
        

    }
    getData(){
        this.service.getBankInfo().subscribe(x=>{
            this.rows=x;
        })

    }

    delete(id:string){
        this.service.deleteBankInfo(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Bank Name Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }
    modalReference:any=null;
    modalData: any;

    create(){
        this.bankinfo=new BankInfo();
        this.modalData = new BankInfo();
        this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
    } 
    show(id:string){
        this.service.getBankInfoById(id).subscribe(x=>{
            this.bankinfo=x;
            this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});

        });
    }
    save(){
     
        if(this.bankinfo.id){
            
                this.service.updateBankInfo(this.bankinfo, this.bankinfo.id).subscribe(x => { 
                this.rows=x;
                this.saveMsg = { 'type': 'success', 'text': "BankInfo Updated Successfully" };
                this.getData();
                this.modalReference.close();

            } );             
         }
        
         else{
            this.service.addBankInfo(this.bankinfo).subscribe(x =>{
            this.rows=x;
            this.saveMsg = { 'type': 'success', 'text': "BankInfo Created Successfully" };
            this.getData();
            this.modalReference.close();

        
        });
        }
    }
   
	
 

}