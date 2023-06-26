import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators,FormControl } from '@angular/forms';
import { analyzeAndValidateNgModules } from '@angular/compiler';
import { saveAs } from 'file-saver';
import { ExpenseClaimService } from '../../../my-space/expense-claim/expense-claim.service';
@Component({
    selector: 'bank-advise-list',
    templateUrl: 'bank-advise-list.component.html',
  
})


export class BankAdviseReportListComponent implements OnInit{
allDatas:any=[];
fromDt:string;
purpose:string;
endDt:string;
form: FormGroup;
fDt:FormControl;
tDt:FormControl;
isGetAllData:boolean=false;
purposes=[
    { id: 1, name: "EXPENSE-CLAIM-SETLLEMENT" },
    { id: 2, name: "Salary" },

]
    constructor(private service:ExpenseClaimService){

}


   ngOnInit(){

   }
getAllBankReports(purpose:string,fromDt:any,endDt:any){
    this.service.getReportData(purpose,fromDt,endDt).subscribe(x=>{
this.allDatas=x.bankAdviseDataList;
this.isGetAllData=true;
console.log("12334",this.allDatas);
    });
}
 

exportAsExcelData(){
    this.service.exportAsExcel(this.purpose,this.fromDt,this.endDt).subscribe(x=>{
    this.downloadFile(x);
    })
}
downloadFile(data: any){
    var url = window.URL.createObjectURL(new Blob([data]));
  
     var a = document.createElement('a');
     document.body.appendChild(a);
     a.setAttribute('style', 'display: none');
     a.href = url;
     a.download = 'Bank-Advise-Report.xls';
     a.click();
     window.URL.revokeObjectURL(url);
     a.remove(); // remove the element
   }
createForm(){
    this.form = new FormGroup({
        
        fDt:this.fDt,
        tDt:this.tDt,
       
    });		
}
}