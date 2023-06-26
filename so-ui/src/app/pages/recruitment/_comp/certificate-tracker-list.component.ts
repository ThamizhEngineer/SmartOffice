import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Title }     from '@angular/platform-browser';
import { CertificateService } from '../certificate.service';


@Component({
  selector: 'certificate-tracker-list',
  templateUrl: './certificate-tracker-list.component.html'
})
export class CertificateTrackerComponent implements OnInit {
  certificates:any=[];
  pageSize :number = 10;
	page :number = 1;
	constructor(private titleService: Title, private router:Router,private service:CertificateService) { }

	ngOnInit() {
		this.getAllCertificatesData();
    }
    getAllCertificatesData(){
      this.service.getAllCertificates().subscribe(x=>{
        this.certificates=x;
        console.log(this.certificates);
      })
    }
    
    certificateDetail(_id:string){

      console.log(_id)

      this.router.navigateByUrl("/recruitment/certificate-tracker/certificate-tracker-detail/"+_id);   
     }
	
	
}
 