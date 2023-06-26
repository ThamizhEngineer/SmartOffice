import { Component, OnInit } from '@angular/core';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ClientService } from '../client.service';
import { Partner, PartnerContact, PartnerDocument, PartnerEmployee, PartnerAccountInfo, PartnerGsts } from '../../job/vo/partner';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { environment } from './../../../../environments/environment';
import { Headers, Http, RequestOptions, Response, URLSearchParams, ResponseContentType } from "@angular/http";
import { Observable } from 'rxjs';
import { CommonService } from '../../../shared/common/common.service';
import { Album } from '../vo/Album';

@Component({
  selector: 'client-profile-edit',
  templateUrl: 'client-profile-edit.component.html',
  styleUrls: ['./client.css']
})

export class ClientProfileEditComponent implements OnInit {
  partner: Partner;
  addScreen: boolean = false;
  partnerList: Array<Partner> = [];
  partnerGsts: PartnerGsts;
  countries: any = [];
  partnerContact: PartnerContact;
  urlValue: any;
  flowType = '';
  id: string;
  msg: any;
  name: string;
  isEdit: boolean = false;
  album: Album;
  exchangeType:any=[];
  companyTypes:any=[];
  logoUrl:any;
  documentApiUrl: string = environment.documentApiUrl;
  constructor(private router: Router,
     private modalService: NgbModal,
      private http: Http, 
      private commonService: CommonService,
      private route: ActivatedRoute, 
      private clientService: ClientService) { }
  ngOnInit() {
    this.album = new Album();
    this.partner = new Partner();
    this.partnerList = new Array<Partner>();
    this.partnerGsts = new PartnerGsts();

    this.partner.partnerDocuments = new Array<PartnerDocument>();
    console.log(this.partner.partnerDocuments.length)
    if (this.partner.partnerDocuments.length == 0) {
      this.addClientDoc();
    }
    console.log(this.partner.partnerDocuments.length)
    this.partner.partnerContacts = new Array<PartnerContact>();
    this.partner.partnerEmployees = new Array<PartnerEmployee>();
    this.partner.partnerAccountInfos = new Array<PartnerAccountInfo>();
    // this.partner.partnerDocuments = new Array<PartnerDocument>();

    this.clientService.getAllCountries().subscribe(x => {
      this.countries = x;
    })


    if (this.route.params['value']['_id']) {
      this.route.params.switchMap((par: Params) => this.clientService.getClientById(par['_id'])).subscribe(x => {
        x.partnerDocuments.forEach(element => {
          element.isAttached=element.isAttached=='Y'?'Y':null;
        });
        this.logoUrl=environment.documentApiUrl+"dm/"+x.logoDocId;
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
    this.clientService.getCompanyTypes()
    this.clientService.getCurrency().subscribe(x=>this.exchangeType=x);
    this.clientService.getCompanyTypes().subscribe(x=>this.companyTypes=x);
  }
  clientContactRows = [1];
  clientContactCount = 1;
  clientDocumentRows = [1];
  clientDocumentCount = 1;
  clientBankCards = [1];
  clientBankCount = 1;



  objBefArr(x) {

    this.partner.partnerDocuments = this.partner.partnerDocuments.length ? this.partner.partnerDocuments : [new PartnerDocument()];
    this.clientDocumentCount = this.partner.partnerDocuments.length ? this.partner.partnerDocuments.length : 1;


    this.partner.partnerEmployees = this.partner.partnerEmployees.length ? this.partner.partnerEmployees : [new PartnerEmployee()];
    this.clientContactCount = this.partner.partnerEmployees.length ? this.partner.partnerEmployees.length : 1;


    this.partner.partnerAccountInfos = this.partner.partnerAccountInfos.length ? this.partner.partnerAccountInfos : [new PartnerAccountInfo()];
    this.clientBankCount = this.partner.partnerAccountInfos.length ? this.partner.partnerAccountInfos.length : 1;


  }

  addClientDoc() {
    console.log("addclientDoc")
    this.clientDocumentCount++;
    let ts = new PartnerDocument();
    this.partner.partnerDocuments.push(ts);

  }
  addClientContact() {
    this.clientContactCount++;
    let ts = new PartnerEmployee();
    this.partner.partnerEmployees.push(ts);   
  }
  delClientDoc(i) {
    this.partner.partnerDocuments.splice(i, 1);
  }
  delClientContact(i) {

    this.partner.partnerEmployees.splice(i, 1);
  }

  addClientBank() {
    this.clientBankCount++;
    let ts = new PartnerAccountInfo();
    this.partner.partnerAccountInfos.push(ts);
  }
  delClientBank(i) {
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


  fileChange(event, type: any,i) {
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
              this.partner.logoDocId=data[0].docId;
              this.urlValue = data[0].docLocation + '/' + data[0].docName;
              console.log(this.urlValue);
              this.photoUpload(this.urlValue);
            }else if(type == 'CLIENT-BILLING'){
              this.partner.partnerDocuments[i].docId=data[0].docId;
            }
          },
          error => console.log(error)
      )
          
          
    }

  }

 

  save() {
    // this.partnerGsts.gstNo = this.partner.gstNo;
    // this.partner.partnerGsts.push(this.partnerGsts);
 let partnerEmp:number;
 this.partner.partnerDocuments.forEach(element => {
  element.isAttached=element.isAttached?'Y':'N';
});
 partnerEmp= this.partner.partnerEmployees.filter(parEmp=>parEmp.firstName==null || parEmp.lastName==null || parEmp.emailId==null).length;
    if(partnerEmp!=0){
      console.log(partnerEmp)
      this.msg = { type: 'danger', text: "Client Contact Need All Information" }
    }else{      
    this.clientService.updateClient(this.partner, this.partner.id).subscribe(x => {
      this.router.navigateByUrl('/client/list');
      this.msg = {
        type: 'success', text: "Client Added successfully"
      }, error => {
        this.msg = { type: 'danger', text: "Error in Client Addition" }
      }
    });
    }

   
  }

  download(docId, docFileName){
    if(docId!=null&&docId!=""&&docId!=undefined){
        this.commonService.downloadDocument(docId,docFileName);
    } 
}

  photoUpload(value) {
    this.album.location = value;
    console.log(this.album);
    this.clientService.addClientLogo(this.album).subscribe(x => {
      console.log(value);
      this.partner.logoUrl = x[0].url;
      console.log(this.partner.logoUrl);
    })
  }


}