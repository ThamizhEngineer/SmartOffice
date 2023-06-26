import { Component, OnInit } from '@angular/core';
import { ReportService } from '../report.service';
import { CommonService } from '../../../shared/common/common.service';

@Component({
    selector: 'revenue',
    templateUrl: 'revenue.component.html'
})

export class RevenueComponent implements OnInit {
    value_x;
    rows;

    //pagination
    page = 0;
    pageSize = 10;
    rowSize = 0;

    //Month config
    mpFromConfig;
    mpSalModel;

    //Filters
    soCode; invoiceCode; monthNo; year; quarterName; buName; divisionName;
    functionName; clientCode; clientName; countryName; jobCodes;

    constructor(private reportService: ReportService, private commonService: CommonService) { }

    ngOnInit() { }

    search() {
        this.reportService.fetchRevenue(this.soCode, this.invoiceCode, this.monthNo, this.year, this.quarterName, this.buName, this.divisionName,
            this.functionName, this.clientCode, this.clientName, this.countryName, this.jobCodes).subscribe(arg => {
                this.rows = arg.revenueList;
                this.rowSize = arg.length;
            });
    }

    reset() {
        this.soCode = "", this.invoiceCode = "", this.monthNo = "", this.year = "", this.quarterName = "", this.buName = "", this.divisionName = "",
            this.functionName = "", this.clientCode = "", this.clientName = "", this.countryName = "", this.jobCodes
    }

    export() {
        this.reportService.exportRevenue(this.soCode, this.invoiceCode, this.monthNo, this.year, this.quarterName, this.buName, this.divisionName,
            this.functionName, this.clientCode, this.clientName, this.countryName, this.jobCodes).subscribe(x => {
            this.commonService._saveFileInSystem(x.blob(), 'revenue-summary.xls');
        });
    }

    mpOnModelChange($event) {
        const mon = parseInt($event.month, 10)
        this.monthNo = mon.toString();
        this.year = $event.year;
    }
}