import { Component, OnInit, Directive,  Input, Output, ViewChild,TemplateRef, EventEmitter, ViewChildren, QueryList } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobService } from '../job.service';
import { ClientPurchaseOrderService } from '.././sale-order/client-purchase-order.service';
import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { ClientPurchaseOrder } from '../vo/ClientPurchaseOrder';
import { Partner } from '../vo/partner';
import { environment } from './../../../../environments/environment';
import { PartnerService } from '../sale-order/partner-service';
import { saveAs } from 'file-saver';
import { Headers, Http, RequestOptions, Response, URLSearchParams,ResponseContentType } from "@angular/http";

import{OfficeMasterService} from '../office-master/office-master.service';
import { UserGroupEmployeeMappingService } from './user-group-employee-mapping.service';
import { UserGroupEmployeeMapping } from './vo/user-group-employee-mapping';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';




export type SortDirection = 'asc' | 'desc' | '';
const rotate: {[key: string]: SortDirection} = { 'asc': 'desc', 'desc': '', '': 'asc' };
export const compare = (v1, v2) => v1 < v2 ? -1 : v1 > v2 ? 1 : 0;

export interface SortEvent {
  column: string;
  direction: SortDirection;
}

@Directive({
  selector: 'th[sortable]',
  host: {
    '[class.asc]': 'direction === "asc"',
    '[class.desc]': 'direction === "desc"',
    '(click)': 'rotate()'
  }
})
export class NgbdSortableHeader {

    @Input() sortable: string;
    @Input() direction: SortDirection = '';
    @Output() sort = new EventEmitter<SortEvent>();
  
    rotate() {
      this.direction = rotate[this.direction];
      console.log(this.direction)
      console.log(this.sortable)

      this.sort.emit({column: this.sortable, direction: this.direction});
    }
  }

//-----------------------------------------------------------------------------------------------------------
@Component({
    selector: 'user-group-employee-mapping-list',
    templateUrl: './user-group-employee-mapping-list.component.html'
}) export class UserGroupEmployeeMappingComponent implements OnInit {
    @ViewChild('vdetail') vdetail: TemplateRef<any>;
    @ViewChildren(NgbdSortableHeader) headers: QueryList<NgbdSortableHeader>;
    sort: any =[];

    clientId: string;
    clientName: string;
   
    userGroupNameAC = (text$: Observable<string>) => text$.pipe( debounceTime(200), distinctUntilChanged(), map(term => term === '' ? this.userGroups : this.userGroups.filter(v => v.userGroupName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)) );
    userGroupNameFormatter = (x:{userGroupName: string}) =>x.userGroupName;
    
    constructor(private router: Router, private http: Http,private modalService: NgbModal, private userGroupEmployeeMappingService: UserGroupEmployeeMappingService) {

    }
    userGroupEmp:UserGroupEmployeeMapping;
    userGroupEmpList:Array<UserGroupEmployeeMapping>;
    userGroupEmps:any=[];
    userGroups:any=[];
    emp:any=[];
    country:any=[];
    documentApiUrl: string = environment.documentApiUrl;
    page :number = 1;
    pageSize :number = 20;
    ngOnInit() {
     this.userGroupEmp = new UserGroupEmployeeMapping();
     this.userGroupEmpList = new Array<UserGroupEmployeeMapping>();;
     this.userGroupEmployeeMappingService.getAllUserGroupEmployeeMappings().subscribe(x=>{
         this.userGroupEmps=x;
         this.sort=x;
     });
     this.userGroupEmployeeMappingService.getAllUserGroups().subscribe(x=>{
        this.userGroups=x;
        console.log(x)
       
     });
     this.userGroupEmployeeMappingService.getAllEmployees().subscribe(x=>{
         this.emp=x;
     })
     
    }
 
getuserGroupEmps(){
    this.userGroupEmployeeMappingService.getAllUserGroupEmployeeMappings().subscribe(x=>{
        this.userGroupEmps=x;
    });
}
   


    modalReference: any = null;

    showDetail(id?: any) {
        this.userGroupEmployeeMappingService.getUserGroupEmployeeMappingById(id).subscribe(x => {
            this.userGroupEmp = x;
            this.userGroupEmpList.push(this.userGroupEmp);

            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        });
    }
    createNew() {
        this.userGroupEmp = new UserGroupEmployeeMapping();

        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }
     saveMsg: any;
     errorMsg: any;
    save() {
        console.log(this.userGroupEmp.id)
        console.log(this.userGroupEmpList)
        if (this.userGroupEmp.id) {
            this.userGroupEmployeeMappingService.createorUpdateUserGroupEmployeeMapping(this.userGroupEmpList).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "User Group Employee Mapping Updated Successfully" };
                this.getuserGroupEmps();
                
                this.modalReference.close();
            });
    }else{
        this.userGroupEmployeeMappingService.createUserGroupEmployeeMapping(this.userGroupEmp).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "User Group Employee Mapping Created Successfully" };
            this.getuserGroupEmps();
            this.modalReference.close();
        },error=>{
            this.errorMsg = {'type': 'danger', 'text':"Select User Group Name and Employee Name"};
        });
    }


    }
    

    deleteRow(id?: any){
		this.userGroupEmployeeMappingService.deleteUserGroupEmployeeMapping(id).subscribe(x=>{
            console.log(x);
            this.saveMsg = {'type': 'success', 'text': "User Group Employee Mapping deleted Successfully"};
            this.getuserGroupEmps();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }

        onSort({column, direction}: SortEvent) {
            this.headers.forEach(header => {
              if (header.sortable !== column) {
                header.direction = '';
              }
            });
    
        if (direction === '') {
            this.userGroupEmps=this.sort;
          } else {
            this.userGroupEmps = [...this.userGroupEmps].sort((a, b) => {
              const res = compare(a[column], b[column]);
              return direction === 'asc' ? res : -res;
            });
          }
        }

        userGroupNameSelect($event){
          console.log($event)
          this.userGroupEmp.userGroupId = $event.item.id;
          this.userGroupEmp.userGroupName = $event.item.userGroupName;

        }

        empNameSelected($event){
          this.userGroupEmp.employeeId = $event.id;
        this.userGroupEmp.employeeName= $event.empName;
        }

}