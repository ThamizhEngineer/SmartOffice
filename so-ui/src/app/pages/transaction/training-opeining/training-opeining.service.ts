import { Injectable } from '@angular/core';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class TrainingOpeningService {

  
    constructor(private http: Http,private commonService:CommonService) { }
   
    getTrainings(){
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/event-type/emp/Training', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 
    getTrainingEmployee(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/employee-list/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }
    applyForTraining(id,incidentApp){        
        return this.http.post(environment.serviceApiUrl + 'transaction/incidents/training-event/'+id,incidentApp, this.commonService.jwt()).map((response: Response) => response); 
    }  
    
}
