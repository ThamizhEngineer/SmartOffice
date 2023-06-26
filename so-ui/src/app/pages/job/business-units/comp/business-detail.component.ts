import { Component, OnInit, Input } from '@angular/core';
import { BusinessService } from '../business-service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { BusinessUnit, Division, DivisionGood, DivisionService } from '../vo/business';
import { CommonService } from '../../../../shared/common/common.service';

import { Observable } from 'rxjs';
import { debounceTime, distinctUntilChanged, map } from 'rxjs/operators';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/merge';
import 'rxjs/add/operator/filter';
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';

@Component({
    selector: 'busdi',
    templateUrl: 'business-detail.component.html'
})

export class BusinessDetailComponent implements OnInit {

    // Related to model
    modalReference: any = null;

    // Other
    businessUnit: BusinessUnit;

    constructor(private route: Router, public commonService: CommonService, private activedRouter: ActivatedRoute, private modalService: NgbModal, private service: BusinessService) { }


    ngOnInit() {
        this.businessUnit = new BusinessUnit();
        this.businessUnit.divisions = [new Division];

        this.triggerReceiveDataEvent();

        if (this.activedRouter.params['_value']['id']) {
            this.activedRouter.params.subscribe(params => {
                this.service.getBusinessId(params.id).subscribe(x => {
                    this.businessUnit = x;
                    if (this.businessUnit.hasProducts == "N") {
                        this.businessUnit.hasProducts = null;
                    }
                    if (this.businessUnit.hasServices == "N") {
                        this.businessUnit.hasServices = null;
                    }
                });
            });
        }
    }

    triggerReceiveDataEvent() {
        this.service.currentMessageEvent.subscribe((data) => {
            this.processReceivedData(data);
            console.log(data)
        });
    }

    processReceivedData(data) {
        if (data.serviceNames) {
            console.log("service", data.serviceNames)
            this.ifServiceReceived(data.serviceNames)
        }

        if (data.productNames) {
            console.log("product", data.productNames)
            this.ifProductReceived(data.productNames);
        }


    }

    ifServiceReceived(data) {
        console.log("ifServiceReceived");
        console.log(data)
        console.log(data.length)
        for (let index = 0; index < data.length; index++) {
            const element = data[index];
            console.log(element);
            if (element.mDivisionId == null) {
                console.log("service division id is null");
                console.log(this.businessUnit.divisions[element.divisionIndex])
                // let res = this.ifNewDivision(data, element.divisionIndex, "service")
                // this.businessUnit.divisions.splice(element.divisionIndex, 1);
                // this.businessUnit.divisions.push(res);
                // break;
            } else {
                console.log(this.businessUnit.divisions[element.divisionIndex])
            }
        }
        this.modalReference.close();
        this.save();
    }

    ifProductReceived(data) {
        console.log("ifProductReceived");
        for (let index = 0; index < data.length; index++) {
            const element = data[index];
            if (element.mDivisionId == null) {
                console.log("product division id is null");
                console.log(this.businessUnit.divisions[element.divisionIndex])
                // let res = this.ifNewDivision(data, element.divisionIndex, "product")
                // this.businessUnit.divisions.splice(element.divisionIndex, 1);
                // this.businessUnit.divisions.push(res);
                break;
            } else {
                console.log(this.businessUnit.divisions[element.divisionIndex])
            }
        }
        this.modalReference.close();
        this.save();
    }

    ifNewDivision(data, index, type) {
        console.log(data, index, type);
        let res: Division
        if (type == "product") {
            let x = this.businessUnit.divisions[index];
            res = { divisionName: x.divisionName, remarks: x.remarks, productNames: data };
            return res;
        } else {
            let x = this.businessUnit.divisions[index];
            res = { divisionName: x.divisionName, remarks: x.remarks, serviceNames: data };
            return res;
        }
    }


    save() {
        if (this.businessUnit.id) {
            this.businessUnit.hasProducts = (this.businessUnit.hasProducts ? "Y" : "N");
            this.businessUnit.hasServices = (this.businessUnit.hasServices ? "Y" : "N");
            this.service.updateBusiness(this.businessUnit.id, this.businessUnit).subscribe(x => {
                this.businessUnit = x;
                this.ngOnInit();
            });
        }
        else {
            this.businessUnit.hasProducts = (this.businessUnit.hasProducts ? "Y" : "N");
            this.businessUnit.hasServices = (this.businessUnit.hasServices ? "Y" : "N");
            this.service.addBusiness(this.businessUnit).subscribe(x => {
                this.businessUnit = x;
                this.route.navigateByUrl("/job/business-units/busdi/" + x.id);
            });
        }
    }

    addNewDivision() {
        let _division = new Division();
        this.businessUnit.divisions.push(_division);
    }

    deleteDivision(i) {
        this.businessUnit.divisions.splice(i, 1)
    }

    openProductModelComponent(productNames, i, id) {
        this.modalReference = null;
        this.modalReference = this.modalService.open(ProductModelPopupComponent, { size: 'lg' });
        this.modalReference.componentInstance.divisionIndex = i;
        if (id) {
            this.modalReference.componentInstance.reference = id;
            this.modalReference.componentInstance.payLoad = productNames;
        } else {
            this.modalReference.componentInstance.reference = "empty";
        }
    }

    openServiceModelComponent(serviceNames, divisionId, i) {
        this.modalReference = null;
        this.modalReference = this.modalService.open(ServiceModelPopupComponent, { size: 'lg' });
        this.modalReference.componentInstance.divisionIndex = i;
        if (divisionId) {
            this.modalReference.componentInstance.reference = divisionId;
            this.modalReference.componentInstance.payLoad = serviceNames;
        } else {
            this.modalReference.componentInstance.reference = "empty";
        }
    }

}


@Component({
    selector: 'product-model-popup',
    templateUrl: 'product-model-popup.component.html'
})

export class ProductModelPopupComponent implements OnInit {
    @Input() public reference;
    @Input() public divisionIndex;
    @Input() public payLoad; //productNames array from division header


    products: any = []; //masterProductList
    materialCode: string = ""; //Only for display (Autocomplte)
    product; //Ro be set after select from masterProductList
    remarks: string = "";

    productNames: Array<DivisionGood> = [];

    constructor(private _businessService: BusinessService) { }

    filterProduct = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.products
                : this.products.filter(v => v.materialCode.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.materialName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    product_formatter = (x: { materialCode: string }) => {
        x.materialCode
    };

    ngOnInit() {
        this._starter();
    }

    _starter() {
        if (this.payLoad) {
            this.productNames = this.payLoad;
            console.log(this.productNames);
        }
        this._businessService.getGoodsAndService('goods').subscribe(x => {
            this.products = x.content;
        })
    }

    onProductSelect($event) {
        this.product = $event.item;
        this.materialCode = $event.item.materialCode;
    }

    addProduct() {
        var mDivId;
        if (this.reference == "empty") {
            mDivId = null;
        } else {
            mDivId = this.reference;
        }
        var res = { mDivisionId: mDivId, mGoodsId: this.product.id, materialCode: this.product.materialCode, materialName: this.product.materialName, remarks: this.remarks, divisionIndex: this.divisionIndex };
        this.productNames.push(res)
        this.reset();
    }

    addProductNamesToDivision() { //Sends the payload to parent component
        this._businessService.changeMessage({ productNames: this.productNames });
    }

    delete(i) {
        this.productNames.splice(i, 1);
    }

    reset() {
        this.materialCode = "";
        this.remarks = "";
    }
}



@Component({
    selector: 'service-model-popup',
    templateUrl: 'service-model-popup.component.html'
})

export class ServiceModelPopupComponent implements OnInit {
    @Input() public reference; // current selected division header Id
    @Input() public divisionIndex; //current selected index from division header
    @Input() public payLoad; //serviceNames array from division header

    services: any = []; //masterServiceList
    serviceCode: string = ""; //Only for display
    service;
    remarks: string = "";

    serviceNames: Array<DivisionService> = [];


    constructor(private _businessService: BusinessService) { }

    serviceFilter = (text$: Observable<string>) =>
        text$.pipe(
            debounceTime(200),
            distinctUntilChanged(),
            map(term => (term === '' ? this.services
                : this.services.filter(v => v.serviceCode.toLowerCase().indexOf(term.toLowerCase()) > -1 || v.serviceName.toLowerCase().indexOf(term.toLowerCase()) > -1)).slice(0, 10))
        );
    service_formatter = (x: { serviceCode: string }) => {
        x.serviceCode
    };

    ngOnInit() {
        this._starter();
    }

    _starter() {
        if (this.payLoad) {
            this.serviceNames = this.payLoad;
            console.log(this.serviceNames);
        }

        this._businessService.getServices().subscribe(x => {
            this.services = x;
        })
    }

    onServiceSelected($event) {
        this.service = $event.item;
        this.serviceCode = $event.item.serviceCode;
    }

    addService() {
        var mDivId;
        if (this.reference == "empty") { //Checking if division header id exists 
            mDivId = null;
        } else {
            mDivId = this.reference;
        }
        var res = { mDivisionId: mDivId, mServiceId: this.service.id, remarks: this.remarks, serviceCode: this.service.serviceCode, serviceName: this.service.serviceName, divisionIndex: this.divisionIndex};
        this.serviceNames.push(res);
        this.reset();
    }

    addServiceNamesToDivision() { //Sends the payload to parent component
        this._businessService.changeMessage({ serviceNames: this.serviceNames });
    }

    delete(i) {
        this.serviceNames.splice(i, 1);
    }

    reset() {
        this.serviceCode = "";
        this.remarks = "";
    }

}