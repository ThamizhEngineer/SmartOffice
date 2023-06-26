import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{AddTestParticipantsService} from '../../../knowledge-assessment/add-test-participants/add-test-participants.service';
import { UserService } from '../../../../auth/_services';
import {User} from '../../../../auth/_models/user';
import { IncidentTestService } from '../incident-test.service';

@Component({
    selector:'take-test-list',
    templateUrl:'take-test-list.component.html'
})

export class TakeTestListComponent implements OnInit{
tests:any;
currentUser:User;
    constructor(private router:Router,
        private addTestParticipantsService:AddTestParticipantsService,
        private service:IncidentTestService,
        private _userService:UserService){

    }
    ngOnInit(){
         this.currentUser=this._userService.getCurrentUser();

    //      this.service.getFetchAllTest().subscribe(x=>{
    // this.tests=x;
        
this.service.fetchByApplicantId(this.currentUser.applicantId).subscribe(x=>{
this.tests=x;
console.log(this.tests);
})

}



 


}



// this.service.getFetchAllTest().subscribe(x=>{
//     this.tests=x;