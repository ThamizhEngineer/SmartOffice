import { Component, OnInit } from '@angular/core';

import { EmployeeLocationService } from '../emp-location.service';


@Component({
    selector: '',
    templateUrl: 'emp-location.component.html'
})

export class EmployeeLocationComponent implements OnInit {
  
    empId:string='';
    empNameAndCode:string='';
    empLocations:any=[];
    searchField:string='';
    searchName:string='';
    page = 0;
    pageSize = 10;
    rowSize = 0;
    listLocation:any;
    constructor(
        private service:EmployeeLocationService
    ) { }

    ngOnInit() { 
        this.service.getAllSearchValue().subscribe(x=>{
            this.listLocation=x;
        })
    }

    onclick(){
        this.searchName='';
    }

    searchEmp(){
        this.service.getAllLocationByEmpId(this.searchField,this.searchName).subscribe(x=>this.empLocations=x);
    }

    projectSelected($event){      
        this.empNameAndCode=$event.empCode+" "+$event.empName;    
        this.searchName=$event.id;  
    }
}