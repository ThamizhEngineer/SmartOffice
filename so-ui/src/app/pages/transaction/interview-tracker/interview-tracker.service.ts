import { Injectable } from '@angular/core';
import { Http, Response} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';

@Injectable()
export class InterviewTrackerService {

    constructor(
        private http:Http,
        private commonService:CommonService
    ) { }

    getInterviewTrackers() {
        return this.http.get(environment.serviceApiUrl + 'transaction/interview', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }    
    getByInterviewer() {
        return this.http.get(environment.serviceApiUrl + 'transaction/interview/interviewer-view', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }  
    getAllInterviewRounds() {
        return this.http.get(environment.serviceApiUrl + 'transaction/interview/hr-view/rounds', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getInterviewRoundsByInterviewer() {
        return this.http.get(environment.serviceApiUrl + 'transaction/interview/interviewer-view/rounds', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }  
    getInterviewDetailsForInterviewer(_id: string) {
        return this.http.get(environment.serviceApiUrl + 'transaction/interview/interviewer-view/'+_id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    InterviewTrackerId(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/interview/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    finalDecision(id,action,interviewTracker){
        return this.http.patch(environment.serviceApiUrl + 'transaction/interview/'+id+'/decide/'+action,interviewTracker, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getApplicantById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'transaction/incidents/'+ _id+'/incident-applicant',this.commonService.jwt()).map(res => res.json());
    }
    roundAction(action,interviewRoundId, interviewRound){
        return this.http.patch(environment.serviceApiUrl + 'transaction/interview/round/'+interviewRoundId+'/'+action,interviewRound, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getEmployees() {
        return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}