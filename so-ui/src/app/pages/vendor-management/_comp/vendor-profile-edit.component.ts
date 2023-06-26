import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { VendorService } from '../vendor.service';
import { Partner, PartnerContact, PartnerDocument, PartnerEmployee, PartnerAccountInfo } from '../../job/vo/partner';
import { environment } from './../../../../environments/environment';
import { Observable } from 'rxjs';
import { Headers, Http, RequestOptions, Response, URLSearchParams, ResponseContentType } from "@angular/http";
import { NULL_EXPR } from '@angular/compiler/src/output/output_ast';
import { CommonService } from '../../../shared/common/common.service';

@Component({
  selector: 'vendor-profile-edit',
  templateUrl: 'vendor-profile-edit.component.html',
  styleUrls: ['./vendor.css']
})

export class VendorProfileEditComponent implements OnInit {
  partner: Partner;
  addScreen: boolean = false;
  partnerList: Array<Partner> = [];
  logoUrl: any;
  countries: any = [];
  partnerContact: PartnerContact;

  flowType = '';
  id: string;
  msg: any;
  name: string;
  isEdit: boolean = false;

  documentApiUrl: string = environment.documentApiUrl;
  constructor(private modalService: NgbModal, private commonService: CommonService, private http: Http, private route: ActivatedRoute, private vendorService: VendorService) { }
  ngOnInit() {
    this.partner = new Partner();
    this.partnerList = new Array<Partner>();

    this.partner.partnerDocuments = new Array<PartnerDocument>();
    console.log(this.partner.partnerDocuments.length)
    if (this.partner.partnerDocuments.length == 0) {
      this.addVendorDoc();
    }
    console.log(this.partner.partnerDocuments.length)
    this.partner.partnerContacts = new Array<PartnerContact>();
    this.partner.partnerEmployees = new Array<PartnerEmployee>();
    this.partner.partnerAccountInfos = new Array<PartnerAccountInfo>();
    // this.partner.partnerDocuments = new Array<PartnerDocument>();

    this.vendorService.getAllCountries().subscribe(x => {
      this.countries = x;
    })


    if (this.route.params['value']['_id']) {
      this.route.params.switchMap((par: Params) => this.vendorService.getVendorById(par['_id'])).subscribe(x => {
        x.partnerDocuments.forEach(element => {
          element.isAttached=element.isAttached=='Y'?'Y':null;
        });
        this.logoUrl = environment.documentApiUrl + "dm/" + x.logoDocId;
        this.partner = x;
        console.log(x);

        this.partnerList.push(x);

      });
    }

    else {
      this.partner.partnerDocuments = [new PartnerDocument];
      this.partner.partnerEmployees = [new PartnerEmployee];
      this.partner.partnerAccountInfos = [new PartnerAccountInfo];
    }
  }
  vendorContactRows = [1];
  vendorContactCount = 1;
  vendorDocumentRows = [1];
  vendorDocumentCount = 1;
  vendorBankCards = [1];
  vendorBankCount = 1;



  objBefArr(x) {

    this.partner.partnerDocuments = this.partner.partnerDocuments.length ? this.partner.partnerDocuments : [new PartnerDocument()];
    this.vendorDocumentCount = this.partner.partnerDocuments.length ? this.partner.partnerDocuments.length : 1;


    this.partner.partnerEmployees = this.partner.partnerEmployees.length ? this.partner.partnerEmployees : [new PartnerEmployee()];
    this.vendorContactCount = this.partner.partnerEmployees.length ? this.partner.partnerEmployees.length : 1;


    this.partner.partnerAccountInfos = this.partner.partnerAccountInfos.length ? this.partner.partnerAccountInfos : [new PartnerAccountInfo()];
    this.vendorBankCount = this.partner.partnerAccountInfos.length ? this.partner.partnerAccountInfos.length : 1;


  }

  addVendorDoc() {
    console.log("addVendorDoc")
    this.vendorDocumentCount++;
    let ts = new PartnerDocument();
    this.partner.partnerDocuments.push(ts);

  }
  addVendorContact() {
    this.vendorContactCount++;
    let ts = new PartnerEmployee();
    this.partner.partnerEmployees.push(ts);

  }
  delVendorDoc(i) {
    this.partner.partnerDocuments.splice(i, 1);
  }
  delVendorContact(i) {

    this.partner.partnerEmployees.splice(i, 1);
  }

  addVendorBank() {
    this.vendorBankCount++;
    let ts = new PartnerAccountInfo();
    this.partner.partnerAccountInfos.push(ts);
  }
  delVendorBank(i) {

    this.partner.partnerAccountInfos.splice(i, 1);
  }



  open(content) {
    this.modalService.open(content).result.then((result) => {
    });
  }
  private jwt(paramArr?: any) {
    let currentUser = JSON.parse(localStorage.getItem('currentUser'));
    let headers = new Headers({ 'Accept': 'application/json', 'enctype': 'multipart/form-data', 'authorization': currentUser.appToken });
    if (paramArr) {
      let myParams = new URLSearchParams();
      paramArr.forEach(x => myParams.append(x.key, x.value));
      return new RequestOptions({ headers: headers, params: myParams });
    }
    else return new RequestOptions({ headers: headers });
  }
  fileChange(event, type: any, i) {
    let fileList: FileList = event.target.files;
    //console.log(type)
    // console.log(jobAssetDocs)

    if (fileList.length > 0) {
      let files: File = fileList[0];
      this.commonService.uploadDocument(fileList[0], type).map((response: Response) => response)
        .catch(error => Observable.throw(error))
        .subscribe(
          data => {
            if (type == 'CLIENT-Logo') {
              this.partner.logoDocId = data[0].docId;
            } else if (type == 'CLIENT-BILLING') {
              this.partner.partnerDocuments[i].docId = data[0].docId;
            }
          },
          error => console.log(error)
        )
    }

  }
  save() {
    this.partner.partnerDocuments.forEach(element => {
      element.isAttached=element.isAttached?'Y':'N';
    });
    this.vendorService.updateVendor(this.partner, this.id).subscribe(x => {
      this.msg = {
        type: 'success', text: "Vendor Added successfully"
      }, error => {
        this.msg = { type: 'danger', text: "Error in Vendor Addition" }

      }


    });
  }
}