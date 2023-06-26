import { Injectable } from '@angular/core';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { WebSiteApplicant } from '../../vo/web-site-applicant';
import 'rxjs/add/operator/map';

@Injectable()
export class WebApplicationsService {

    constructor(
        private http: Http,
        private commonService: CommonService
    ) { }

    getJobrequests() {
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-requests', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateWebApplication(webApplicants) {
        return this.http.patch(environment.serviceApiUrl + 'transaction/web-site-applicants/bulkupdate', webApplicants, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getWebApplications(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/web-site-applicants/groupByJrId/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}