import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { CertificateService } from '../certificate.service';
import { Certificate } from '../vo/Certificate';


@Component({
  selector: 'certificate-tracker-detail',
  templateUrl: './certificate-tracker-detail.component.html'
})
export class CertificateTrackerDetailComponent implements OnInit {
  certificate:Certificate;
	constructor(private titleService: Title,  private route: ActivatedRoute,private router:Router,private service:CertificateService) { }

	ngOnInit() {
    this.certificate= new Certificate();
    this.route.params.switchMap((_params: Params) =>this.service.getCertificateById(_params['_id'])).subscribe(x=>{
      this.certificate=x;
      console.log(this.certificate,"in detail")
    })
    }
    
    navigateToListPage(){
        this.router.navigateByUrl("/recruitment/certificate-tracker/certificate-tracker-list");   
       }
	
	
}
 