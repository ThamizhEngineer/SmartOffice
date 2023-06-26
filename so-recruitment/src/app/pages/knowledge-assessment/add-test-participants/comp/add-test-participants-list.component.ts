import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{AddTestParticipantsService} from '../add-test-participants.service';


@Component({
    selector:'add-test-participants-list',
    templateUrl:'add-test-participants-list.component.html'
})

export class AddTestParticipantsListComponent implements OnInit{
testParticipants:any;
    constructor(private router:Router,private addTestParticipantsService:AddTestParticipantsService){

    }
    ngOnInit(){
  
this.addTestParticipantsService.getAllTestPreprations().subscribe(x=>{
this.testParticipants=x;
})

}



 


}