import { Injectable } from '@angular/core';
import { Http, Response,ResponseContentType,Headers} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs/Rx'

@Injectable()
export class IncidentService {

    constructor(
        private http:Http,
        private commonService:CommonService
    ) { }

    getJobRequests() {
        return this.http.get(environment.serviceApiUrl + 'transaction/vacancy-requests', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }  
    getHrL1(){
        return this.http.get(environment.serviceApiUrl + 'seed/user-groups?isHrL1=Y', this.commonService.jwt()).map((response: Response) => response.json());  
    }

    getEmployees() {
        return this.http.get(environment.serviceApiUrl+'master/employees/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getApplicants() {
        return this.http.get(environment.serviceApiUrl + 'transaction/web-site-applicants/groupByJrId/', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }  
    updateApplicant(id,incidentApplicant){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incidents/'+id+'/update-incident-applicant',incidentApplicant, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    sendNotificationToApplicant(id,incident){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incidents/'+id+'/training-notification',incident, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    addincident(incident){
        return this.http.post(environment.serviceApiUrl + 'transaction/incidents/create',incident, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 

    }
    updateinc(id,incident){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incidents/'+id+'/update',incident, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    deleteInc(id){
        return this.http.delete(environment.serviceApiUrl + 'transaction/incidents/'+id+'/delete', this.commonService.jwt()).map((response: Response) => response); 
    }
    addOrUpdateincident(incident){
        return this.http.post(environment.serviceApiUrl + 'transaction/incidents/add-applicant',incident, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 

    }
    getApplicantById(id:any){
      
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/'+id+'/incident-applicant', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getIncidentById(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateIncident(id,action,incident){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incidents/'+id+'/'+action,incident, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateEmployeeConversion(id,list){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incidents/'+id+'/employee-conversion',list, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    convertToEmployee(id,appids){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incidents/'+id+'/on-board-employee',appids, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getIncidents() {
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getIncidentForDir() {
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/dir-view', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    triggerRegenerate(id){
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/regenerate/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getWebAppByVCId(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/web-site-applicants/groupByJrId/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    fetchTestReportByIncidentId(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/incident/testreports/fetch-by-incident-id/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    finalDecision(id,participantId,decision,dummy){
        return this.http.patch(environment.serviceApiUrl + 'transaction/incident/testreports/finalDecision/'+id+'/'+participantId+'/'+decision,dummy, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    fetchTestHistoryByTestId(incdientId,participantId,ttId){
        return this.http.get(environment.serviceApiUrl + 'transaction/incident/testreports/fetchHistoryByIncidentId/'+incdientId+'/'+participantId+'/'+ttId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
    }

    regenerateIncidentTest(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/regenerate/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }


    deleteTestAndTempalte(type,id){
        return this.http.get(environment.serviceApiUrl + 'transaction/incidents/delete/'+type+'/'+id, this.commonService.jwt()).map((response: Response) => response); 
    }
    moveToUnassigned(id,applicantId){
        return this.http.delete(environment.serviceApiUrl + 'transaction/incidents/delete-test/'+id+'/'+applicantId, this.commonService.jwt()).map((response: Response) => response); 
    }

    fetchBewtweenScores(startScore,endScore,result,incidentId,testStatus,startMark,endMark){

        var url = 'transaction/incident/testreports/advanceFilters?startScore='+startScore       
        if(endScore!=null){
            url= url+'&&endScore='+endScore   
        }
        if(result!=null){
            url= url+'&&testResult='+result   
        }
        if(incidentId!=null){
            url= url+'&&incidentId='+incidentId   
        }
        if(testStatus!=null){
            url= url+'&&testStatus='+testStatus   
        }
        if(startMark!=null){
            url= url+'&&startMark='+startMark   
        }
         
        if(endMark!=null){
            url= url+'&&endMark='+endMark   
        }
         

        return this.http.get(environment.serviceApiUrl + url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    //Advance Search
    advanceSearch(institute,course,degreeName,passoutYear,marks,historyOfArrears,isEligibleForTest) {
        var url = 'transaction/incidents/advance-search?'
        if(institute!=null){
            url= url+'&&institute='+institute
        }
        if(course!=null){
            url= url+'&&course='+course   
        }
        if(degreeName!=null){
            url= url+'&&degreeName='+degreeName   
        }
        if(passoutYear!=null){
            url= url+'&&passoutYear='+passoutYear   
        }
        if(marks!=null){
            url= url+'&&marks='+marks   
        }
        if(historyOfArrears!=null){
            url= url+'&&historyOfArrears='+historyOfArrears   
        }
        if(isEligibleForTest!=null){
            url= url+'&&isEligibleForTest='+isEligibleForTest   
        }        
        return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    //employee Service
    
    getAllEmployee() {
        return this.http.get(environment.serviceApiUrl + 'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getEmployeeById(id){
        return this.http.get(environment.serviceApiUrl + 'master/employees/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());   
    }

    //Test-template Service

    getTestTemplates() {
        return this.http.get(environment.serviceApiUrl + 'master/testtemplate', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }


    // -------------------------------------------------------

    downloadFile(data, filename='data',columns) {
        let csvData = this.ConvertToCSV(data,columns);
        console.log(csvData)
        let blob = new Blob(['\ufeff' + csvData], { type: 'text/csv;charset=utf-8;' });
        let dwldLink = document.createElement("a");
        let url = URL.createObjectURL(blob);
        let isSafariBrowser = navigator.userAgent.indexOf('Safari') != -1 && navigator.userAgent.indexOf('Chrome') == -1;
        if (isSafariBrowser) {  //if Safari open in new window to save file with random filename.
            dwldLink.setAttribute("target", "_blank");
        }
        dwldLink.setAttribute("href", url);
        dwldLink.setAttribute("download", filename + ".csv");
        dwldLink.style.visibility = "hidden";
        document.body.appendChild(dwldLink);
        dwldLink.click();
        document.body.removeChild(dwldLink);
    }

    ConvertToCSV(objArray, headerList) {
         let array = typeof objArray != 'object' ? JSON.parse(objArray) : objArray;
         let str = '';
         let row = 'S.No,';

         for (let index in headerList) {
             row += headerList[index] + ',';
         }
         row = row.slice(0, -1);
         str += row + '\r\n';
         for (let i = 0; i < array.length; i++) {
             let line = (i+1)+'';
             for (let index in headerList) {
                let head = headerList[index];

                 line += ',' + array[i][head];
             }
             str += line + '\r\n';
         }
         return str;
     }

     //generate certificate:
     generateCertificate(incidentApplicantId){
           return this.http.get(environment.documentApiUrl + 'document/prints/certificates/'+incidentApplicantId+'/generate-certificate', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
     }

     download(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'document/prints/certificates/'+docId+'/download-certificate';
          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
     }

     downloadResume(docId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.documentApiUrl+'dm/'+docId;
          return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
     }

     exportApplicant(incidentId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.serviceApiUrl+'transaction/incidents/export-app/'+incidentId+'/applicant-filtered.xls';
        return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
     }
     

     exportResult(incidentId){

        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.serviceApiUrl+'transaction/incidents/export-app/'+incidentId+'/overall-test-status.xls';
        return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
     }

     exportTest(incidentId){
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        let headers = new Headers({'authorization': currentUser.appToken});	
        let options = {responseType: ResponseContentType.ArrayBuffer ,headers:headers};
        let url =environment.serviceApiUrl+'transaction/incidents/export-app/'+incidentId+'/export-test-result.xls';
        return this.http.get(url,options).catch((err: Response) => Observable.throw(err.json()));
     }

     search(finalTestStatus,finalInterviewStatus,finalDecision){
        var url = 'transaction/incidents/advance?' 

        if(finalTestStatus!=null){
            url= url+'finalTestStatus='+finalTestStatus   
        } 
        if(finalInterviewStatus!=null){
            url= url+'&&finalInterviewStatus='+finalInterviewStatus   
        }   
        if(finalDecision!=null){
            url= url+'&&finalDecision='+finalDecision   
        }  
      
        return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

     }

