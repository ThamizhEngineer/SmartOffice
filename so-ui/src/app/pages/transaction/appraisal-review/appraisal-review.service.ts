import { Injectable } from '@angular/core';
import { Http, Response,ResponseContentType,Headers} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';

@Injectable()
export class AppraisalReivewService {
    constructor(
        private http:Http,
        private commonService:CommonService
    ) { }
    getAppraisalsReview() {
        return this.http.get(environment.serviceApiUrl + 'transaction/appraisals/reviews', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 
    updateAppraisalByAction(id,action,appraisal) {
        return this.http.patch(environment.serviceApiUrl+'transaction/appraisals/'+id+'/review/'+action,appraisal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}