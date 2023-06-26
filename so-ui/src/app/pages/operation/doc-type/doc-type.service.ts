import { Injectable } from '@angular/core';
import { Http,Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from './../../../../environments/environment';

@Injectable()
export class DocTypeService {
    constructor(private http:Http,private commonService:CommonService) { }
    
    getDoc(){
        return this.http.get(environment.serviceApiUrl + 'dm/document_types', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    deleteDoc(id){
        return this.http.delete(environment.serviceApiUrl +'dm/document_types/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    postDoc(documentType){
        return this.http.post(environment.serviceApiUrl +'dm/document_types',documentType, this.commonService.jwt()).map((response: Response) => response); 
    }
    patchDoc(c){
        console.log(c)
        var url = environment.serviceApiUrl +'dm/document_types/'+c.id;
        console.log(url)
        return this.http.patch(url,c, this.commonService.jwt()).map((response: Response) => response); 

    }
} 