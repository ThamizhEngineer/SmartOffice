import { Injectable } from '@angular/core';
import { Headers, Http, RequestOptions, Response, URLSearchParams, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../environments/environment';
import { DocInfo } from '../../pages/vo/doc-info';
import { saveAs } from 'file-saver';

@Injectable()
export class CommonService {

  cachedCodesData: any;
  cachedSubstations: any;
  cachedFeeders: any;
  cachedEdcs: any;
  cachedMenuItems: any;

  constructor(private http: Http) { }

  // Jwt related ---------------------------------------------------------------------------------------------------------

  jwt(paramArr?: any) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currToken = this.getCurrentToken(currentUser);
    let headers = new Headers({ 'Accept': 'application/json', 'Content-Type': 'application/json', 'authorization': currToken });
    if (paramArr) {
      let myParams = new URLSearchParams();
      paramArr.forEach(x => myParams.append(x.key, x.value));
      return new RequestOptions({ headers: headers, params: myParams });
    }
    else return new RequestOptions({ headers: headers });
  }

  jwtForUpload() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currToken = this.getCurrentToken(currentUser);
    let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data', 'authorization': currToken });
    let myParams = new URLSearchParams();
    myParams.append("content-type", "application/json");
    return new RequestOptions({ headers: headers, params: myParams });
  }

  jwtDownoloadResBlob() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currToken = this.getCurrentToken(currentUser);
    let headers = new Headers({ 'authorization': currToken });
    return { responseType: ResponseContentType.Blob, headers: headers };
  }

  jwtDownloadResArrayBuffer() {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    var currToken = this.getCurrentToken(currentUser);
    let headers = new Headers({ 'authorization': currToken });
    return { responseType: ResponseContentType.ArrayBuffer, headers: headers };
  }

  getCurrentToken(currentUser): string {
    if (currentUser && currentUser.appToken) 
      return currentUser.appToken;
    else
        return '';
  }

  getAppToken(): string {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    if (currentUser && currentUser.appToken) 
      return currentUser.appToken;
    else
        return '';
  }
  

  // Upload document (Universal)---------------------------------------------------------------------------------------------

  uploadDocument(file: File, type: any) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "dm/upload/" + type;
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  UpdateDocument(file: File, type: any, docId: any) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "dm/update-upload/" + docId + "/" + type;
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  uploadQues(file: File, type: any) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "csv/question-bank/upload/" + type;
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  uploadApplicant(file: File, type: any, incidentId: any) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "csv/incident-applicants/upload/" + type + "/" + incidentId;
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  uploadInvoice(file: File) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "csv/invoice/upload/INVOICE-CSV-UPLOAD";
    console.log(documentUrl)
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  uploadAttendance(file: File) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "csv/attendance/upload/ATTENDANCE-CSV-UPLOAD";
    console.log(documentUrl)
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  uploadEmployee(file: File) {
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let documentUrl = environment.documentApiUrl + "csv/employee/upload/EMPLOYEE-CSV-UPLOAD";
    console.log(documentUrl)
    return this.http.post(documentUrl, formData, this.jwtForUpload()).map((response: Response) => response)
      .map(res => res.json());
  }

  // Downloader  -----------------------------------------------------

  fileDownloader(fileId, fileName) { //New
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    let currentToken = this.getCurrentToken(currentUser);
    let headers = new Headers({ 'authorization': currentToken });
    let options = { responseType: ResponseContentType.Blob, headers: headers };
    let url = environment.documentApiUrl + 'fm/' + fileId;
    this.http.get(url, options).subscribe(res => {
      this._saveFileInSystem(res.blob(), fileName);
    });
  }

  downloadDocument(docId, docFileName) {
    let url = environment.documentApiUrl + 'dm/' + docId;
    this.http.get(url, this.jwtDownoloadResBlob()).subscribe(docRes => {
      if (docFileName == null || docFileName == "" || docFileName == undefined) {
        this.getDocInfoByDocId(docId).subscribe(res => {
          (res as DocInfo[]).forEach(docInfo => {
            console.log(docInfo.docNameFromUser);
            docFileName = docInfo.docNameFromUser;
            console.log(docFileName);
          });
          this._saveFileInSystem(docRes.blob(), docFileName);
        });
      }
      else {
        this._saveFileInSystem(docRes.blob(), docFileName);
      }
    });

  }

  _saveFileInSystem(docBlob, docFileName) {
    saveAs(docBlob, docFileName);
  }

  // --------------------------------------------------------------------------------

  checkInDocuments(docInfoList: DocInfo[]) {
    let url = environment.documentApiUrl + 'dm/checkin/';
    return this.http.post(url, docInfoList, this.jwt()).map((response: Response) => response).map(res => res.json());
  }

  getDocInfoByDocId(docId) {
    let url = environment.documentApiUrl + 'dm/docinfo/' + docId;
    return this.http.get(url, this.jwt()).map((response: Response) => response).map(res => res.json());
  }

  getDocUrl(docId){
    return environment.documentApiUrl + 'dm/' + docId+"?authorization="+this.getAppToken();
  }

  uploadHolidays(file: File, type: any, officeId: any, yearId: any) { // TODO: Custom should be TESTED later
    let formData: FormData = new FormData();
    formData.append('uploadingFiles', file, file.name);
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data', 'authorization': currentUser.appToken });
    let myParams = new URLSearchParams();
    // myParams.append("content-type", "application/json");
    let options = new RequestOptions({ headers: headers, params: myParams });
    let documentUrl = environment.documentApiUrl + "csv/office-calendars/upload/" + type + "/" + officeId + "/" + yearId;
    return this.http.post(documentUrl, formData, options).map((response: Response) => response)
      .map(res => res.json());
  }

  // ----Extras ------------------------------------------------------------------

  getClientIpAddress() {
    if (localStorage.getItem('clientIp')) {
      return localStorage.getItem('clientIp');
    }
    else
      return "";
  }

  displayMonth(month: string) {
    var months = ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"]
    return months[parseInt(month) - 1];
  }

  fetchMonths() {
    return [
      { "value": "01", "name": "January" },
      { "value": "02", "name": "February" },
      { "value": "03", "name": "March" },
      { "value": "04", "name": "April" },
      { "value": "05", "name": "May" },
      { "value": "06", "name": "June" },
      { "value": "07", "name": "July" },
      { "value": "08", "name": "August" },
      { "value": "09", "name": "September" },
      { "value": "10", "name": "October" },
      { "value": "11", "name": "November" },
      { "value": "12", "name": "December" }
    ];
  }

  public isValidName(e) {
    let input;
    if (e.which == 32) {
      return true;
    }
    if (e.which == 45) {
      return true;
    }
    if (e.which == 46) {
      return true;
    }
    if (e.which == 13) {
      return true;
    }
    if (e.which == 13) {
      return true;
    }
    if (e.which == 38) {
      return true;
    }
    if (e.which == 40) {
      return true;
    }
    if (e.which == 41) {
      return true;
    }
    if (e.which == 123) {
      return true;
    }
    if (e.which == 125) {
      return true;
    }
    if (e.which == 91) {
      return true;
    }
    if (e.which == 93) {
      return true;
    }
    if (e.which == 47) {
      return true;
    }
    if (e.which == 65) {
      return true;
    }
    input = String.fromCharCode(e.which);
    console.log(input)
    return /\w/g.test(input);
  }

  // returns true if checkDate is between fromDate and toDate
  comparingDates(fromdate, toDate, checkDate) {
    var fDate, tDate, cDate;
    fDate = Date.parse(fromdate);
    tDate = Date.parse(toDate);
    cDate = Date.parse(checkDate);
    if ((cDate <= tDate && cDate >= fDate)) {
      return true;
    }
    return false;
  }
}
