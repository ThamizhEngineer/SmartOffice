import { Component, OnInit, ViewChild, TemplateRef } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PayoutProcessService } from '../../payout-process/payout-process.service';
import { payoutAdjustmentService } from '../payout-adjustment.service';
import { FormBuilder } from '@angular/forms';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Payout } from '../payout';


@Component({
    selector: 'payout-adjustment-detail',
    templateUrl: 'payout-adjustment-detail.component.html'
})

export class payoutAdjustmentDetailComponent implements OnInit {
    @ViewChild('vdetail') vdetail: TemplateRef<any>;
    empCode: any;
    rows: any=[];
    modalReference: any = null;
    payout:Payout;
    saveMsg: { 'type': string; 'text': string; };
    row: any;
    ro: any;
    constructor(private formBuilder: FormBuilder,private modalService: NgbModal,private router: Router,private activatedRoute:ActivatedRoute,private route: ActivatedRoute,private service:payoutAdjustmentService) {

    }
    ngOnInit() {
        this.payout = new Payout();

        this.getAll();
        this.getCode();

        this.activatedRoute.params.subscribe(param=>{
           this.empCode=param;
        this.service.getPayOutByMonthYear(this.empCode.payMonth,this.empCode.payYear).subscribe(x=>{
            this.rows=x;
            console.log(this.rows);
        })

        });
     }

     getAll(){
        this.service.getPayOutAdj().subscribe(_emps => {
            this.ro = _emps;
            // console.log(this.rows);
        });
    }

    getCode(){
        this.service.getPayoutTypes().subscribe(_emps => {
            this.row = _emps;
            console.log(this.row);
        });
    }

     showDetail(id?: any) {
        this.service.getPayOutAdjById(id).subscribe(x => {
            this.payout = x;
            // this.officeList.push(this.office);

            this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
        });
    }

    createNew() {
        this.payout = new Payout();

        this.modalReference = this.modalService.open(this.vdetail, { size: 'lg' });
    }

    save() {
        console.log(this.empCode.payMonth)
console.log(this.empCode.payYear)
        if (this.payout.id) {
           
            this.service.updatePayOutAdjById(this.payout,this.payout.id).subscribe(x => {
                this.saveMsg = { 'type': 'success', 'text': "Office Updated Successfully" };
                this.ngOnInit();
                
                this.modalReference.close();
            });
    }else{
        console.log(this.empCode.payMonth)
        console.log(this.empCode.payYear)
                    this.payout.payMonth=this.empCode.payMonth;
                    this.payout.payYear=this.empCode.payYear;
        this.service.addPayOutAdj(this.payout).subscribe(x => {
            this.saveMsg = { 'type': 'success', 'text': "Office Created Successfully" };
            this.ngOnInit();
            // console.log(this.office);
            this.modalReference.close();
        });
    }
    }

    deleteRow(id?: any){
		this.service.deletePayOutAdjById(id).subscribe(x=>{
            console.log(x);
            this.saveMsg = {'type': 'success', 'text': "Office Deleted Successfully"};
            this.ngOnInit();
        },error=>{
            this.saveMsg = {'type': 'danger', 'text': "Server Error"};
        });
        }
}