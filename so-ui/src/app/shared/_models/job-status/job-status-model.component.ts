import { Component, OnInit, Input } from '@angular/core';
import { JobMilestone } from '../../../pages/job/vo/job';
import { SaleOrderService } from '../../_services/sale-order.service';

@Component({
    selector: 'job-status-model',
    templateUrl: 'job-status-model.component.html',
    providers: [SaleOrderService]

})

export class JobStatusModelComponent implements OnInit {
    @Input() public id;

    jobMilestone: JobMilestone;
    jobMilestones: Array<JobMilestone>
    isMileStone:boolean=true;

    constructor(public _service: SaleOrderService) { }

    ngOnInit() {
        this.starter();
    }
    starter() {
        this.jobMilestone = new JobMilestone();
        this.jobMilestones = [new JobMilestone];
        this.isMileStone=true;
        this._service.getJobStatusByJobId(this.id).subscribe(x => {
            console.log(x)
            this.jobMilestones = x;
            if (this.jobMilestones.length ==0) {
                this.isMileStone=false;
            }
        }, error => {
            console.log(error)
        });
    }

    bayHide: string = "bay";
    bayHide1: string = "bay";
    bay1: any;
    bay2: any;
    bay3: any;
    stages: boolean = false;
    bay: boolean = false;

    stageClick($event: Event) {

    }

    modalData: any;
    hide(i: any) {
        console.log(i)
        if (this.bayHide1 == this.bayHide + i.toString()) {
            this.bayHide = null;
        } else {
            this.bayHide = this.bayHide + i.toString();
            this.bayHide1 = this.bayHide + i.toString();
        }
        console.log(this.bayHide)
    }

}