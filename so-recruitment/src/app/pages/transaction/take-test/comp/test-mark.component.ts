import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{AddTestParticipantsService} from '../../../knowledge-assessment/add-test-participants/add-test-participants.service';
import { UserService } from '../../../../auth/_services';
import { Test } from '../../../knowledge-assessment/create-test/vo/Test';
import { IncidentTestService } from '../incident-test.service';


@Component({
    selector:'test-mark',
    templateUrl:'test-mark.component.html'
})

export class TestMarkComponent implements OnInit{
tests:Test;
mark:any;
page :number = 1;
pageSize :number = 1;
    constructor(private router:Router,
        private route:ActivatedRoute,
        private service:IncidentTestService,
        private addTestParticipantsService:AddTestParticipantsService,
        private _userService:UserService){

    }
    ngOnInit(){
        this.tests = new Test();
        this.route.params.switchMap((par: Params) => this.service.getTestById(par['_id'])).subscribe(x => {
this.tests=x;
console.log(this.tests);
});

}
navigateToListPage(){
    this.router.navigateByUrl('transaction/take-test/take-test-list');
}


 


}