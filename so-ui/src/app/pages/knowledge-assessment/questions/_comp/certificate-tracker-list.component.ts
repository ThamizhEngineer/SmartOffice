import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { QuestionsService } from './../questions.service';

@Component({
  selector: 'certificate-tracker-list',
  templateUrl: './certificate-tracker-list.component.html'
})
export class CertificateTrackerComponent implements OnInit {

	constructor(private titleService: Title, private _service: QuestionsService,private router:Router) { }

	ngOnInit() {
		
    }
    
    navigateToDetailPage(){
        this.router.navigateByUrl("/knowledge-assessment/certificate-tracker/certificate-tracker-detail");   
       }
	
	
}
 