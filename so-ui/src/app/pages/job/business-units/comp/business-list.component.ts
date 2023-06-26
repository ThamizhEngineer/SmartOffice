import { Component, OnInit } from '@angular/core';
import { BusinessService } from '../business-service';
import { Router, ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BusinessUnit } from '../vo/business';


@Component({
    selector: 'busli',
    templateUrl: 'business-list.component.html'
})

export class BusinessListComponent implements OnInit {
    rows: Array<BusinessUnit>;
    businessUnit:BusinessUnit;
    constructor(private route: Router, private activedRouter: ActivatedRoute, private modalService: NgbModal, private service : BusinessService) { }

    ngOnInit() { 
        this.getBusines();
        this.businessUnit = new BusinessUnit();
    }

getBusines(){
    this.service.getBusiness().subscribe(x=> {
        this.rows = x;
        console.log(this.rows);
    })
}


delete(id){
    this.service.deleteBusiness(id).subscribe(x =>{
        console.log(x);
        this.ngOnInit();
    });


}



}