import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { AddTestParticipantsService } from '../add-test-participants.service';
import { TestParticipant, TestPreparation } from '../vo/TestPreparation';


@Component({
    selector: 'add-test-participants-detail',
    templateUrl: 'add-test-participants-detail.component.html'
})

export class AddTestParticipantsDetailComponent implements OnInit {
    testpreparation: TestPreparation;
    testParticipant: TestParticipant;
    addScreen: boolean = false;
    testTypes: any = [];
    saveMsg: any;
    addTestParticipantsCount = 1;
    testPrepId: any
    constructor(private router: Router, private route: ActivatedRoute, private addTestParticipantsService: AddTestParticipantsService) {

    }
    ngOnInit() {
        this.testpreparation = new TestPreparation();
        this.testParticipant = new TestParticipant();
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

    addTestPreparation() {
        if (this.testPrepId != null) {
            this.addTestParticipantsService.updateTestParticipants(this.testpreparation, this.testPrepId).subscribe(x => {
                this.saveMsg = {
                    type: 'success', text: "Updated successfully"
                }
                this.router.navigateByUrl("/partcipants-list");
            })
        } else {
            this.addTestParticipantsService.addTestParticipants(this.testpreparation).subscribe(x => {
                this.saveMsg = {
                    type: 'success', text: "Added successfully"
                }
                this.router.navigateByUrl("/partcipants-list");
            })

        }
    }



}