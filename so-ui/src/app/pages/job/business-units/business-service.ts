import { Injectable, EventEmitter } from '@angular/core';
import 'rxjs/add/operator/map';
import { environment } from '../../../../environments/environment';
import { Http, Response } from '@angular/http';
import { CommonService } from '../../../shared/common/common.service';
import { BusinessUnit } from './vo/business';
import { Division } from './vo/business';
import { DivisionGoods } from './vo/division-goods';
import { DivisionService } from './vo/division-service';

@Injectable()
export class BusinessService {

    currentMessageEvent = new EventEmitter<any>();

    baseUrl: string = environment.serviceApiUrl;
    xapikey: string = environment.xapikey;
    clientId: string = environment.clientId;

    constructor(private http: Http, private commonService: CommonService) { }

    changeMessage(payload) {
        console.log(payload)
        this.currentMessageEvent.emit(payload)
    }

    getBusiness() {
        return this.http.get(this.baseUrl + 'master/business-units', this.commonService.jwt()).map((response: Response) => response.json());
    }

    getBusinessId(id: string) {
        return this.http.get(this.baseUrl + 'master/business-units/' + id, this.commonService.jwt()).map((response: Response) => response.json());
    }

    addBusiness(businessUnit: BusinessUnit) {
        return this.http.post(this.baseUrl + 'master/business-units', businessUnit, this.commonService.jwt()).map((response: Response) => response.json());
    }

    updateBusiness(id: string, businessUnit: BusinessUnit) {
        return this.http.patch(this.baseUrl + 'master/business-units/' + id, businessUnit, this.commonService.jwt()).map((response: Response) => response.json());
    }

    deleteBusiness(id: string) {
        return this.http.delete(this.baseUrl + 'master/business-units/' + id, this.commonService.jwt()).map((response) => response);
    }

    getAllDivisions() {
        return this.http.get(this.baseUrl + '/all-divisions/', this.commonService.jwt()).map((response: Response) => response.json());
    }

    updateLines(id: string, businessUnit: BusinessUnit) {
        return this.http.patch(this.baseUrl + 'master/business-units/' + id, businessUnit + '/lines', this.commonService.jwt()).map((response: Response) => response.json());
    }

    deleteLines(id: string) {
        return this.http.delete(this.baseUrl + 'master/business-units/' + id + '/delete/lines', this.commonService.jwt()).map((response: Response) => response.json());
    }

    getDivById(id: string) {
        return this.http.get(this.baseUrl + 'master/business-units/' + id + '/div', this.commonService.jwt()).map((response: Response) => response.json());
    }

    postDiv(division: Division) {
        return this.http.post(this.baseUrl + 'master/business-units/divisionGood', division + '/division', this.commonService.jwt()).map((response: Response) => response.json());
    }

    updateDivision(id: string, division: Division) {
        return this.http.patch(this.baseUrl + 'master/business-units/' + id, division + '/patch-division', this.commonService.jwt()).map((response: Response) => response.json());
    }

    deleteDivision(id: string) {
        return this.http.delete(this.baseUrl + 'master/business-units/' + id + '/delete-division', this.commonService.jwt()).map((response: Response) => response);
    }

    getDivisionGoodsById(id: string) {
        return this.http.get(this.baseUrl + 'master/business-units/' + id + '/getall-divisionGood', this.commonService.jwt()).map((response: Response) => response.json());
    }

    postDivisionGoods(goods: DivisionGoods) {
        return this.http.post(this.baseUrl + 'master/business-units/divisionGood', goods, this.commonService.jwt()).map((response: Response) => response.json());
    }

    updateDivisionGoods(id: string, goods: DivisionGoods) {
        return this.http.patch(this.baseUrl + 'master/business-units/' + id, goods + '/pacth-divisionGoods', this.commonService.jwt()).map((response: Response) => response.json());
    }

    deleteDivisionGoods(id: string) {
        return this.http.delete(this.baseUrl + 'master/business-units/' + id + '/delete-divisionGoods', this.commonService.jwt()).map((response: Response) => response);
    }

    getDivisionService() {
        return this.http.get(this.baseUrl + 'master/business-units/get-DivisionService', this.commonService.jwt()).map((response: Response) => response.json());
    }

    getDivisionServiceById(id: string) {
        return this.http.get(this.baseUrl + 'master/business-units/' + id + '/getall-DivisionService', this.commonService.jwt()).map((response: Response) => response.json());
    }

    postDivisionService(sNames: DivisionService) {
        return this.http.post(this.baseUrl + 'master/business-units/divisionService', sNames, this.commonService.jwt()).map((response: Response) => response.json());
    }

    updateDivisionService(id: string, sNames: DivisionService) {
        return this.http.patch(this.baseUrl + 'master/business-units/' + id, sNames + '/patch-divisionService', this.commonService.jwt()).map((response: Response) => response.json());
    }

    deleteDivisionService(id: string) {
        return this.http.delete(this.baseUrl + 'master/business-units/' + id + '/delete-divisionService', this.commonService.jwt()).map((response: Response) => response);
    }

    getAllProductMaster() {
        return this.http.get(this.baseUrl + 'master/products/getAll', this.commonService.jwt()).map((response: Response) => response.json());
    }

    getAllServiceMaster() {
        return this.http.get(this.baseUrl + 'master/abilities', this.commonService.jwt()).map((response: Response) => response.json());
    }
    getGoodsAndService(type){
        return this.http.get(this.baseUrl + 'master/materials?materialCategory='+type, this.commonService.jwt()).map((response: Response) => response.json());
    }

    getServices(){
        return this.http.get(this.baseUrl + 'master/abilities', this.commonService.jwt()).map((response: Response) => response.json());
    }
}