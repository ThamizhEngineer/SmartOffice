import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';

@Component({
    selector: 'role-access-detail',
    templateUrl: './detail.component.html'
})
export class RoleAccessDetailComponent implements OnInit {


    saveMsg: any;
    saveMsg1: any;
    months: any;
    fy: any;
    
	isFYModified: boolean = false;
    isEdit: boolean = false;
    

	updateFY(){}
	constructor(private router:Router, private activatedRoute: ActivatedRoute){

    }
	
    ngOnInit() {
		
    }
	
}