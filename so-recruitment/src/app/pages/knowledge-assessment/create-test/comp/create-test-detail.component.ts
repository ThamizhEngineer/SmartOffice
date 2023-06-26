import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AddTestParticipantsService } from '../../add-test-participants/add-test-participants.service';
import { TestParticipant, TestPreparation } from '../../add-test-participants/vo/TestPreparation';
import { Test } from '../vo/Test';
import { UserService } from '../../../../auth/_services';
import { empty } from 'rxjs/Observer';


@Component({
    selector: 'create-test-detail',
    templateUrl: 'create-test-detail.component.html'
})

export class CreateTestDetailComponent implements OnInit {
    testpreparation: TestPreparation;
    testParticipant: TestParticipant;
    addScreen: boolean = false;
    testTypes: any = [];
    saveMsg: any;
    dummy:any;
    createdTest:Array<Test>;
    testUser:Array<Test>;
    addTestParticipantsCount = 1;
    testPrepId: any
    constructor(private router: Router, private route: ActivatedRoute, private addTestParticipantsService: AddTestParticipantsService,private _userService:UserService) {

    }
    ngOnInit() {
        this.testpreparation = new TestPreparation();
        this.testParticipant = new TestParticipant();
        this.createdTest= new Array<Test>();
        this.testUser= new Array<Test>();
        this.testpreparation.testParticipants = new Array<TestParticipant>();
        if (this.route.params['_value']['_id'] === undefined) {
            this.addScreen = true;
        }

        if (this.addScreen) {
            this.testpreparation.testParticipants = [new TestParticipant];

        }
        else {
            this.route.params.switchMap((par: Params) => this.addTestParticipantsService.getAllTestPreparationById(par['_id'])).subscribe(x => {
                this.testpreparation = x;
                this.testPrepId = x.id;
            console.log(x.testParticipants);
            if(x.testParticipants.length<=0)
                    this.addTestParticipants();
                
            });

        }
        this.addTestParticipantsService.getAllTestPreprations().subscribe(x => {
            this.testTypes = x;
        })


    }
    addTestParticipants() {
        this.addTestParticipantsCount++;
        let ac = new TestParticipant();

        console.log(ac);
        this.testpreparation.testParticipants.push(ac);
    }


    deleteTestParticipants(i) {
        this.testpreparation.testParticipants.splice(i, 0);
    }

    createTestPreparation() {
     
            this.addTestParticipantsService.createTest(this.testPrepId,this.dummy).subscribe(x => {
              this.createdTest=x;
              this.createdTest.forEach(x=>{
                let currentUser=this._userService.getCurrentUser();
           
               
              if(currentUser.userName==x.participantId){
                console.log(currentUser.userName)
                console.log(x.participantId)
                
                  this.testUser.push(x);
                  console.log(this.testUser);
              }
              })
               
             this.router.navigateByUrl("/index");
            })
        
        
    }



}