import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { EmployeeServiceService } from '../employee-service.service';
import { EmployeeServiceHeader } from '../vo/record';
import { EmployeeServiceLines } from '../vo/record';
import { Employee } from '../../../vo/employee';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';
import { environment } from '../../../../../environments/environment';
import { User } from '../../../../auth/_models';
import { UserService } from '../../../../auth/_services';


@Component({
    selector: 'EmpSer',
    templateUrl: 'employee-service.component.html'
})

export class EmployeeServiceComponent implements OnInit {
    @ViewChild('cdetail') cdetail: TemplateRef<any>;



    saveMsg: { 'type': string; 'text': string; };
    empCode: any="";
    id: String;
    officeName: any;
    n1EmpId: any="";
    code: any = [];
    employees: any;
    empDetail: any[];
    empFname: any="";
    firstName: any;
    http: any;
    commonService: any;
    internalId: String = null;
    user: User;
    isDir: boolean = false;
    isHr1: boolean = false;
    emp: any = [];
    rows: Array<EmployeeServiceHeader>;
    lines: Array<EmployeeServiceLines>;
    employeeCount: Array<EmployeeServiceHeader>;
    employeeServiceHeader: EmployeeServiceHeader;
    offices: any = [];
    busKeyCount: number = 0;
    n1Id: any="";
    n1Name: any="";
    empName: any="";
    page :number = 1;
    pageSize :number = 10;

    constructor(private route: Router, private activedRouter: ActivatedRoute, private userService: UserService,
        private service: EmployeeServiceService, private modalService: NgbModal) { }



    taskTypeAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.emp.filter(v => v.firstName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
    tasktype_formatter = (x: { firstName: string }) => x.firstName;

    emptaskType = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.emp.filter(v => v.firstName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
    empformatter = (x: { firstName: string }) => x.firstName;


    codetaskType = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.emp.filter(v => v.internalId.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
    codeformatter = (x: { internalId : string }) => x.internalId;

    empIdTypeAC = (text$: Observable<string>) => text$.pipe(debounceTime(200), distinctUntilChanged(), map(term => term === '' ? [] : this.emp.filter(v => v.empName.toLowerCase().indexOf(term.toLowerCase()) > -1).slice(0, 10)));
    formatter3 = (x: { empName: string }) => x.empName;

    ngOnInit() {
        // this.employeeServiceHeader = new EmployeeServiceHeader();  
        this.getData();
        this.getEmployee();
        this.getOffice();
        this.rows = new Array<EmployeeServiceHeader>();
        this.employeeServiceHeader = new EmployeeServiceHeader();

        this.service.getType().subscribe(x => {
            this.code = x;
        })

        this.user = this.userService.getCurrentUser();

        this.user.userGroupMappings.forEach(element => {
            if (element.isHrL1 == 'Y' || element.isHrL2 == 'Y') {
                this.isHr1 = true;
            } if (element.isDir == 'Y') {
                this.isDir = true;
            }
        });
    }


    getOffice() {
        this.service.getOffices().subscribe(x => {
            this.offices = x;
            console.log(this.offices);
        })
    }

    getData() {
        this.service.getEmployeeService().subscribe(x => {
            this.rows = x;
            console.log(this.rows);
        })
    }

    getEmployee() {
        this.service.getEmployeeProfile().subscribe(x => {
            this.emp = x.memployeeList;
            console.log(this.emp);
        })
    }

    modalReference: any = null;
    modalData: any;
    create() {
        this.employeeServiceHeader = new EmployeeServiceHeader();
        this.modalData = new EmployeeServiceHeader();
        this.modalData.lt = false;
        this.modalReference = this.modalService.open(this.cdetail, { size: 'lg' });
    }

    show(id: number) {
        this.service.getEmployeeServiceId(id).subscribe(x => {
            this.employeeServiceHeader = x;
            console.log(this.employeeServiceHeader);
            this.modalReference = this.modalService.open(this.cdetail, { size: 'lg' });
        });

    }
    save() {

        if (this.employeeServiceHeader.id) {

            this.service.updateEmployeeService(this.employeeServiceHeader, this.employeeServiceHeader.id).subscribe(x => {

                this.saveMsg = { 'type': 'success', 'text': "employee ServiceHeader Updated Successfully" };
                this.getData();
                this.modalReference.close();
            });
        }

        else {


            this.service.addEmployeeService(this.employeeServiceHeader).subscribe(x => {
                console.log(this.employeeServiceHeader);
                this.saveMsg = { 'type': 'success', 'text': "employee ServiceHeader Created Successfully" };
                this.getData();
                this.modalReference.close();
            });
        }
    }
    delete(id: string) {
        this.service.deleteEmployeeService(id).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Day Range Deleted Successfully" };
            this.getData();
        }, error => {
            this.saveMsg = { 'type': 'danger', 'text': "Server Error" };
        });
    }
    addEmployee() {
        this.busKeyCount++;
        console.log(this.busKeyCount)
        let ec = new EmployeeServiceLines();
        console.log(ec);
        this.employeeServiceHeader.employeeServiceLine.push(ec);

    }
    delEmployeeRow(iter) {

        this.employeeServiceHeader.employeeServiceLine.splice(iter, 1);
        console.log(this.employeeServiceHeader.employeeServiceLine);
    }
    search() {
        this.service.advanceSearch(this.internalId, this.empCode, this.empFname, this.officeName, this.n1EmpId).subscribe(x => {
            console.log(this.internalId)
            this.rows = x;
            // this.rows.forEach(element => {
            //     this.rows.push(element);
            //     console.log(element)
            // });
            console.log(this.rows)
        })
    }

    reset() {
        this.internalId = null; this.empCode = null; this.empFname = null; this.officeName = null; this.n1EmpId = null
        this.search();
    }

    employeeSelected($event){
        this.employeeServiceHeader.mempId = $event.id;
        this.empName = $event.empName;
    }

    internalIdSelected($event) {
        this.id = $event.id;
        this.internalId = $event.internalId;
    }

    empCodeSelected($event) {
        this.emp.id = $event.id;
        this.empCode = $event.empCode;
    }

    empFirstNameSelected($event) {
        this.emp.id = $event.id;
        this.empCode = $event.empCode;
    }
    n1Selected($event){
        console.log($event)
        this.n1EmpId= $event.id;
        this.n1Name=$event.empName;      
    }

    selectchange($event) {
        console.log($event)
        this.emp.id = $event.item;
        console.log($event.item)
    }

    projectSelected3($event) {
        console.log($event)
        var eh = new EmployeeServiceHeader();
        this.employeeServiceHeader.internalId = $event.item.internalId;
        this.employeeServiceHeader.empCode = $event.item.empCode;
        this.employeeServiceHeader.empFname = $event.item.empFname;
        this.employeeServiceHeader.empLname = $event.item.empLname;
        this.employeeServiceHeader.officeName = $event.item.officeName;
        this.employeeServiceHeader.n1EmpId = $event.item.n1EmpId;
        this.employeeServiceHeader.n2EmpId = $event.item.n2EmpId;
    }


    self() {
        if (this.empFname) {
            // this.route.navigateByUrl('/transaction/employeeServiceRecord/advance-filters/?&&empFname='+this.firstName);
            return this.http.get(environment.serviceApiUrl + '/transaction/employeeServiceRecord/advance-filters/?&&empFname=' + this.firstName, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());

        }
    }
    cod() {
       
    }


    empi() {

    }

}
