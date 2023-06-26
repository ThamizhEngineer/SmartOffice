import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router } from '@angular/router';
import { DocumentManagementService } from './documentmanagement.service';
import { DocumentManagement, DocMetadata } from './vo/documentmanagement';

import { CommonService } from '../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'document-management-list',
    templateUrl: './document-management-list.component.html'
})

export class DocumentManagementComponent implements OnInit {
    constructor(private router:Router,private service:DocumentManagementService,private commonService:CommonService,private modalService:NgbModal) 
    { }
  
    @ViewChild('vedit') vedit:TemplateRef<any>;

     doclist:Array<DocumentManagement>;
     docManagement:DocumentManagement;
     docMetadata:DocMetadata;
     metadataList:Array<DocMetadata>;
     page :number = 1;
     pageSize :number = 20;
     docListLength:number=0;
     searchString:string;
     advSearch:boolean=false;   
     docId:any;
     docName:any;
     docSize:any;
     docExtension:any; 
     data:any=[];
     mdValue:any;
     mdCode:any;

 
   
ngOnInit() { 
        this.docManagement=new DocumentManagement();
        this.docMetadata=new DocMetadata();
        this.data = new DocumentManagement;
        let data=new DocMetadata();
        
       
        this.getDocInfos();
}
getDocInfos(){
  this.service.getDocInfos().subscribe(x=>{
  this.doclist=x;
  this.data.push(x);
  this.docListLength=this.doclist.length;
  console.log(this.doclist)
            });
}

download(docId,docFileName){

    if(docId!=null&&docId!=""&&docId!=undefined){
        this.commonService.downloadDocument(docId,docFileName);
} 	
}

textSearch(){
    console.log(this.data)
   this.data=this.doclist.filter(e =>
        e.docId.toLowerCase().includes(this.searchString.toLowerCase())
       || e.docName.toLowerCase().includes(this.searchString.toLowerCase())
       || e.docLocation.toLowerCase().includes(this.searchString.toLowerCase())
       || e.docSize.toLowerCase().includes(this.searchString.toLowerCase())
       || e.docExtension.toLowerCase().includes(this.searchString.toLowerCase())
       || e.docNameFromUser.toLowerCase().includes(this.searchString.toLowerCase())
);                  
}
modalReference:any=null;

showMetaData(id:any){
    this.service.getDocInfoById(id).subscribe(x=>{
        this.docManagement=x;
        this.docMetadata=x.metadataList;
        this.modalReference=this.modalService.open(this.vedit,{size:'lg'});

    })

}
advanceSearch(){
    if(this.advSearch==false){
        this.advSearch=true;
    }else if(this.advSearch==true){
        this.advSearch=false;
    }
}
reset(){
    this.mdValue=null;this.mdCode=null;
    this.search();
 }
 search(){

 }



 
  
 }



  
  
 	



     
