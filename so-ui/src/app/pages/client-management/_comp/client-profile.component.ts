import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { Partner } from '../../job/vo/partner';
import { ClientService } from '../client.service';
import { CommonService } from '../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { SaleOrderModelComponent } from '../../../shared/_models/sale-order-module/sale-order-model.component';
import { JobStatusModelComponent } from '../../../shared/_models/job-status/job-status-model.component'
import helper from '../client-management-helper';
import { ReportService } from '../../report/report.service';
import {UserService} from '../../../auth/_services/user.service';
import {User} from '../../../auth/_models/user';

@Component({
  selector: 'client-profile',
  templateUrl: 'client-profile.component.html'
})

export class ClientProfileComponent implements OnInit {

  @ViewChild('user') user: TemplateRef<any>;
  partner: Partner;
  partnerList: any = [];
  mobileNo: string;
  firstName: string;
  emailId: string;
  address: string;
  clientCode: string;

  clientPos: any = [];
  saleOrders: any = [];
  modalReference: any = null;
  invoices; payments;

  //Search
  searchClientPosString: string;
  sortedClientPos: any = [];
  searchSoString: string;
  sortedSaleOrders: any = [];
  searchPaymentString: string;
  sortedPayments: any = [];
  searchInvoiceString: string;
  sortedInvoices: any = [];

  //Pagination
  poPage = 0; poPageSize = 5;
  soPage = 0; soPageSize = 5;
  inPage = 0; inPageSize = 5;
  payPage = 0; payPageSize = 5;
  Page = 0; PageSize = 5;

  isDescending: boolean = false;

  overDue:number=0;
  currentAmt:number=0;

  Authuser:User;

   //Filters
   soCode; invoiceCode; monthNo; year; quarterName; buName; divisionName;
   functionName; clientName; countryName; jobCodes;
  users: any=[];
  sortedUsers: any=[];
  parentId: string;
  searchUserString: any;
  isHide:boolean=false;


  constructor(private router: Router,
    private route: ActivatedRoute,
    private modalService: NgbModal,
    private commonService: CommonService,
    private clientService: ClientService,
    private reportService:ReportService,
    private userService:UserService) {

  }
  ngOnInit() {
    this.starter();
    this.Authuser = this.userService.getCurrentUser();
    if(this.Authuser.userType!='EMPLOYEE'){
      this.isHide=true;
    }
  }

  sort(listType, type) {
    if (this.isDescending == false) { this.isDescending = true; }
    else if (this.isDescending == true) { this.isDescending = false; }
    switch (listType) {
      case 'clientpos':
        this.sortedClientPos = helper.sort(this.sortedClientPos, type, this.isDescending);
        break;
      case 'saleorder':
        this.sortedSaleOrders = helper.sort(this.sortedSaleOrders, type, this.isDescending);
        break;
      case 'invoice':
        this.sortedInvoices = helper.sort(this.sortedInvoices, type, this.isDescending);
        break;
      case 'payment':
        this.sortedPayments = helper.sort(this.sortedPayments, type, this.isDescending);
        break;
      default:
        break;
    }
  }

  clientPoClientSideSearch() {
    this.sortedClientPos = this.clientPos.filter(s =>
      (this.nullCheck(s.poRefNo).toLowerCase().includes(this.searchClientPosString.toLowerCase()))
      || (this.nullCheck(s.poDesc).toLowerCase().includes(this.searchClientPosString.toLowerCase()))
    );
  }

  clientSideFilterForSaleOrders() {
    this.sortedSaleOrders = this.saleOrders.filter(s =>
      (this.nullCheck(s.saleOrderCode).toLowerCase().includes(this.searchSoString.toLowerCase()))
      || (this.nullCheck(s.projectName).toLowerCase().includes(this.searchSoString.toLowerCase()))
      || (this.nullCheck(s.purchaseOrderId).toLowerCase().includes(this.searchSoString.toLowerCase()))
      || (this.nullCheck(s.status).toLowerCase().includes(this.searchSoString.toLowerCase()))
    );
  }

  clientSideFilterForInvoices() {
    this.sortedInvoices = this.invoices.filter(s =>
      (this.nullCheck(s.refInvoiceNo).toLowerCase().includes(this.searchInvoiceString.toLowerCase()))
      || (this.nullCheck(s.country).toLowerCase().includes(this.searchInvoiceString.toLowerCase()))
    );
  }

  clientSideFilterForPayments() {
    this.sortedPayments = this.payments.filter(s =>
      (this.nullCheck(s.paymentCode).toLowerCase().includes(this.searchPaymentString.toLowerCase()))
      || (this.nullCheck(s.clientName).toLowerCase().includes(this.searchPaymentString.toLowerCase()))
    );
  }

  clientSideFilterForUsers(){
    this.sortedUsers = this.users.filter(s =>
      (this.nullCheck(s.userName).toLowerCase().includes(this.searchUserString.toLowerCase()))
      || (this.nullCheck(s.loginAccess).toLowerCase().includes(this.searchUserString.toLowerCase()))
    );
  }

  private nullCheck(input): string {
    if (input != null) {
      return input;
    } else {
      return " ";
    }
  }

  starter() {
    this.partner = new Partner();
    this.partnerList = new Array<Partner>();
    this.route.params.switchMap((par: Params) => this.clientService.getClientById(par['_id'])).subscribe(x => {
      this.partner = x;
      console.log(this.partner);
      this.partnerList.push(x);
      this.partnerList.forEach(x => {
        this.firstName = x.priFirstName;
        this.emailId = x.emailId;
        this.address = x.address;
        this.clientCode = x.clientCode;
        this.getRevenue();
        x.partnerContacts.forEach(y => {
          this.mobileNo = y.mobileNo;
        })
      });
      this.clientService.getClientPO(this.partner.id).subscribe(x => {
        this.clientPos = x.content;
        this.sortedClientPos = this.clientPos;
      });
      this.clientService.getSaleOrder(this.partner.id).subscribe(x => {
        this.saleOrders = x;
        this.sortedSaleOrders = this.saleOrders;
        console.log(this.sortedSaleOrders)
      })
      this.clientService.getInvoices(this.partner.id).subscribe(x => {
        this.invoices = x;
        this.sortedInvoices = this.invoices;
      }, (error) => {
        console.log("Error fetching invoice/ Status:", error._body)
      });
      this.clientService.getPayments(this.partner.id).subscribe(x => {
        this.payments = x;
        this.sortedPayments = this.payments;
      }, (error) => {
        console.log("Error fetching payments", error._body)
      });
    this.clientService.getUsers(this.partner.id).subscribe(x => {
      this.users = x;
      this.sortedUsers = this.users;
    });
    });
  }

  getRevenue(){
    this.reportService.fetchRevenue(this.soCode, this.invoiceCode, this.monthNo, this.year, this.quarterName, this.buName, this.divisionName,
      this.functionName, this.clientCode, this.clientName, this.countryName, this.jobCodes).subscribe(arg => {       
        arg.revenueList.forEach(element => {
          console.log(element.invOverdueAmt,element.invBalAmt)
          this.overDue=Number(this.overDue)+Number(element.invOverdueAmt);
          this.currentAmt=Number(this.currentAmt)+Number(element.invBalAmt);          
        });
      });
  }


  editClientProfile() {
    this.router.navigateByUrl("client/edit/" + this.partner.id);
  }

  download(docId, docFileName) {
    if (docId != null && docId != "" && docId != undefined) {
      this.commonService.downloadDocument(docId, docFileName);
    }
  }

  openProgressBar(id) {
    this.modalReference = this.modalService.open(JobStatusModelComponent, { size: 'lg' });
    this.modalReference.componentInstance.id = id;
  }

  openSaleOrderModel(saleOrderId?: string) {
    this.modalReference = this.modalService.open(SaleOrderModelComponent, { size: 'lg' });
    this.modalReference.componentInstance.id = saleOrderId;
    this.modalReference.componentInstance.isClient = 'Y';
  }

  Create(){
    // this.testcategory=new TestCategory();
    this.partner = new Partner();
this.modalReference = this.modalService.open(this.user, {size: 'lg'});		
}
}