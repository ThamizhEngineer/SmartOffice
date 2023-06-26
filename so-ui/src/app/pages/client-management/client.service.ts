import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';
import { Partner } from '../job/vo/partner';

@Injectable()
export class ClientService {

    constructor(private http: Http, private commonService: CommonService) {
    }
getAllClient(){
    return this.http.get(environment.serviceApiUrl+'master/clients', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
getClientById(id:any){
    return this.http.get(environment.serviceApiUrl + 'master/clients/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
getAllCountries(){
    return this.http.get(environment.serviceApiUrl + 'seed/countries', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
addClient(client:Partner){

    return this.http.post(environment.serviceApiUrl+'master/clients/', client, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
updateClient(client:Partner,id:any){
    return this.http.patch(environment.serviceApiUrl+'master/clients/'+id, client, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

deleteClientById(id){
   return this.http.delete(environment.serviceApiUrl + 'master/clients/'+id, this.commonService.jwt()).map((response) => response);
}

addClientLogo(value){
    return this.http.post(environment.serviceApiUrl + 'photoservice',value, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
getClientPO(id){
    return this.http.get(environment.serviceApiUrl+'transaction/client-pos?clientId='+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
getSaleOrder(id){
    return this.http.get(environment.serviceApiUrl+'transaction/sale-orders/so-jobs?partnerId='+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

getInvoices(id){
    return this.http.get(environment.serviceApiUrl+'transaction/invoice/by-client/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

getPayments(id){
    return this.http.get(environment.serviceApiUrl+'transaction/invoice-payment/by-client/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

getUsers(id){
    return this.http.get(environment.authUrl+'auth/users/auth-client?partnerID='+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

getClientEmployeeById(id){
    return this.http.get(environment.serviceApiUrl+'master/clients/client-emp/'+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}



advanceSearch(clientCode,companyName,countryName) {
    var url = 'master/clients/advance-filters?' 

    if(clientCode!=null){
        url= url+'clientCode='+clientCode   
    } 
    if(companyName!=null){
        url= url+'&&companyName='+companyName   
    }   
    if(countryName!=null){
        url= url+'&&countryName='+countryName   
    } 
    return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
getCurrency(){
    return this.http.get(environment.serviceApiUrl+'seed/currency',this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}

getCompanyTypes(){
    return this.http.get(environment.serviceApiUrl+'seed/company-type',this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

}