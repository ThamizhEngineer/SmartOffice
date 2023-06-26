import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';
import { Partner } from '../job/vo/partner';

@Injectable()
export class VendorService {

    constructor(private http: Http, private commonService: CommonService) {
    }
getAllVendors(){
    return this.http.get(environment.serviceApiUrl + 'master/vendor', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
getVendorById(id:any){
    return this.http.get(environment.serviceApiUrl + 'master/vendor/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
getAllCountries(){
    return this.http.get(environment.serviceApiUrl + 'seed/countries', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
addVendor(vendor:Partner){

    return this.http.post(environment.serviceApiUrl+'master/vendor/', vendor, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}
updateVendor(vendor:Partner,id:any){
    return this.http.patch(environment.serviceApiUrl+'master/vendor/'+id, vendor, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

advanceSearch(vendorCode,vendorName,countryName) {
    var url = 'master/vendor/advance-filters?' 

    if(vendorCode!=null){
        url= url+'vendorCode='+vendorCode   
    } 
    if(vendorName!=null){
        url= url+'&&vendorName='+vendorName   
    }   
    if(countryName!=null){
        url= url+'&&countryName='+countryName   
    } 
    return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
}

getPayable(venodrId,vendorName){
    return this.http.get(environment.reportApiUrl+'reports/payable/summary?venodrId='+venodrId+"&&vendorName="+vendorName, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
}

getPurchaseOrderByVendor(venodrId){
    return this.http.get(environment.serviceApiUrl+'transaction/purchase-orders/summary?vendorId='+venodrId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
}
getPayoutByVendor(venodrId){
    return this.http.get(environment.serviceApiUrl+'transaction/purchase-orders/find-payout/'+venodrId, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());  
}
getVendor(id){
    return this.http.get(environment.authUrl+'auth/users/auth-vendor?partnerID='+id,this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
}
}