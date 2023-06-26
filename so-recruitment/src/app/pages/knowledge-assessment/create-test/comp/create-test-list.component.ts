import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{AddTestParticipantsService} from '../../add-test-participants/add-test-participants.service';


@Component({
    selector:'create-test-list',
    templateUrl:'create-test-list.component.html'
})

export class CreateTestListComponent implements OnInit{
testParticipants:any;
    constructor(private router:Router,private addTestParticipantsService:AddTestParticipantsService){

    }
    ngOnInit(){
  
this.addTestParticipantsService.getAllTestPreprations().subscribe(x=>{
this.testParticipants=x;
})

}



 


}