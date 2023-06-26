import { Injectable } from '@angular/core';

import 'rxjs/add/operator/map';
import { Observable } from 'rxjs/Rx'
import { environment } from '../../../../environments/environment';
import { Http, Response, Headers, RequestOptions,ResponseContentType } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { FunctionGroup } from './vo/function-group';

@Injectable()
export class FunctionGroupService {

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;
	
    constructor(private http: Http, private commonService: CommonService) {

    }

    getFunctionGroup() {
        return this.http.get(this.baseUrl + 'master/function-group', this.commonService.jwt()).map((response: Response) => response.json());
    }

    getFunctionGroupId(id: string) {
        return this.http.get(this.baseUrl + 'master/function-group/' + id, this.commonService.jwt()).map((response: Response) => response.json());
    }

    getFunctionGroupType(type) {
        return this.http.get(this.baseUrl + 'master/function-group/deliver-type/'+type, this.commonService.jwt()).map((response: Response) => response.json());
    }

    addFunctionGroup(functionGroup: FunctionGroup) {
        return this.http.post(this.baseUrl + 'master/function-group', functionGroup, this.commonService.jwt()).map((response: Response) => response.json());
    }

    updateFunctionGroup(id: string, functionGroup: FunctionGroup) {
        return this.http.patch(this.baseUrl + 'master/function-group/' + id, functionGroup, this.commonService.jwt()).map((response: Response) => response.json());
    }

    deleteFunctionGroup(id: string) {
        return this.http.delete(this.baseUrl + 'master/function-group/' + id, this.commonService.jwt()).map((response) => response);
    }
}