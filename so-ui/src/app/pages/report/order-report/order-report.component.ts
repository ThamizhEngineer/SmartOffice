import { Component, OnInit } from '@angular/core';
import { ReportService } from '../report.service';
import { CommonService } from '../../../shared/common/common.service';
@Component({
    selector: 'order-report',
    templateUrl: 'order-report.component.html'
})

export class OrderReportComponent implements OnInit {
    rows=[];

    //pagination
    page = 0;
    pageSize = 10;
    rowSize = 0;

    //Month config
    mpFromConfig;
    mpSalModel;

    //search
    soCode; soOrderAmount; isVirtualPo;
    virtualPoNum; hasGoods; hasServices;
    buName; divisionName; functionName;
    clientCode; clientName; countryName;
    jobCodes;monthNo; year;
    constructor(private reportService: ReportService, private commonService: CommonService) { }

    ngOnInit() { }

    search() {
        this.check();
        this.reportService.fetchOrderIntake(this.soCode, this.soOrderAmount, this.isVirtualPo,
            this.virtualPoNum, this.hasGoods, this.hasServices,
            this.buName, this.divisionName, this.functionName,
            this.clientCode, this.clientName, this.countryName,
            this.jobCodes,this.monthNo,this.year).subscribe(x => {                
                if(x){
                    this.rows = x;
                    this.rowSize = x.length;
                }
            })
    }

    check() {
        if (this.isVirtualPo == 'All') {
            this.isVirtualPo = "";
        }
        if (this.hasGoods == 'All') {
            this.hasGoods = "";
        }
        if (this.hasServices == 'All') {
            this.hasServices = "";
        }
    }

    reset() {
        this.soCode = ""; this.soOrderAmount = ""; this.isVirtualPo = "";
        this.virtualPoNum = ""; this.hasGoods = ""; this.hasServices = "";
        this.buName = ""; this.divisionName = ""; this.functionName = "";
        this.clientCode = ""; this.clientName = ""; this.countryName = "";
        this.jobCodes = "";this.monthNo=""; this.year="";
    }

    export() {
        this.reportService.exportOrderIntake(this.soCode, this.soOrderAmount, this.isVirtualPo,
            this.virtualPoNum, this.hasGoods, this.hasServices,
            this.buName, this.divisionName, this.functionName,
            this.clientCode, this.clientName, this.countryName,
            this.jobCodes,this.monthNo,this.year).subscribe(x => {
            this.commonService._saveFileInSystem(x.blob(), 'order-intake-summary.xls');
        });
    }

    mpOnModelChange($event) {
        const mon = parseInt($event.month, 10)
        this.monthNo = mon.toString();
        this.year = $event.year;
    }
}