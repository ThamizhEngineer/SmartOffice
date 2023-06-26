import { Component, OnInit, ViewChild, TemplateRef, Output, EventEmitter, Input, ViewEncapsulation } from '@angular/core'; 
import { NgbModal } from '@ng-bootstrap/ng-bootstrap'; 
import { CommonService } from '../common/common.service';
@Component({
    selector: 'preview-document',
    template: `	
        <a class="btn btn-{{btnClassName}} btn-xs" (click)="openModal()" title="{{toolTip}}"><i class="fa fa-search"> 
        <ng-template #docViewerModal let-modal>
        <div class="modal-header">
            <h4 class="modal-title" id="modal-basic-title">View Document</h4>
            <button type="button" class="close" aria-label="Close" (click)="close()">
            <span aria-hidden="true">×</span>
            </button>
        </div>
        <div class="modal-body">
            <ngx-doc-viewer *ngIf="docExtension!='pdf'"  url="{{docUrl}}"  viewer="mammoth" style="width:100%;"></ngx-doc-viewer> 
            <pdf-viewer *ngIf="docExtension=='pdf'" [src]="docUrl"   style="display: block;"></pdf-viewer> 

            <!-- <ngx-doc-viewer url="http://localhost/so-document-service/dm/834bd627-768c-407a-95d8-08175d1930e7?authorization=acbc70a4-1206-426c-8e14-fff7b3d0c475"  viewer="mammoth" style="width:100%;"></ngx-doc-viewer> -->
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-danger" (click)="close()">Close</button>
        </div>
        </ng-template>   
	
	`,    
    providers:[],
    
})

export class PreviewDocumentComponent implements OnInit {
  
    @Input() docId:string;       
    @ViewChild('docViewerModal') docViewerModal: TemplateRef<any>;
    docExtension:string = '';
    btnClassName: string = "light";
    toolTip: string;  
    docUrl:string="https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf";    
    constructor( 
        private modalService: NgbModal,
        private commonService: CommonService
    ) { }
 
    modalReference:any = null;

    ngOnInit() { 
        this.docUrl = "https://vadimdez.github.io/ng2-pdf-viewer/assets/pdf-test.pdf";

        if(this.docId){
            this.btnClassName = "success";
            this.toolTip = "Preview Document";
        }
        else{
            this.toolTip = "No Document to View";
            this.btnClassName = "light";
        } 
    }  
    close(){
        this.modalReference.close();  
    }
 
  openModal() {
    this.docUrl =  this.commonService.getDocUrl(this.docId); 
    this.commonService.getDocInfoByDocId(this.docId).subscribe(x=>{
        if(x.length > 0){
            this.docExtension = x[0].docExtension;
        }
        console.log(this.docExtension) 
        if(this.docId){
            console.log(this.docUrl)
            this.modalReference = this.modalService.open(this.docViewerModal, { size: 'lg' });
            this.toolTip = "Preview Document";
            this.btnClassName = "success";
        }
        else{
            this.toolTip = "No Document to View";
            this.btnClassName = "light";
            alert("No Document available");
        }
    });

  }
  
}