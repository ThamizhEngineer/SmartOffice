
import { Component ,OnInit,ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import {EmployeeService} from '../employee.service';
import { Employee } from '../../../vo/employee';
import { status_css } from '../../../vo/status'
import { Observable } from 'rxjs/Observable';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { Observer } from 'rxjs';
import { ResponseContentType, Http } from '@angular/http';
import { HttpHeaders } from '@angular/common/http';
import{SecurePipe}from '././../secure-pipe.component';
import { environment } from '../../../../../environments/environment';
import { CommonService } from '../../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'employee-list',
    
    templateUrl: './employee-list.component.html',
    providers: [SecurePipe]
})

export class EmployeeListComponent implements OnInit {
    @ViewChild('uploadInfo') uploadInfo: TemplateRef<any>;    
    searchEmpName="";
    searchDesigName="";
    mainImgUrl:boolean;
    searchEmailId="";
    searchContactMobileNo="";
    serachOfficeName="";
    searchEmpCode="";
    searchEmpStatus="";
    serachId="";
    allEmployees:any=[];
    isAdvanceSerach = false;
    employee:Employee;
    statuses:any=status_css;
    finalList:any=[];
    docLocation:any;
    binding:any='true';
    imageUrl = '';
    rows:Array<Employee>;
    errorMsg: any;
    saveMsg: any;
    imgMale=environment.assetsUrl+"/assets/img/users/en1.png";
    imgFemale=environment.assetsUrl+"/assets/img/users/en1.png";
    documentApiUrl: string = environment.documentApiUrl;
    uploadEmployee:any=[];
    modalReference:any;
    
    formatter = (x: {empName: string}) => x.empName;
	profileAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.allEmployees.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );

    constructor(private router:Router,private commonService:CommonService,private modalService: NgbModal,private http: Http,private service:EmployeeService){

    }
    
    ngOnInit() {
    this.employee = new Employee();
    this.rows = new Array<Employee>();
    this.getEmployee();

    this.service.getEmployees().subscribe(x=>{
        this.allEmployees=x;
    })

    }
  
    bearerToken  = 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...';

    activeOrInActive(){
        if (this.isAdvanceSerach==true) {
            this.isAdvanceSerach=false;
        }
        else{
            this.isAdvanceSerach=true;
        }
    }

    
 
    getEmployee(){
        this.finalList=[];
        this.service.hr1GetEmployee(this.searchEmpName,this.searchDesigName,this.searchEmailId,this.searchContactMobileNo,
            this.serachOfficeName,this.searchEmpCode,this.searchEmpStatus,this.serachId).subscribe(_emps=>{
            for(let _emp of _emps.memployeeList){
                     this.docLocation=_emp.docLocation+"/"+_emp.docName;
                      if(_emp.docId == null || _emp.docId == "") {
                        _emp.docUrl = (_emp.gender == "male")?this.imgMale:this.imgFemale; 
                      }
                      else{
                        _emp.docUrl=environment.documentApiUrl+"dm/"+_emp.docId;
                      }
                    this.finalList.push(_emp);
                }
        })
    }

    deleteRow(id:any){
        this.service.deleteEmployee(id).subscribe(x=>{
           this.finalList=[];
            this.getEmployee();
        },error=>{
           
        });
    }
  
    changeView(value){
        this.binding=value;
    }

    navigateToDetailPage(_id:number){
        this.router.navigate(['/operation/employee/detail/'] ,{queryParams:{flowType:"Edit Employee",id:_id}});
    //  this.router.navigateByUrl("/operation/employee/detail/"+id);   
    }

    fileChanged($event){
        let fileList: FileList = $event.target.files;        
        if (fileList.length > 0) {  
            let files: File = fileList[0];          
			 this.commonService.uploadEmployee(files).map((response: Response) => response)
                .catch(error => Observable.throw(error))
                .subscribe(
                    data => {    
                        this.uploadEmployee=data;
                        this.modalReference = this.modalService.open(this.uploadInfo, {size: 'lg'});
                        this.ngOnInit();                                                                           
                    },
                    error => {                       
			this.errorMsg = JSON.parse(error._body);
			this.saveMsg = { type: 'danger', text: this.errorMsg.message }
                    }
                )}  
    }

}
