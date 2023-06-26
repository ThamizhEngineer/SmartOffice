import { Component, OnInit, Input, ViewChild, TemplateRef } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { JobMilestone } from '../../../pages/job/vo/job';

import { SaleOrderService } from '../../_services/sale-order.service';
import { SaleOrder} from '../../../pages/job/vo/sale-order';


@Component({
    selector: 'sale-order-model',
    templateUrl: 'sale-order-model.component.html',
    providers: [SaleOrderService]
})

export class SaleOrderModelComponent implements OnInit {
    @Input() public id;
    @Input() public isClient;


    @ViewChild('viewSatus') viewSatus: TemplateRef<any>;


    saleOrder:SaleOrder;
    page = 1;
    pageSize = 6;
    modalReference: any = null;

    jobMilestone: JobMilestone;
    jobMilestones: Array<JobMilestone>

    isMileStone:boolean=true;

    constructor(private _saleOrderService:SaleOrderService,private modalService: NgbModal) { }

    ngOnInit() {
        this.starter();
     }

    starter(){
        this.saleOrder = new SaleOrder();
        if(this.isClient=='Y'){
           this._saleOrderService.saleOrderWithJob(this.id).subscribe(x=>{
               this.saleOrder=x;
               console.log(this.saleOrder);
           })
        }else {
            this._saleOrderService.getSaleOrder(this.id).subscribe(x=>{
                this.saleOrder =x;
                console.log("N"+this.saleOrder);
            });
        }		
    }

    ProgressBar(id) {
        this.jobMilestone = new JobMilestone();
        this.jobMilestones = [new JobMilestone];
        this.isMileStone=true;

        this._saleOrderService.getJobStatusByJobId(id).subscribe(x => {
            console.log(x)
            this.jobMilestones = x;
            if (this.jobMilestones.length ==0) {
                this.isMileStone=false;
            }
        }, error => {
            console.log(error)
        });
        this.modalReference = this.modalService.open(this.viewSatus, { size: 'lg' });
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