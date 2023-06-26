import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { QuestionsService } from './../questions.service';

@Component({
  selector: 'certificate-tracker-detail',
  templateUrl: './certificate-tracker-detail.component.html'
})
export class CertificateTrackerDetailComponent implements OnInit {

	constructor(private titleService: Title, private _service: QuestionsService,private router:Router) { }

	ngOnInit() {
		
    }
    
    navigateToListPage(){
        this.router.navigateByUrl("/knowledge-assessment/certificate-tracker/certificate-tracker-list");   
       }
	
	
}
 