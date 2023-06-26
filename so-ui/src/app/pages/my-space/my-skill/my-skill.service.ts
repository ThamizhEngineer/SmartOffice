import { Injectable } from "@angular/core";
import { Http, Response } from "@angular/http";
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class MySkillService {

	constructor(private http: Http,private commonService: CommonService) {
    }

   
    getEmployeeById(_id: string) {
        return this.http.get(environment.serviceApiUrl+'master/employees/' + _id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getSkill() {
        return this.http.get(environment.serviceApiUrl+'seed/configs?configDtlCode=SKILL-TYPE-CODE', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getProducts(){
        return this.http.get(environment.serviceApiUrl + 'master/products?materialCategory=product', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getJobs(type?: string) {
        return this.http.get(environment.serviceApiUrl + 'master/' + type, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getSkillFromProdId(id){
        return this.http.get(environment.serviceApiUrl + 'master/materials/' + id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    updateEmployeeSkill(employee:any,id:any) {
   
        return this.http.patch(environment.serviceApiUrl+'master/emp-profiles/'+id+'/skill-update', employee, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
    EmployeeUpdate(id:any,action:any,employee:any){
        return this.http.patch(environment.serviceApiUrl+'master/emp-profiles/'+id+'/'+action, employee, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    

}