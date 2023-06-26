
import { Component,OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{ExitInterviewService}from '../exit-interview.service';
import { status_css } from '../../../vo/status'

@Component({
    selector: 'exit-interview',
    templateUrl: './exit-interview.component.html'
})
export class ExitInterviewComponent implements OnInit {
        
    exitInterviews:any=[];
    statuses:any=status_css;

    constructor(private router:Router,private exitInterviewService:ExitInterviewService ){}

    ngOnInit() {
        this.exitInterviewService.getAlExitInterview().subscribe(x=>{
            this.exitInterviews=x;        
                })
    }
  
    operationNavigation(type,id){
        let url = "/operation/exit-interview/exit-interview-"+type+"/"+id;        
        this.router.navigateByUrl(url); 
    }
  
}


