import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions} from '@angular/http'; 
import 'rxjs/add/operator/map';
import { environment } from '../../../environments/environment';
import { CommonService } from '../../shared/common/common.service';

@Injectable()
export class MapLocationService {

    constructor(private http:Http,private commonService: CommonService){
    }

    getAllCountry() {
        return this.http.get(environment.serviceApiUrl + 'seed/countries', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
 
    searchKnowLocation(locName,address,locType,country){
        return this.http.get(environment.serviceApiUrl + 'map-locations?locName='+locName+'&address='+address+'&locType='+locType+'&country='+country, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    searchGeoposition(address:string,countryCode:string,locName:string){
        return this.http.get(environment.serviceApiUrl + 'map-locations/search-geoposition?address='+address+'&country-code='+countryCode+'&limit=1&loc-name='+locName+'&status=COMPLETED', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
        
    }

    getByAddressAndCountryCodeAndStatusAndLocName(address:string,countryCode:string,locName:string){
        return this.http.get(environment.serviceApiUrl + 'transaction/map-location-parameter/search?address='+address+'&country-code='+countryCode+'&limit=1&loc-name='+locName+'&status=COMPLETED', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
        
    }

    addMapLocation(mapLocations?: any) {
        return this.http.post(environment.serviceApiUrl+'map-locations', mapLocations, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }

    getMapLocation() {
        return this.http.get(environment.serviceApiUrl + 'map-locations/all' , this.commonService.jwt()).map((response: Response) => response).map(res => res.json());
    }
}
