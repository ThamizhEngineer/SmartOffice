import { Component ,OnInit} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{ TestResulService } from '../result.service';


@Component({
    selector:'test-result',
    templateUrl:'test-result-list.component.html'
})

export class TestResultListComponent implements OnInit{
    testResult:any;
row:any;
msg:any;
    constructor(private router:Router,private testResulService:TestResulService){

    }
    ngOnInit(){
  
this.testResulService.getAllTestResulService().subscribe(x=>{
this.testResult=x;
});

}

submitTestResult(particpantId? :any){

    this.testResulService.evalTest(particpantId).subscribe(x=>{
        this.msg = { type: 'success', text: "Updated successfully" }
});

}



 


}