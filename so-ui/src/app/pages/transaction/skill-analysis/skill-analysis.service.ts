import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import 'rxjs/add/operator/map';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';


@Injectable()
export class SkillAnalysisService {

    constructor(
        private http:Http,
        private commonService:CommonService
    ) { }

    
    getSkills(key) {
        return this.http.post(environment.serviceApiUrl + 'skill-matrix/result',key, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getSkillsHdr(key) {
        return this.http.post(environment.serviceApiUrl + 'skill-matrix/result-hdr',key, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }  

    initSkill(processBody){
        return this.http.post(environment.serviceApiUrl + 'skill-matrix/init-request', processBody,this.commonService.jwt()).map((response: Response) => response); 
    }
    getskillHdr(){
        return this.http.get(environment.serviceApiUrl + 'skill-matrix/hdrs', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getSkillFromProdId(id){
        return this.http.get(environment.serviceApiUrl + 'master/materials/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getProducts(){
        return this.http.get(environment.serviceApiUrl + 'master/products?materialCategory=product', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}

