import { Component, OnInit } from '@angular/core';
import { Router,ActivatedRoute } from '@angular/router';
import { CommonService } from '../../../../shared/common/common.service';
import { SkillAnalysisService } from '../skill-analysis.service';

@Component({
    selector: '',
    templateUrl: 'skill-analysis-list.componet.html'
})

export class SkillAnalysisListComponent implements OnInit {
    alertMessage="";
    constructor(
        private service:SkillAnalysisService,
        private router:Router,
        private commonService: CommonService
    ) { }

    skillHdrs:any=[];
    page :number = 1;
    pageSize :number = 10;

    ngOnInit() {
        
        this.service.getskillHdr().subscribe(x=>this.skillHdrs=x);        
     }

     detailRouting(view,key){
        this.router.navigateByUrl('/transaction/skill-analysis/'+view+'/'+key);
     }

     downloadPdf(x){
        console.log(x)
        if(x.docId!=null || x.docId!=undefined){
            this.commonService.downloadDocument(x.docId,"");
        }else{
            this.alertMessage="No document mapped";
        }
     }

}