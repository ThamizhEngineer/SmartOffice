
import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {ApplicantService} from '../applicant.service';
import { Applicant } from '../../../vo/applicant';
@Component({
    selector: 'applicant-list',
    templateUrl: './applicant-list.component.html',
  
})
export class ApplicantListComponent implements OnInit {
	pageSize :number = 10;
	page :number = 1;
    applicant:Applicant;
    rows:Array<Applicant>;
    // sub:any=[];
    saveMsg:any;
    contactEmailId:any;
    id:number;
    contactMobileNo:any;
    constructor(private router:Router,private service:ApplicantService){

    }

    ngOnInit() {
   this.onInint();
    }
    onInint(){
        this.applicant = new Applicant();
        this.rows = new Array<Applicant>();
        // this.sub= new Array<Applicant>();

    
    }

    get(){
        this.service.getApplicants().subscribe(element=>{
            this.rows =element;
            // this.sub.push(element);

            console.log(this.rows);
        })
    }

    reset(){
        this.contactMobileNo=null;this.contactEmailId=null
        this.search();
     }

     search(){
        //  console.log(this.sub)
        this.service.advanceSearch(this.contactMobileNo, this.contactEmailId).subscribe(x=>{
        this.rows=[];   
        x.forEach(element => {
                // if(element.firstName=='N'){
                //     element.firstName=null;
                //     this.rows.push(element);
                // }else{
                //   this.rows.push(element);
                // } 
                this.rows.push(element);
            });
            console.log(this.rows)
        })
     }

    navigateToDetailPage(_id:number){
        this.router.navigate(['/recruitment/applicant/detail/'] ,{queryParams:{flowType:"Edit Applicant",id:_id}});
    //  this.router.navigateByUrl("/operation/applicant/detail/"+id);   
    }

    deleteRow(id?: any){
		this.service.deleteApplicant(id).subscribe(x=>{
            console.log(x);
            this.saveMsg = {'type': 'success', 'text': "Applicant Deleted Successfully"};
            this.onInint();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}
