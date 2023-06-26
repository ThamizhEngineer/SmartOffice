import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { payoutAdjustmentService } from '../payout-adjustment.service';

@Component({
    selector: 'payout-adjustment',
    templateUrl: 'payout-adjustment-list.component.html'
})

export class payoutAdjustmentComponent implements OnInit {
    rows: any=[];
    payYear: string;
    payMonth: string;

    month = [
        { id: "1", month: "1" },
        { id: "2", month: "2" },
        { id: "3", month: "3" },
        { id: "4", month: "4" },
        { id: "5", month: "5" },
        { id: "6", month: "6" },
        { id: "7", month: "7" },
        { id: "8", month: "8" },
        { id: "9", month: "9" },
        { id: "10", month: "10" }
    
      ];

      year = [
        { id: "0", year: "2018" },
        { id: "1", year: "2019" },
        { id: "2", year: "2020" },
        { id: "3", year: "2021" },
        { id: "4", year: "2022" },
        { id: "5", year: "2023" },
        { id: "6", year: "2024" },
        { id: "7", year: "2025" },
        { id: "8", year: "2026" },
        { id: "9", year: "2027" },
        { id: "10", year: "2028" }
    
      ];


    constructor(private router: Router,private route: ActivatedRoute, private service:payoutAdjustmentService) { }

    ngOnInit() {
        this.getAll();
     }

    getAll(){
    this.service.getPayOutAdj().subscribe(_emps => {
        this.rows = _emps;
        console.log(this.rows);
    });
}

mpOnModelChange($event){
    console.log($event)
    this.payYear = $event
}

onModelChange1($event){
    console.log($event)
    this.payMonth = $event

}

navigateToDetailPage(){
    // console.log(this.payMonth)
    // console.log(this.payYear)

    this.router.navigateByUrl("/operation/payout-adjustment/new/"+this.payMonth+"/"+this.payYear);
}
}