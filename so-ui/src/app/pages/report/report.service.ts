import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { environment } from './../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';
import { Observable } from 'rxjs/Rx'
import helper from './report-helper';

@Injectable()
export class ReportService {
    constructor(private http: Http, private commonService: CommonService) { }


    fetchOrderIntake(soCode?: string, soOrderAmount?: string, isVirtualPo?: string,
        virtualPoNum?: string, hasGoods?: string, hasServices?: string,
        buName?: string, divisionName?: string, functionName?: string,
        clientCode?: string, clientName?: string, countryName?: string,
        jobCodes?: string, monthNo?: string, year?: string) {
        var url = environment.reportApiUrl + 'reports/order-intake/summary';
        url = url + "?dummy=1";
        url = helper.formUrlOrderIntake(url, soCode, soOrderAmount, isVirtualPo,
            virtualPoNum, hasGoods, hasServices,
            buName, divisionName, functionName,
            clientCode, clientName, countryName,
            jobCodes, monthNo, year);
        return this.http.get(url, this.commonService.jwt()).map((res: Response) => res.json());
    }

    fetchRevenue(soCode?: string, invoiceCode?: string, monthNo?: string, year?: string,
        quarterName?: string, buName?: string, divisionName?: string,
        functionName?: string, clientCode?: string, clientName?: string,
        countryName?: string, jobCodes?: string) {
        var url = environment.reportApiUrl + 'reports/revenue/summary';
        url = url + "?dummy=1";
        url = helper.formUrlOrderRevenue(url, soCode, invoiceCode, monthNo, year, quarterName, buName, divisionName,
            functionName, clientCode, clientName, countryName, jobCodes);
        return this.http.get(url, this.commonService.jwt()).map((res: Response) => res.json());
    }

    exportOrderIntake(soCode?: string, soOrderAmount?: string, isVirtualPo?: string,
        virtualPoNum?: string, hasGoods?: string, hasServices?: string,
        buName?: string, divisionName?: string, functionName?: string,
        clientCode?: string, clientName?: string, countryName?: string,
        jobCodes?: string, monthNo?: string, year?: string) {
        var url = environment.reportApiUrl + 'reports/order-intake/summary.xls';
        url = url + "?dummy=1";
        url = helper.formUrlOrderIntake(url, soCode, soOrderAmount, isVirtualPo,
            virtualPoNum, hasGoods, hasServices,
            buName, divisionName, functionName,
            clientCode, clientName, countryName,
            jobCodes,monthNo, year);
        return this.http.get(url, this.commonService.jwtDownloadResArrayBuffer()).catch((err: Response) => Observable.throw(err.json()));
    }

    exportRevenue(soCode?: string, invoiceCode?: string, monthNo?: string, year?: string,
        quarterName?: string, buName?: string, divisionName?: string,
        functionName?: string, clientCode?: string, clientName?: string,
        countryName?: string, jobCodes?: string) {
        var url = environment.reportApiUrl + 'reports/revenue/summary.xls';
        url = url + "?dummy=1";
        url = helper.formUrlOrderRevenue(url, soCode, invoiceCode, monthNo, year, quarterName, buName, divisionName,
            functionName, clientCode, clientName, countryName, jobCodes);
        return this.http.get(url, this.commonService.jwtDownloadResArrayBuffer()).catch((err: Response) => Observable.throw(err.json()));
    }

}