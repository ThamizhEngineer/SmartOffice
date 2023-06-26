import { Injectable } from '@angular/core';
import { Http,Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { environment } from './../../../../environments/environment';

@Injectable()
export class DocumentManagementService {
    documentApiUrl:string=environment.documentApiUrl;
    constructor(private http:Http,private commonService:CommonService) { }
 getDocInfos(){
     return this.http.get(this.documentApiUrl+'documents',this.commonService.jwt()).map((response:Response)=>response.json());
 }
 getDocInfoById(id:any){
    return this.http.get(this.documentApiUrl+'documents/'+id+'/doc-mgmt/docs/doc-info',this.commonService.jwt()).map((response:Response)=>response.json());
}
   
}