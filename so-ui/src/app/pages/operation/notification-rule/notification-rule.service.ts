import { Injectable } from '@angular/core';
import { Http,Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';

@Injectable()
export class NotificationRuleService {

    constructor(private http:Http,private commonService:CommonService) { }

    getAllNotificationRules(){
        return this.http.get(environment.notfnApiUrl + 'seed/event-ntfn-rules', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    getEventByFilters(eventName, entity, sendSms, sendEmail, sendInAppNotfn){
        var url = 'seed/event-ntfn-rules/fetch-by-filters?'
        if(eventName!=null){
            url= url+'eventName='+eventName
        }
        if(entity!=null){
            url= url+'&&entity='+entity   
        }
        if(sendSms!=null){
            url= url+'&&sendSms='+sendSms   
        }
        if(sendEmail!=null){
            url= url+'&&sendEmail='+sendEmail   
        }
        if(sendInAppNotfn!=null){
            url= url+'&&sendInAppNotfn='+sendInAppNotfn   
        }
        return this.http.get(environment.notfnApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateEventFromUi(list){
        return this.http.patch(environment.notfnApiUrl + 'seed/event-ntfn-rules/update-event-list',list, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

}