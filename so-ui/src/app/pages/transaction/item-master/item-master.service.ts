import { Injectable } from '@angular/core';
import { Http, Response,ResponseContentType,Headers} from '@angular/http';
import{CommonService} from '../../../shared/common/common.service';
import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs/Rx'


@Injectable()
export class ItemMasterService {

  
    constructor(private http:Http,
        private commonService:CommonService) {
    }
    
    getItemMaster() {
        return this.http.get(environment.serviceApiUrl + 'master/item', this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 
   
    getItemMasterById(id) {
        return this.http.get(environment.serviceApiUrl + 'master/item/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    } 

    createItemMaster(item) {
        return this.http.post(environment.serviceApiUrl + 'master/item',item, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    updateItemMaster(id,item) {
        return this.http.patch(environment.serviceApiUrl + 'master/item/'+id,item, this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }

    deleteItemMaste(id){
        return this.http.delete(environment.serviceApiUrl+'master/item/'+id, this.commonService.jwt()).map((response: Response) => response);    
    }

    advanceSearch(itemCode,referenceNumber,forPurchase,hsnSacCode,itemName) {
        var url = 'master/item/advance-filters?' 

        if(itemCode!=null){
            url= url+'itemCode='+itemCode   
        } 
        if(referenceNumber!=null){
            url= url+'&&referenceNumber='+referenceNumber   
        }   
        if(forPurchase!=null){
            url= url+'&&forPurchase='+forPurchase   
        } 
        if(hsnSacCode!=null){
            url= url+'&&hsnSacCode='+hsnSacCode   
        } 
        if(itemName!=null){
            url= url+'&&itemName='+itemName   
        } 
      
        return this.http.get(environment.serviceApiUrl + url , this.commonService.jwt()).map((response: Response) => response).map(res => res.json()); 
    }
}
