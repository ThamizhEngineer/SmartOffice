import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response, URLSearchParams, ResponseContentType} from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { Observable } from 'rxjs/Rx'
import { DocInfo } from '../vo/doc-info';
import { saveAs } from 'file-saver';



import { environment } from '../../../environments/environment';

@Injectable()
export class CommonService {
    
	cachedCodesData:any;
	cachedSubstations:any;
  cachedFeeders:any;
  cachedEdcs:any;
  cachedMenuItems:any;
  
    constructor(private http:Http){
      //this.http._defaultOptions.headers.set('Authorization', 'token');

        
    }

  displayMonth(month: string){
    var months = [ "January", "February", "March","April","May","June","July","August","September","October", "November","December"]
    return months[parseInt(month)-1];
  }

  fetchMonths(){
    return [
            {"value":"01", "name":"January"},
            {"value":"02", "name":"February"},
            {"value":"03", "name":"March"},
            {"value":"04", "name":"April"},
            {"value":"05", "name":"May"},
            {"value":"06", "name":"June"},
            {"value":"07", "name":"July"},
            {"value":"08", "name":"August"},
            {"value":"09", "name":"September"},
            {"value":"10", "name":"October"},
            {"value":"11", "name":"November"},
            {"value":"12", "name":"December"}
          ];
  }

  jwt(paramArr?: any) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currToken = this.getCurrentToken(currentUser);
        let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json','authorization': currToken});
		if(paramArr){
			let myParams = new URLSearchParams();
			paramArr.forEach( x => myParams.append(x.key, x.value) );
			return new RequestOptions({ headers: headers, params: myParams });
		}
        else return new RequestOptions({ headers: headers });
  }

  getCurrentToken(currentUser): string {
    if (currentUser && currentUser.appToken) 
      return currentUser.appToken;
    else
        return '';
  }
   
  uploadDocument(file: File,type:any) {
 
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currToken = this.getCurrentToken(currentUser);
    let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data','authorization':currToken});
    let myParams = new URLSearchParams();
    myParams.append("content-type","application/json");  
    let options = new RequestOptions({ headers: headers, params:myParams });
    let documentUrl=environment.documentApiUrl + "dm/upload/"+type;
    return this.http.post(documentUrl, formData, options).map((response: Response) => response)
      .map(res => res.json());
}

getDocInfoByDocId(docId) {
  let url =environment.documentApiUrl+'dm/docinfo/'+docId;
  return this.http.get(url, this.jwt()).map((response: Response) => response).map(res => res.json()); 
}

downloadDocument(docId, docFileName){
  let currentUser = JSON.parse(localStorage.getItem('currentUser'));
  var currToken = this.getCurrentToken(currentUser);
  let headers = new Headers({'authorization': currToken});	
  let options = {responseType: ResponseContentType.Blob ,headers:headers};
  let url =environment.documentApiUrl+'dm/'+docId;
  this.http.get(url, options).subscribe(docRes=>{
    if(docFileName==null || docFileName=="" || docFileName==undefined){
      this.getDocInfoByDocId(docId).subscribe(res =>{
        (res as DocInfo[]).forEach(docInfo => {
          console.log(docInfo.docNameFromUser);
          docFileName = docInfo.docNameFromUser;
          console.log(docFileName);
        });
        this._saveFileInSystem(docRes.blob(), docFileName);
      });
    }
    else{
      this._saveFileInSystem(docRes.blob(), docFileName);
    }
  });
}

_saveFileInSystem(docBlob, docFileName){
  saveAs(docBlob, docFileName);
}


}

