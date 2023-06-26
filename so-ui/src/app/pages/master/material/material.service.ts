import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions, ResponseContentType } from '@angular/http';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import { environment } from '../../../../environments/environment';
import { CommonService } from '../../../shared/common/common.service';

@Injectable()
export class MaterialsService {

    baseUrl: string;
    cachedData: any;

    constructor(private http: Http,
        private commonService: CommonService) {
        this.baseUrl = environment.apiUrl;
    }

    getMaterials() {  
        return this.http.get(environment.serviceApiUrl + 'master/materials', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    getMaterialsById(id) {  
        return this.http.get(environment.serviceApiUrl + 'master/materials/'+id, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    createMaterials(material) {  
        return this.http.post(environment.serviceApiUrl + 'master/materials',material, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }

    updateMaterials(id,material) {  
        return this.http.patch(environment.serviceApiUrl + 'master/materials/'+id,material, this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
    deleteMaterials(id) {  
        return this.http.delete(environment.serviceApiUrl + 'master/materials/'+id, this.commonService.jwt()).map((response: Response) => response);              
    }

    getServices(){
        return this.http.get(environment.serviceApiUrl + 'master/abilities', this.commonService.jwt()).map((response: Response) => response).map(res => res.json());              
    }
}
