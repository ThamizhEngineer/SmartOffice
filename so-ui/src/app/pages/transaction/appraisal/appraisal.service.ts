import { Injectable } from '@angular/core';
import { Http, Response,ResponseContentType,Headers} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs/Rx'
@Injectable()
export class AppraisalService {

    constructor(
        private http:Http,
        private commonService:CommonService
    ){}

    getAppraisals() {
        return this.http.get(environment.serviceApiUrl + 'transaction/appraisals', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 
    getAppraisalById(id) {
        return this.http.get(environment.serviceApiUrl + 'transaction/appraisals/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 
    createAppraisal(appraisal) {
        return this.http.post(environment.serviceApiUrl + 'transaction/appraisals/initiate',appraisal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateAppraisal(id,appraisal) {
        return this.http.patch(environment.serviceApiUrl + 'transaction/appraisals/'+id+'/update',appraisal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    updateAppraisalByAction(id,action,appraisal) {
        return this.http.patch(environment.serviceApiUrl + 'transaction/appraisals/'+id+'/'+action,appraisal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getAppraisalByFyId(fyId){
        return this.http.get(environment.serviceApiUrl + 'transaction/appraisals/'+fyId+'/hdr', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    getAppraisalByFyIdAndEmpId(fyId,empId){
        return this.http.get(environment.serviceApiUrl + 'transaction/appraisals/'+fyId+'/'+empId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getDesignations(){
        return this.http.get(environment.serviceApiUrl + 'seed/designations', this.commonService.jwt()).map((response: Response) => response.json());
       }
    getEmployees(){
        return this.http.get(environment.serviceApiUrl+'master/employees', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getFyYears(){
        return this.http.get(environment.serviceApiUrl + 'seed/fiscal-years', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getMetrics(){
        return this.http.get(environment.serviceApiUrl + 'master/metrics', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());         
    }
    getOperator() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=OPERATOR', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getScoreCode(){
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=APPRAISAL_SCORE_CODE', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    getOrgGoalByEmpId(yearId){
        let url = environment.serviceApiUrl+'transaction/org-services/emp-by-fiscal-year?fyYearId='+yearId;
           return this.http.get(url, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());    
       }
    updateAppraisalAchiv(action,id,appraisal){
        return this.http.patch(environment.serviceApiUrl+'transaction/appraisals/'+id+'/review/'+action,appraisal, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getAppraisalByFisYearId(fiscalYearId){
        return this.http.get(environment.serviceApiUrl +'transaction/appraisal-setting/'+fiscalYearId+'/fiscal-year-id',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getOffices(){
        return this.http.get(environment.serviceApiUrl + 'master/offices', this.commonService.jwt()).map((response: Response) => response.json());    
    }

    getSkillFromProdId(id){
        return this.http.get(environment.serviceApiUrl + 'master/materials/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }


    hr1GetEmployee(searchEmpName,searchDesigName,searchEmailId,searchContactMobileNo,
        serachOfficeName,searchEmpCode,searchEmpStatus,serachId,n1EmpId,n2EmpId){             

           var andSplit= "&";
        var fromFilter = "empName="+searchEmpName+andSplit+"desigName="+searchDesigName+andSplit+"emailId="
        +searchEmailId+andSplit+"contactMobileNo="+searchContactMobileNo+andSplit+"officeName="+serachOfficeName+andSplit+"empCode="+searchEmpCode+andSplit+
        "empStatus="+searchEmpStatus+andSplit+"id="+serachId+andSplit+"n1EmpId="+n1EmpId+andSplit+"n2EmpId="+n2EmpId;
        console.log(fromFilter)
        return this.http.get(environment.serviceApiUrl+'master/emp-profiles/hr1-profile-validate?'+fromFilter, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateAppraisalSetting(id,appSetBody){
        return this.http.patch(environment.serviceApiUrl+'transaction/appraisal-setting/'+id+'/update',appSetBody,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    createAppraisalSetting(appSetBody){
        return this.http.post(environment.serviceApiUrl+'transaction/appraisal-setting/create',appSetBody,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    
    getProducts(){
        return this.http.get(environment.serviceApiUrl + 'master/products?materialCategory=product', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
    getAbilities() {
        return this.http.get(environment.serviceApiUrl + 'master/employeedetails', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

            // demo


   
    getTimeline() {
        let url = 'assets/data/pedp-timeline.json';
        return this.http.get(url).map(res => res.json());
    }    
    
}
