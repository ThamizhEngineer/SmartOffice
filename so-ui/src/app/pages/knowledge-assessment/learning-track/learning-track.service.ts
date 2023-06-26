import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class LearningTrackService {
    constructor(private http: Http, private commonService: CommonService) {
    }
    getAllMinioVideos(){
    return this.http.get(environment.serviceApiUrl + 'photoservice/list', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    addMinioVideo(value){
        return this.http.post(environment.serviceApiUrl + 'photoservice?filePath='+value, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getLearningTracks(){
        return this.http.get(environment.serviceApiUrl + 'transaction/video-upload-service', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
    }
    addLearningTrack(learningTrack:any){
        return this.http.post(environment.serviceApiUrl+'transaction/video-upload-service',learningTrack, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
}