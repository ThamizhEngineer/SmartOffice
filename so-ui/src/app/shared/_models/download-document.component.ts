import { Component, OnInit, ViewChild, TemplateRef, Output, EventEmitter, Input, ViewEncapsulation } from '@angular/core'; 
import { CommonService } from '../common/common.service';
@Component({
    selector: 'download-document',
    template: `	
        <a class="btn btn-{{btnClassName}} btn-xs" (click)="download()" title="{{toolTip}}"><i class="fa fa-download">  
	`,    
    providers:[],
    
})

export class DownloadDocumentComponent implements OnInit {
  
    @Input() docId:string;        
    btnClassName: string = "light";
    toolTip: string;  
    buttonSize: string = "btn-xs"; 
    docUrl:string;     
    constructor(  
        private commonService: CommonService
    ) { }
  

    ngOnInit() { 
        if(this.docId){
            this.btnClassName = "success";
            this.toolTip = "Download Document"
        }
        else{
            this.toolTip = "No Document to download"
        } 
    }   

    download(){
        if(this.docId){
            this.commonService.downloadDocument(this.docId,"");
            this.toolTip = "Download Document"
        }
        else{
            this.toolTip = "No Document to download"
            alert("No Document available");
        }
    } 
  
}