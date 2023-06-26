import { Component ,OnInit, TemplateRef, ViewChild} from '@angular/core';
import { Router, ActivatedRoute, Params } from '@angular/router';
import{VendorService} from '../vendor.service';
import { Partner,PartnerContact } from '../../job/vo/partner';
import { PurchaseOrder,PurchaseOrderLine,PurchaseOrderPayout } from '../../job/vo/purchase-order';
import { CommonService } from '../../../shared/common/common.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';


@Component({
    selector:'vendor-profile',
    templateUrl:'vendor-profile.component.html'
})

export class VendorProfileComponent implements OnInit{
  @ViewChild('vendors') vendors: TemplateRef<any>;
partner:Partner;
partnerList:any=[];
mobileNo:string;
firstName:string;
emailId:string;
address:string;
vendorCode:string;
overDue:number=0;
currentAmt:number=0;
purchaseOrders:[PurchaseOrder];
sortPurchaseOrders:any=[];
purchaseOrderPayout:[PurchaseOrderPayout];
sortPurchaseOrderPayout:any=[];
poPage = 0; poPageSize = 5;
payPage = 0; payPageSize = 5;
Page = 0; PageSize = 5;
searchClientPosString:string='';
searchPaymet:string='';
  vendor: any;
  sortVendor: any=[];
  modalReference: any;
  parentId: string;
  searchUserString: any;


    constructor(private router:Router, private route: ActivatedRoute,private modalService: NgbModal,private vendorService:VendorService,private commonService:CommonService){

    }
    ngOnInit(){
        this.partnerList = new Array<Partner>();
        this.partner=new Partner();
        this.purchaseOrderPayout=[new PurchaseOrderPayout];
        this.purchaseOrders=[new PurchaseOrder];
	
        this.route.params.switchMap((par: Params) => this.vendorService.getVendorById(par['_id'])).subscribe(x => {
            this.partner = x;
            this.partnerList.push(x);
            this.startUp();
          console.log(this.partnerList);
          this.partnerList.forEach(x=>{
              this.firstName= x.priFirstName;
              this.emailId = x.emailId;
              this.address= x.address;
              this.vendorCode=x.vendorCode;
              x.partnerContacts.forEach(y=>{
                this.mobileNo=y.mobileNo;
          })
        });  
          });
        }
    
    editVendorProfile(){
        this.router.navigateByUrl("vendor/edit/"+this.partner.id);
    }

    startUp(){
      this.vendorService.getPayable(this.partner.id,this.partner.vendorName).subscribe(x=>{
        console.log(x);
        for(let element of x.payableList){
          this.overDue=Number(this.overDue)+Number(element.totalOverdueAmt);
          this.currentAmt=Number(this.currentAmt)+Number(element.totalDueAmt); 
        }
    });

    this.vendorService.getPurchaseOrderByVendor(this.partner.id).subscribe(x=>{
      this.purchaseOrders=x.content ;
      this.sortPurchaseOrders=x.content;
    });
    this.vendorService.getPayoutByVendor(this.partner.id).subscribe(payout=>{
      this.purchaseOrderPayout=payout
      this.sortPurchaseOrderPayout=payout
    });
    this.vendorService.getVendor(this.partner.id).subscribe(payout=>{
      this.vendor=payout
      this.sortVendor=this.vendor
    });
  }

  paymetSideSerach(){
    this.sortPurchaseOrderPayout = this.purchaseOrderPayout.filter(x=>
      (this.nullCheck(x.payoutRef).toLowerCase().includes(this.searchPaymet.toLowerCase()))||
      (this.nullCheck(x.payoutDate).toLowerCase().includes(this.searchPaymet.toLowerCase()))  
      );
  }

  VendorFilterForUsers(){
    this.sortVendor = this.vendor.filter(s =>
      (this.nullCheck(s.userName).toLowerCase().includes(this.searchUserString.toLowerCase()))
      || (this.nullCheck(s.loginAccess).toLowerCase().includes(this.searchUserString.toLowerCase()))
    );
  }

  PoVendorSideSearch() {
    this.sortPurchaseOrders = this.purchaseOrders.filter(s =>
      (this.nullCheck(s.poCode).toLowerCase().includes(this.searchClientPosString.toLowerCase())) ||
      (this.nullCheck(s.poDt).toLowerCase().includes(this.searchClientPosString.toLowerCase()))
    );
  }

  private nullCheck(input): string {
    if (input != null) {
      return input;
    } else {
      return " ";
    }
  }

  download(docId, docFileName) {
    if (docId != null && docId != "" && docId != undefined) {
      this.commonService.downloadDocument(docId, docFileName);
    }
  }

  Create(){
    // this.testcategory=new TestCategory();
    this.partner = new Partner();
this.modalReference = this.modalService.open(this.vendors, {size: 'lg'});		
}
}

   
