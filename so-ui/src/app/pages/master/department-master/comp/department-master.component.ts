import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Department } from '../vo/department';
import { DepartmentMasterService } from '../department-master.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
    selector: 'dept-list',
    templateUrl: 'department-master.component.html'
})

export class DepartmentMasterComponent implements OnInit {
    @ViewChild('cdetail') cdetail:TemplateRef<any>;
    department: Department;
    // rows:Array<Department>;
    rows:any=[];
    saveMsg:any;
    page :number = 1;
    pageSize :number = 10;

    constructor(private router:Router,private route:ActivatedRoute,private modalService:NgbModal,
        private departmentMasterService:DepartmentMasterService) { }

    ngOnInit() {
        this.getData();
        this.department=new Department();

     }
     
    getData(){
        this.departmentMasterService.getAllDepartment().subscribe(x=>{
            this.rows=x;
            console.log(x);
            console.log(this.department);

        });
    }

    navigateToDetailPage(){
        this.router.navigateByUrl("/operation/department-master/dept-master-detail");   
       }

       modalReference:any=null;
       modalData: any;
   
       create(){
           this.department=new Department();
           this.modalData = new Department();
           this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});
       } 
    
       show(id:string){
        this.departmentMasterService.getAllDepartmentById(id).subscribe(x=>{
            this.department=x;
            this.modalReference=this.modalService.open(this.cdetail,{size:'lg'});

        });
    }
    save(){
     
        if(this.department.id){
            
                this.departmentMasterService.updateDepartment(this.department, this.department.id).subscribe(x => { 
                // this.rows=x;
                console.log(x)
                this.saveMsg = { 'type': 'success', 'text': "Updated Successfully" };
                this.getData();
                this.modalReference.close();

            } );             
         }
        
         else{
            this.departmentMasterService.createDepartment(this.department).subscribe(x =>{
            // this.rows=x;
            this.saveMsg = { 'type': 'success', 'text': "Created Successfully" };
            console.log(x)
            this.getData();
            this.modalReference.close();

        
        });
        }
    }
    delete(id:string){
        this.departmentMasterService.deleteDepartmentById(id).subscribe(x=>{
            this.saveMsg={'type':'success','text':"Deleted Successfully"};
            this.getData();
        },error=>{
            this.saveMsg={'type':'danger','text':"Server Error"};
        });
    }
}