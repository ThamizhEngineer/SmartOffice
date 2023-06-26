import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { environment } from '../../../../environments/environment';
import { UploadPayslipHdr } from './../../vo/upload-payslip';
import { DocInfo } from './../../vo/doc-info';
import { CommonService } from '../../../shared/common/common.service';
@Injectable()
export class PayslipsService {

    baseUrl: string = environment.serviceApiUrl;
    documentApiUrl: string = environment.documentApiUrl;
    constructor(private http: Http, private commonService: CommonService) { }


    getData() {
        let url = 'assets/data/upload-paylsips.json';
        return this.http.get(url).map(res => res.json());
    }


    getPaySlipById(_id: string) {
        return this.http.get(environment.serviceApiUrl + 'csv/payslips/id/' + _id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    overWritePaySlip(docTypeCode: string, hdrId: string, id) {
        let url = environment.serviceApiUrl + 'csv/payslips/update-payslip/' + '/' + docTypeCode + '/' + hdrId + '/' + id;

        return this.http.get(url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getPaySlip() {
        return this.http.get(environment.serviceApiUrl + 'csv/payslips/get-all', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getPayslipByMonthAndYearAndOverwrteFlag(payMonth: string, payYear: string, isOverWriteFlag: string) {
        return this.http.get(environment.serviceApiUrl + 'csv/payslips/get-filter?payMonth=' + payMonth + '&&payYear=' + payYear + '&&isOverWriteFlag=' + isOverWriteFlag, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }


    triggerPayslipUpload(uploadPayslipHdr: UploadPayslipHdr, payMonth: string, payYear: string) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(uploadPayslipHdr);
        let url = environment.serviceApiUrl + 'csv/payslips/upload-payslip/PAYSLIP-USER-UPLOADED-DATA?payMonth=' + payMonth + '&&payYear=' + payYear;
        return this.http.post(url, body, this.commonService.jwt()).map((response: Response) => response).map((res: Response) => res.json());
    }

    movePayslipToFinal(docInfos: Array<DocInfo>, id: string) {
        let headers = new Headers({ 'Content-Type': 'application/json' });
        let options = new RequestOptions({ headers: headers });
        let body = JSON.stringify(docInfos);
        let url = environment.documentApiUrl + 'documents/doc-mgmt/upload-metadata/' + id;
        console.log(url)
        return this.http.patch(url, body, this.commonService.jwt()).map((response: Response) => response).map((res: Response) => res.json());
    }
}
